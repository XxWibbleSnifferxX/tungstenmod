package net.xxwibblesnifferxx.tungstenmod.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.xxwibblesnifferxx.tungstenmod.TungstenMod;
import net.xxwibblesnifferxx.tungstenmod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures
{
    public static final List<OreFeatureConfig.Target> OVERWORLD_WOLFRAMITE_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    ModBlocks.WOLFRAMITE_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> WOLFRAMITE_ORE =
            ConfiguredFeatures.register("wolframite_ore", Feature.ORE,
                    new OreFeatureConfig(OVERWORLD_WOLFRAMITE_ORES, 4));

    public static void registerConfiguredFeatures()
    {
        System.out.println("Registering ModConfiguredFeatures for" + TungstenMod.MOD_ID);
    }
}
