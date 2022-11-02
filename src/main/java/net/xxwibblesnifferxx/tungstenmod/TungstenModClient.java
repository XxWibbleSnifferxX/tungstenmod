package net.xxwibblesnifferxx.tungstenmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import net.xxwibblesnifferxx.tungstenmod.block.ModBlocks;
import net.xxwibblesnifferxx.tungstenmod.screen.CrucibleScreen;
import net.xxwibblesnifferxx.tungstenmod.screen.ModScreenHandlers;

public class TungstenModClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRUCIBLE, RenderLayer.getCutout());

        ScreenRegistry.register(ModScreenHandlers.CRUCIBLE_SCREEN_HANDLER, CrucibleScreen::new);
    }
}
