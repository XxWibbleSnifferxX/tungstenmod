package net.xxwibblesnifferxx.tungstenmod;

import net.fabricmc.api.ModInitializer;
import net.xxwibblesnifferxx.tungstenmod.block.ModBlocks;
import net.xxwibblesnifferxx.tungstenmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TungstenMod implements ModInitializer {
	public static final String MOD_ID = "tungstenmod"; //makes life easier
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{

		ModItems.registerModItems(); //register all mod items
		ModBlocks.registerModBlocks(); //register all mod blocks
	}
}
