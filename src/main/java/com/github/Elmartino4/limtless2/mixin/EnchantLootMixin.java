package com.github.Elmartino4.limitless2.mixin;

import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.math.MathHelper;

import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

@Mixin(EnchantRandomlyLootFunction.class)
public class EnchantLootMixin {
	@ModifyVariable(method = "addEnchantmentToStack", ordinal = 0, at = @At("STORE"))
	private static int changeEnchantLvl(int prev, ItemStack stack, Enchantment enchantment, Random random) {
    int i = Math.min(
			enchantment.getMaxLevel(),
			(int)Math.floor(
				Math.log(
					1d/(1-MathHelper.nextDouble(random, 0, 1))
				) * 7 + 1
			)
		);

		System.out.println("[fabric example mod] EnchantLootMixin");

		return i;
  }
}