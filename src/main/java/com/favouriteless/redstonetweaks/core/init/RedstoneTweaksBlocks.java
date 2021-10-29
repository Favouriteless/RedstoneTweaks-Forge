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

package com.favouriteless.redstonetweaks.core.init;

import com.favouriteless.redstonetweaks.common.blocks.*;
import com.favouriteless.redstonetweaks.common.blocks.ModPressurePlateBlock.Sensitivity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RedstoneTweaksBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "redstonetweaks");

    public static final RegistryObject<Block> ANDESITE_BUTTON = BLOCKS.register("andesite_button", () -> new ModButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F)));
    public static final RegistryObject<Block> DIORITE_BUTTON = BLOCKS.register("diorite_button", () -> new ModButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F)));
    public static final RegistryObject<Block> GRANITE_BUTTON = BLOCKS.register("granite_button", () -> new ModButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F)));
    public static final RegistryObject<Block> ANDESITE_PRESSURE_PLATE = BLOCKS.register("andesite_pressure_plate", () -> new ModPressurePlateBlock(Sensitivity.MOBS, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().noCollission().strength(0.5F)));
    public static final RegistryObject<Block> DIORITE_PRESSURE_PLATE = BLOCKS.register("diorite_pressure_plate", () -> new ModPressurePlateBlock(Sensitivity.MOBS, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().noCollission().strength(0.5F)));
    public static final RegistryObject<Block> GRANITE_PRESSURE_PLATE = BLOCKS.register("granite_pressure_plate", () -> new ModPressurePlateBlock(Sensitivity.MOBS, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().noCollission().strength(0.5F)));
    public static final RegistryObject<Block> TORCH_LEVER = BLOCKS.register("torch_lever", () -> new TorchLeverBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> 14).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BOOKSHELF_SWITCH = BLOCKS.register("bookshelf_switch", () -> new BookshelfSwitchBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.5F).sound(SoundType.WOOD).noOcclusion()));

}
