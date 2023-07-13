package com.vtmcraft.treasure.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StrawberryMilk extends Item {
    private static final int MAX_USE_TIME = 32;
    private static final int EFFECT_TIME = 1000;

    public StrawberryMilk() {
        super(new FabricItemSettings().maxCount(1));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (user instanceof PlayerEntity && !((PlayerEntity) user).getAbilities().creativeMode) {
            stack.decrement(1);
        }

        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, EFFECT_TIME, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, EFFECT_TIME, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, EFFECT_TIME, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, EFFECT_TIME, 1));
        }

        return stack.isEmpty() ? ItemStack.EMPTY : stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return MAX_USE_TIME;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.vtmtreasure.strawberry_milk.tooltip").formatted(Formatting.LIGHT_PURPLE));
    }
}
