package net.xxwibblesnifferxx.tungstenmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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
