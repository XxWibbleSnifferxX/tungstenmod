package net.xxwibblesnifferxx.tungstenmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.client.gl.Uniform;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.xxwibblesnifferxx.tungstenmod.TungstenMod;
import net.xxwibblesnifferxx.tungstenmod.item.ModItemGroup;

public class ModBlocks
{
    //make blocks
    public static final Block WOLFRAMITE_ORE = registerBlock("wolframite_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(2.2f).requiresTool(), UniformIntProvider.create(3, 8)), ModItemGroup.TUNGSTEN);

    public static final Block TUNGSTEN_BLOCK = registerBlock("tungsten_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(2.4f).requiresTool()), ModItemGroup.TUNGSTEN);

    //registration
    private static Block registerBlock(String name, Block block, ItemGroup group)
    {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(TungstenMod.MOD_ID, name), block); //registers block under namespace
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group)
    {
        return Registry.register(Registry.ITEM, new Identifier(TungstenMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }
    public static void registerModBlocks()
    {
        TungstenMod.LOGGER.info("Registering mod blocks for " + TungstenMod.MOD_ID);
    }
}
