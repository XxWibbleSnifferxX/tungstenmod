package net.xxwibblesnifferxx.tungstenmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.xxwibblesnifferxx.tungstenmod.TungstenMod;
import net.xxwibblesnifferxx.tungstenmod.block.ModBlocks;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities
{
    public static BlockEntityType<CrucibleBlockEntity> CRUCIBLE;

    public static void registerAllBlockEntities()
    {
        CRUCIBLE = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(TungstenMod.MOD_ID, "crucible"),
                FabricBlockEntityTypeBuilder.create(CrucibleBlockEntity::new,
                    ModBlocks.CRUCIBLE).build(null));
    }
}
