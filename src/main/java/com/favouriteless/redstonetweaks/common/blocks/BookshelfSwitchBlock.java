/*
 * Copyright (c) 2021. Favouriteless
 * RedstoneTweaks-Forge, a minecraft mod.
 * GNU GPLv3 License
 *
 *     This file is part of RedstoneTweaks-Forge.
 *
 *     RedstoneTweaks-Forge is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     RedstoneTweaks-Forge is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with RedstoneTweaks-Forge.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.favouriteless.redstonetweaks.common.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import javax.swing.text.html.BlockView;

public class BookshelfSwitchBlock extends HorizontalDirectionalBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public BookshelfSwitchBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter blockGetter, BlockPos pos, Direction direction) {
        return state.getValue(POWERED) ? 15 : 0;
    }

    
    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (!moved && !state.is(newState.getBlock())) {
            if (state.getValue(POWERED)) {
                this.updateNeighbors(state, world, pos);
            }

            super.onRemove(state, world, pos, newState, moved);
        }
    }

    private void updateNeighbors(BlockState state, Level world, BlockPos pos) {
        world.updateNeighborsAt(pos, this);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockState blockState;
        if (world.isClientSide) {
            blockState = state.cycle(POWERED);
            if (blockState.getValue(POWERED)) {
                spawnParticles(blockState, world, pos, 1.0F);
            }

            return InteractionResult.SUCCESS;
        } else {
            blockState = this.togglePower(state, world, pos);
            float f = blockState.getValue(POWERED) ? 0.6F : 0.5F;
            world.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, f);
            world.gameEvent(player, blockState.getValue(POWERED) ? GameEvent.BLOCK_SWITCH : GameEvent.BLOCK_UNSWITCH, pos);
            return InteractionResult.CONSUME;
        }
    }

    private static void spawnParticles(BlockState state, LevelAccessor world, BlockPos pos, float alpha) {
/*        Direction direction = ((Direction)state.getValue(FACING)).getOpposite();
        Direction direction2 = getDirection(state).getOpposite();
        double d = (double)pos.getX() + 0.5D + 0.1D * (double)direction.getOffsetX() + 0.2D * (double)direction2.getOffsetX();
        double e = (double)pos.getY() + 0.5D + 0.1D * (double)direction.getOffsetY() + 0.2D * (double)direction2.getOffsetY();
        double f = (double)pos.getZ() + 0.5D + 0.1D * (double)direction.getOffsetZ() + 0.2D * (double)direction2.getOffsetZ();
        world.addParticle(new DustParticleEffect(DustParticleEffect.RED, alpha), d, e, f, 0.0D, 0.0D, 0.0D);*/
    }

    public BlockState togglePower(BlockState state, Level world, BlockPos pos) {
        state = state.cycle(POWERED);
        world.setBlockAndUpdate(pos, state);
        this.updateNeighbors(state, world, pos);
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getNearestLookingDirection().getOpposite());
    }
}
