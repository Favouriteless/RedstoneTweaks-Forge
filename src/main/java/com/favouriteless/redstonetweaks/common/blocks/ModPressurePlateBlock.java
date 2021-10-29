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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BasePressurePlateBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;

import java.util.Iterator;
import java.util.List;

public class ModPressurePlateBlock extends BasePressurePlateBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private final Sensitivity sensitivity;

    public ModPressurePlateBlock(Sensitivity sensitivity, BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
        this.sensitivity = sensitivity;
    }

    protected int getSignalForState(BlockState state) {
        return state.getValue(POWERED) ? 15 : 0;
    }

        protected BlockState setSignalForState(BlockState state, int rsOut) {
        return state.setValue(POWERED, Boolean.valueOf(rsOut > 0));
    }

    protected void playOnSound(LevelAccessor world, BlockPos pos) {
        if (this.material != Material.WOOD && this.material != Material.NETHER_WOOD) {
            world.playSound((Player)null, pos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 0.3F, 0.6F);
        } else {
            world.playSound((Player)null, pos, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 0.3F, 0.8F);
        }

    }

    protected void playOffSound(LevelAccessor world, BlockPos pos) {
        if (this.material != Material.WOOD && this.material != Material.NETHER_WOOD) {
            world.playSound(null, pos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS, 0.3F, 0.5F);
        } else {
            world.playSound(null, pos, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS, 0.3F, 0.7F);
        }

    }

    protected int getSignalStrength(Level p_55264_, BlockPos pos) {
        AABB aabb = TOUCH_AABB.move(pos);
        List<? extends Entity> list;
        switch(this.sensitivity) {
            case EVERYTHING:
                list = p_55264_.getEntities((Entity)null, aabb);
                break;
            case MOBS:
                list = p_55264_.getEntitiesOfClass(LivingEntity.class, aabb);
                break;
            default:
                return 0;
        }

        if (!list.isEmpty()) {
            for(Entity entity : list) {
                if (!entity.isIgnoringBlockTriggers()) {
                    return 15;
                }
            }
        }

        return 0;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55262_) {
        p_55262_.add(POWERED);
    }

    public enum Sensitivity {
        EVERYTHING,
        MOBS;
    }
}