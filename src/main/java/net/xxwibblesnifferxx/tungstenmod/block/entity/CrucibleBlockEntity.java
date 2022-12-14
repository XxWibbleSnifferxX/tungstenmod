package net.xxwibblesnifferxx.tungstenmod.block.entity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.xxwibblesnifferxx.tungstenmod.item.ModItems;
import net.xxwibblesnifferxx.tungstenmod.item.inventory.ImplementedInventory;
import net.xxwibblesnifferxx.tungstenmod.recipe.CrucibleRecipe;
import net.xxwibblesnifferxx.tungstenmod.screen.slot.CrucibleScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CrucibleBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory
{
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(4, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 400; //time to finish one smelt, default 72
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public CrucibleBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CRUCIBLE, pos, state);
        this.propertyDelegate = new PropertyDelegate()
        {
            public int get(int index)
            {
                switch (index) //look mr buckland i am using a switch statement
                {
                    case 0: return CrucibleBlockEntity.this.progress;
                    case 1: return CrucibleBlockEntity.this.maxProgress;
                    case 2: return CrucibleBlockEntity.this.fuelTime;
                    case 3: return CrucibleBlockEntity.this.maxFuelTime;
                    default: return 0;
                }
            }

            public void set(int index, int value)
            {
                switch(index)
                {
                    case 0: CrucibleBlockEntity.this.progress = value; break;
                    case 1: CrucibleBlockEntity.this.maxProgress = value; break;
                    case 2: CrucibleBlockEntity.this.fuelTime = value; break;
                    case 3: CrucibleBlockEntity.this.maxFuelTime = value; break;
                }
            }

            public int size()
            {
                return 4; //number of variables obv
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems()
    {
        return inventory;
    }

    @Override
    public Text getDisplayName()
    {
        return Text.literal("Purification Crucible"); //sick
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player)
    {
        return new CrucibleScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt)
    {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("crucible.progress", progress);
        nbt.putInt("crucible.fuelTime", fuelTime);
        nbt.putInt("crucible.maxFuelTime", maxFuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt)
    {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("crucible.progress");
        fuelTime = nbt.getInt("crucible.fuelTime");
        maxFuelTime = nbt.getInt("crucible.maxFuelTime");
    }

    private void consumeFuel()
    {
        if(!getStack(0).isEmpty())
        {
            this.fuelTime = FuelRegistry.INSTANCE.get(this.removeStack(0, 1).getItem());
            this.maxFuelTime = this.fuelTime;
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, CrucibleBlockEntity entity)
    {
        if(isConsumingFuel(entity))
        {
            entity.fuelTime--; //if fuel is being consumed, consume the fuel lol
        }

        if(hasRecipe(entity))
        {
            if(hasFuelInFuelSlot(entity) && !isConsumingFuel(entity))
            {
                entity.consumeFuel(); //consume fuel item
            }
            if(isConsumingFuel(entity))
            {
                entity.progress++; //increase smelting progress
                if(entity.progress > entity.maxProgress)
                {
                    craftItem(entity); //finish smelting
                }
            }
        }
        else
        {
            entity.resetProgress();
        }
    }

    private static boolean hasFuelInFuelSlot(CrucibleBlockEntity entity)
    {
        return !entity.getStack(0).isEmpty();
    }

    private static boolean isConsumingFuel(CrucibleBlockEntity entity)
    {
        return entity.fuelTime > 0;
    }

    private static boolean hasRecipe(CrucibleBlockEntity entity) //find out if everything for a crucible recipe is present
    {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++)
        {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<CrucibleRecipe> match = world.getRecipeManager()
                .getFirstMatch(CrucibleRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    private static void craftItem(CrucibleBlockEntity entity)
    {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++)
        {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<CrucibleRecipe> match = world.getRecipeManager()
                .getFirstMatch(CrucibleRecipe.Type.INSTANCE, inventory, world);

        if(match.isPresent())
        {
            entity.removeStack(1,1);
            entity.removeStack(2,1);

            entity.setStack(3, new ItemStack(match.get().getOutput().getItem(),
                    entity.getStack(3).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(3).getItem() == output.getItem() || inventory.getStack(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory)
    {
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }

}
