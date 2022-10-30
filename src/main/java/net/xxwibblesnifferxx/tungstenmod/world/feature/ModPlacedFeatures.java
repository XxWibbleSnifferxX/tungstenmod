package net.xxwibblesnifferxx.tungstenmod.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;

public class ModPlacedFeatures
{

    public static final RegistryEntry<PlacedFeature> WOLFRAMITE_ORE_PLACED = PlacedFeatures.register("mythril_ore_placed",
            ModConfiguredFeatures.WOLFRAMITE_ORE, ModOreFeatures.modifiersWithCount(3,
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(-10))));
}
