package net.xxwibblesnifferxx.tungstenmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.xxwibblesnifferxx.tungstenmod.TungstenMod;

public class ModItems
{
    //make items
    public static final Item WOLFRAMITE_CHUNK = registerItem("wolframite_chunk",
            new Item(new FabricItemSettings().group(ModItemGroup.TUNGSTEN)));

    public static final Item TUNGSTEN_INGOT = registerItem("tungsten_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.TUNGSTEN)));

    //make armor
    public static final Item TUNGSTEN_HELMET = registerItem("tungsten_helmet",
            new ArmorItem(ModArmorMaterials.TUNGSTEN, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.TUNGSTEN)));

    public static final Item TUNGSTEN_CHESTPLATE = registerItem("tungsten_chestplate",
            new ArmorItem(ModArmorMaterials.TUNGSTEN, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.TUNGSTEN)));

    public static final Item TUNGSTEN_LEGGINGS = registerItem("tungsten_leggings",
            new ArmorItem(ModArmorMaterials.TUNGSTEN, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.TUNGSTEN)));

    public static final Item TUNGSTEN_BOOTS = registerItem("tungsten_boots",
            new ArmorItem(ModArmorMaterials.TUNGSTEN, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.TUNGSTEN)));

    //registration
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registry.ITEM, new Identifier(TungstenMod.MOD_ID, name), item);
    }
    public static void registerModItems()
    {
        TungstenMod.LOGGER.info("Registering mod items for  " + TungstenMod.MOD_ID);
    }


}
