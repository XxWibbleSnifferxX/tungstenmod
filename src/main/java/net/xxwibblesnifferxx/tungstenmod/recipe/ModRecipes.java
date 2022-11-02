package net.xxwibblesnifferxx.tungstenmod.recipe;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.xxwibblesnifferxx.tungstenmod.TungstenMod;

public class ModRecipes
{
    public static void registerRecipes()
    {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(TungstenMod.MOD_ID, CrucibleRecipe.Serializer.ID),
                CrucibleRecipe.Serializer.INSTANCE);

        Registry.register(Registry.RECIPE_TYPE, new Identifier(TungstenMod.MOD_ID, CrucibleRecipe.Type.ID),
                CrucibleRecipe.Type.INSTANCE);
    }
}
