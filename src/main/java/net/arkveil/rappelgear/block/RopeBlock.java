package net.arkveil.rappelgear.block;

import net.arkveil.rappelgear.RappelGearMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class RopeBlock extends FacingBlock{

    protected static final VoxelShape CENTER_SHAPE  = Block.createCuboidShape(0.375f, 0f, 0.375f, 0.625f, 1.0f, 0.625f);
    protected static final VoxelShape EAST_SHAPE    = Block.createCuboidShape(0.375f, 0f, 0.75f, 0.625f, 1.0f, 1.0f);
    protected static final VoxelShape WEST_SHAPE    = Block.createCuboidShape(0.375f, 0f, 0.0f, 0.625f, 1.0f, 0.25f);
    protected static final VoxelShape SOUTH_SHAPE   = Block.createCuboidShape(0.0f, 0f, 0.375f, 0.25f, 1.0f, 0.625f);
    protected static final VoxelShape NORTH_SHAPE   = Block.createCuboidShape(0.625f, 0f, 0.375f, 1.0f, 1.0f, 0.625f);

    public RopeBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.UP));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context)
    {
        switch (state.get(FACING)) {
            case NORTH: {
                RappelGearMod.LOGGER.info("Facing: North");
                return NORTH_SHAPE;
            }
            case SOUTH: {
                RappelGearMod.LOGGER.info("Facing: South");
                return SOUTH_SHAPE;
            }
            case WEST: {
                RappelGearMod.LOGGER.info("Facing: West");
                return WEST_SHAPE;
            }
            case EAST: {
                RappelGearMod.LOGGER.info("Facing: East");
                return EAST_SHAPE;
            }
        }
        RappelGearMod.LOGGER.info("Facing: Center");
        return CENTER_SHAPE;
    }

    @Override
    protected void appendProperties (StateManager.Builder<Block, BlockState> stateManager)
    {
        stateManager.add((Properties.FACING));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        return (BlockState)this.getDefaultState().with(Properties.FACING, ctx.getPlayerFacing().getOpposite());
    }




}
