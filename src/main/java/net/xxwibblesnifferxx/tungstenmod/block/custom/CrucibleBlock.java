package net.xxwibblesnifferxx.tungstenmod.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.xxwibblesnifferxx.tungstenmod.block.entity.CrucibleBlockEntity;
import net.xxwibblesnifferxx.tungstenmod.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class CrucibleBlock extends BlockWithEntity implements BlockEntityProvider
{
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING; //important to let the crucible face the correct direction

    public CrucibleBlock(Settings settings)
    {
        super(settings);
    }

    //get custom hitbox for crucible
    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(1, 0, 1, 15, 3, 15),
            Block.createCuboidShape(2, 3, 2, 14, 15, 14),
            Block.createCuboidShape(2, 15, 2, 14, 28, 14),
            Block.createCuboidShape(1, 28, 1, 15, 31, 15),
            Block.createCuboidShape(0, 0, 0, 2, 15, 2),
            Block.createCuboidShape(14, 0, 0, 16, 15, 2),
            Block.createCuboidShape(0, 15, 0, 2, 31, 2),
            Block.createCuboidShape(14, 15, 0, 16, 31, 2),
            Block.createCuboidShape(0, 0, 14, 2, 15, 16),
            Block.createCuboidShape(0, 15, 14, 2, 31, 16),
            Block.createCuboidShape(14, 0, 14, 16, 15, 16),
            Block.createCuboidShape(14, 15, 14, 16, 31, 16),
            Block.createCuboidShape(3, 31, 3, 13, 32, 13),
            Block.createCuboidShape(14, 5, 4, 15, 15, 12),
            Block.createCuboidShape(14, 15, 4, 15, 26, 12),
            Block.createCuboidShape(1, 5, 4, 2, 15, 12),
            Block.createCuboidShape(1, 15, 4, 2, 26, 12),
            Block.createCuboidShape(4, 5, 14, 12, 15, 15),
            Block.createCuboidShape(4, 15, 14, 12, 26, 15),
            Block.createCuboidShape(4, 18, 1, 12, 26, 2),
            Block.createCuboidShape(9, 5, 1, 12, 17, 2),
            Block.createCuboidShape(4, 14, 1, 8, 17, 2),
            Block.createCuboidShape(4, 5, 1, 8, 13, 2)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(1, 0, 1, 15, 3, 15),
            Block.createCuboidShape(2, 3, 2, 14, 15, 14),
            Block.createCuboidShape(2, 15, 2, 14, 28, 14),
            Block.createCuboidShape(1, 28, 1, 15, 31, 15),
            Block.createCuboidShape(0, 0, 14, 2, 15, 16),
            Block.createCuboidShape(0, 0, 0, 2, 15, 2),
            Block.createCuboidShape(0, 15, 14, 2, 31, 16),
            Block.createCuboidShape(0, 15, 0, 2, 31, 2),
            Block.createCuboidShape(14, 0, 14, 16, 15, 16),
            Block.createCuboidShape(14, 15, 14, 16, 31, 16),
            Block.createCuboidShape(14, 0, 0, 16, 15, 2),
            Block.createCuboidShape(14, 15, 0, 16, 31, 2),
            Block.createCuboidShape(3, 31, 3, 13, 32, 13),
            Block.createCuboidShape(4, 5, 1, 12, 15, 2),
            Block.createCuboidShape(4, 15, 1, 12, 26, 2),
            Block.createCuboidShape(4, 5, 14, 12, 15, 15),
            Block.createCuboidShape(4, 15, 14, 12, 26, 15),
            Block.createCuboidShape(14, 5, 4, 15, 15, 12),
            Block.createCuboidShape(14, 15, 4, 15, 26, 12),
            Block.createCuboidShape(1, 18, 4, 2, 26, 12),
            Block.createCuboidShape(1, 5, 4, 2, 17, 7),
            Block.createCuboidShape(1, 14, 8, 2, 17, 12),
            Block.createCuboidShape(1, 5, 8, 2, 13, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(1, 0, 1, 15, 3, 15),
            Block.createCuboidShape(2, 3, 2, 14, 15, 14),
            Block.createCuboidShape(2, 15, 2, 14, 28, 14),
            Block.createCuboidShape(1, 28, 1, 15, 31, 15),
            Block.createCuboidShape(14, 0, 14, 16, 15, 16),
            Block.createCuboidShape(0, 0, 14, 2, 15, 16),
            Block.createCuboidShape(14, 15, 14, 16, 31, 16),
            Block.createCuboidShape(0, 15, 14, 2, 31, 16),
            Block.createCuboidShape(14, 0, 0, 16, 15, 2),
            Block.createCuboidShape(14, 15, 0, 16, 31, 2),
            Block.createCuboidShape(0, 0, 0, 2, 15, 2),
            Block.createCuboidShape(0, 15, 0, 2, 31, 2),
            Block.createCuboidShape(3, 31, 3, 13, 32, 13),
            Block.createCuboidShape(1, 5, 4, 2, 15, 12),
            Block.createCuboidShape(1, 15, 4, 2, 26, 12),
            Block.createCuboidShape(14, 5, 4, 15, 15, 12),
            Block.createCuboidShape(14, 15, 4, 15, 26, 12),
            Block.createCuboidShape(4, 5, 1, 12, 15, 2),
            Block.createCuboidShape(4, 15, 1, 12, 26, 2),
            Block.createCuboidShape(4, 18, 14, 12, 26, 15),
            Block.createCuboidShape(4, 5, 14, 7, 17, 15),
            Block.createCuboidShape(8, 14, 14, 12, 17, 15),
            Block.createCuboidShape(8, 5, 14, 12, 13, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(1, 0, 1, 15, 3, 15),
            Block.createCuboidShape(2, 3, 2, 14, 15, 14),
            Block.createCuboidShape(2, 15, 2, 14, 28, 14),
            Block.createCuboidShape(1, 28, 1, 15, 31, 15),
            Block.createCuboidShape(14, 0, 0, 16, 15, 2),
            Block.createCuboidShape(14, 0, 14, 16, 15, 16),
            Block.createCuboidShape(14, 15, 0, 16, 31, 2),
            Block.createCuboidShape(14, 15, 14, 16, 31, 16),
            Block.createCuboidShape(0, 0, 0, 2, 15, 2),
            Block.createCuboidShape(0, 15, 0, 2, 31, 2),
            Block.createCuboidShape(0, 0, 14, 2, 15, 16),
            Block.createCuboidShape(0, 15, 14, 2, 31, 16),
            Block.createCuboidShape(3, 31, 3, 13, 32, 13),
            Block.createCuboidShape(4, 5, 14, 12, 15, 15),
            Block.createCuboidShape(4, 15, 14, 12, 26, 15),
            Block.createCuboidShape(4, 5, 1, 12, 15, 2),
            Block.createCuboidShape(4, 15, 1, 12, 26, 2),
            Block.createCuboidShape(1, 5, 4, 2, 15, 12),
            Block.createCuboidShape(1, 15, 4, 2, 26, 12),
            Block.createCuboidShape(14, 18, 4, 15, 26, 12),
            Block.createCuboidShape(14, 5, 9, 15, 17, 12),
            Block.createCuboidShape(14, 14, 4, 15, 17, 8),
            Block.createCuboidShape(14, 5, 4, 15, 13, 8)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    //make outline using VoxelShapes
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()); //make block face the player
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation)
    {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror)
    {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager .Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    //block entity :)

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved)
    {
        if (state.getBlock() != newState.getBlock())
        {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CrucibleBlockEntity)
            {
                ItemScatterer.spawn(world, pos, (CrucibleBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    //open gui on right click
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        if (!world.isClient)
        {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null)
            {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new CrucibleBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return checkType(type, ModBlockEntities.CRUCIBLE, CrucibleBlockEntity::tick);
    }
}
