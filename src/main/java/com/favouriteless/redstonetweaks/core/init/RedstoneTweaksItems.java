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

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RedstoneTweaksItems {
    
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "redstonetweaks");

    public static final RegistryObject<Item> ANDESITE_BUTTON = ITEMS.register("andesite_button", () -> new BlockItem(RedstoneTweaksBlocks.ANDESITE_BUTTON.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final RegistryObject<Item> DIORITE_BUTTON = ITEMS.register("diorite_button", () -> new BlockItem(RedstoneTweaksBlocks.DIORITE_BUTTON.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final RegistryObject<Item> GRANITE_BUTTON = ITEMS.register("granite_button", () -> new BlockItem(RedstoneTweaksBlocks.GRANITE_BUTTON.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final RegistryObject<Item> ANDESITE_PRESSURE_PLATE = ITEMS.register("andesite_pressure_plate", () -> new BlockItem(RedstoneTweaksBlocks.ANDESITE_PRESSURE_PLATE.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final RegistryObject<Item> DIORITE_PRESSURE_PLATE = ITEMS.register("diorite_pressure_plate", () -> new BlockItem(RedstoneTweaksBlocks.DIORITE_PRESSURE_PLATE.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final RegistryObject<Item> GRANITE_PRESSURE_PLATE = ITEMS.register("granite_pressure_plate", () -> new BlockItem(RedstoneTweaksBlocks.GRANITE_PRESSURE_PLATE.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final RegistryObject<Item> TORCH_LEVER = ITEMS.register("torch_lever", () -> new BlockItem(RedstoneTweaksBlocks.TORCH_LEVER.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final RegistryObject<Item> BOOKSHELF_SWITCH = ITEMS.register("bookshelf_switch", () -> new BlockItem(RedstoneTweaksBlocks.BOOKSHELF_SWITCH.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

}
