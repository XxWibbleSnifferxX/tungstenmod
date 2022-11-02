package net.xxwibblesnifferxx.tungstenmod;

import net.fabricmc.api.ModInitializer;
import net.xxwibblesnifferxx.tungstenmod.block.ModBlocks;
import net.xxwibblesnifferxx.tungstenmod.block.entity.ModBlockEntities;
import net.xxwibblesnifferxx.tungstenmod.item.ModItems;
import net.xxwibblesnifferxx.tungstenmod.recipe.ModRecipes;
import net.xxwibblesnifferxx.tungstenmod.screen.ModScreenHandlers;
import net.xxwibblesnifferxx.tungstenmod.world.feature.ModConfiguredFeatures;
import net.xxwibblesnifferxx.tungstenmod.world.gen.ModWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TungstenMod implements ModInitializer {
	public static final String MOD_ID = "tungstenmod"; //makes life easier
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		ModConfiguredFeatures.registerConfiguredFeatures(); //register those juicy configured features (yum!)

		ModItems.registerModItems(); //register all mod items
		ModBlocks.registerModBlocks(); //register all mod blocks
		ModBlockEntities.registerAllBlockEntities(); //register all block entities
		ModRecipes.registerRecipes(); //register recipes

		ModWorldGen.generateModWorldGen(); //generate ores
	}
}
