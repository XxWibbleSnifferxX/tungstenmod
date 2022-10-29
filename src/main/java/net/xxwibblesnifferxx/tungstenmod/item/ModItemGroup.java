package net.xxwibblesnifferxx.tungstenmod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.xxwibblesnifferxx.tungstenmod.TungstenMod;

public class ModItemGroup
{
    public static final ItemGroup TUNGSTEN = FabricItemGroupBuilder.build(new Identifier(TungstenMod.MOD_ID, "tungsten"),
            ()-> new ItemStack(ModItems.TUNGSTEN_INGOT));

    public static final ItemGroup BISMUTH = FabricItemGroupBuilder.build(new Identifier(TungstenMod.MOD_ID, "bismuth"),
            ()-> new ItemStack(Items.DIAMOND));
}
