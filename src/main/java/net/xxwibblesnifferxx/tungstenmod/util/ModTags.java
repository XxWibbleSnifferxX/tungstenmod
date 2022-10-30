package net.xxwibblesnifferxx.tungstenmod.util;

import com.mojang.datafixers.types.templates.Tag;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.xxwibblesnifferxx.tungstenmod.TungstenMod;

public class ModTags
{
    public static class Blocks
    {
        private static TagKey<Block> createTag(String name) //formerly Tag.Identified
        {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(TungstenMod.MOD_ID, name));
        }

        private static TagKey<Block> createCommonTag(String name)
        {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier("c", name));
        }
    }

    public static class Items
    {
        //test tag, not used
        public static final TagKey<Item> TUNGSTEN_INGOTS = createCommonTag("tungsten_ingots");

        //not a test tag, hopefully used later lmao
        public static final TagKey<Item> CRUCIBLE_SMELTABLE = createTag("crucible_smeltable");

        private static TagKey<Item> createTag(String name) //formerly Tag.Identified
        {
            return TagKey.of(Registry.ITEM_KEY, new Identifier(TungstenMod.MOD_ID, name));
        }

        private static TagKey<Item> createCommonTag(String name)
        {
            return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
        }
    }
}
