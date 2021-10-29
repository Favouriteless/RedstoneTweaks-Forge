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
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;


public class TorchLeverBlock extends LeverBlock {

    public TorchLeverBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    public static final VoxelShape FLOOR_SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
    public static final VoxelShape CEILING_SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
    public static final VoxelShape WALL_SHAPE_NORTH = Block.box(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D);
    public static final VoxelShape WALL_SHAPE_SOUTH = Block.box(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D);
    public static final VoxelShape WALL_SHAPE_WEST = Block.box(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D);
    public static final VoxelShape WALL_SHAPE_EAST = Block.box(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D);


    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction dir = getConnectedDirection(state);
        return dir != Direction.DOWN && canAttach(world, pos, dir.getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACE)) {
            case FLOOR -> FLOOR_SHAPE;
            case WALL -> switch (state.getValue(FACING)) {
                case EAST -> WALL_SHAPE_EAST;
                case WEST -> WALL_SHAPE_WEST;
                case SOUTH -> WALL_SHAPE_SOUTH;
                default -> WALL_SHAPE_NORTH;
            };
            case CEILING -> CEILING_SHAPE;
        };
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
        Direction direction = state.getValue(FACING);
        double d = (double)pos.getX() + 0.5D;
        double e = (double)pos.getY() + 0.7D;
        double f = (double)pos.getZ() + 0.5D;

        if (state.getValue(FACE) == AttachFace.WALL) {
            Direction direction2 = direction.getOpposite();
            if(state.getValue(POWERED)) {
                world.addParticle(ParticleTypes.SMOKE, d + 0.125D * (double) direction2.getStepX(), e + 0.04D, f + 0.125D * (double) direction2.getStepZ(), 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, d + 0.125D * (double) direction2.getStepX(), e + 0.04D, f + 0.125D * (double) direction2.getStepZ(), 0.0D, 0.0D, 0.0D);
            } else {
                world.addParticle(ParticleTypes.SMOKE, d + 0.27D * (double) direction2.getStepX(), e + 0.22D, f + 0.27D * (double) direction2.getStepZ(), 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, d + 0.27D * (double) direction2.getStepX(), e + 0.22D, f + 0.27D * (double) direction2.getStepZ(), 0.0D, 0.0D, 0.0D);
            }
        } else {
            if(state.getValue(POWERED)) {
                world.addParticle(ParticleTypes.SMOKE, d, e, f + 0.25D, 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, d, e, f + 0.25D, 0.0D, 0.0D, 0.0D);
            } else {
                world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, d, e, f, 0.0D, 0.0D, 0.0D);
            }
        }

        if (state.getValue(POWERED) && random.nextFloat() < 0.25F) {
            direction = state.getValue(FACING).getOpposite();
            Direction direction2 = getConnectedDirection(state).getOpposite();
            d = (double)pos.getX() + 0.5D + 0.1D * (double)direction.getStepX() + 0.2D * (double)direction2.getStepX();
            e = (double)pos.getY() + 0.5D + 0.1D * (double)direction.getStepY() + 0.2D * (double)direction2.getStepY();
            f = (double)pos.getZ() + 0.5D + 0.1D * (double)direction.getStepZ() + 0.2D * (double)direction2.getStepZ();
            world.addParticle(new DustParticleOptions(DustParticleOptions.REDSTONE_PARTICLE_COLOR, 0.5F), d, e, f, 0.0D, 0.0D, 0.0D);
        }
    }

}
