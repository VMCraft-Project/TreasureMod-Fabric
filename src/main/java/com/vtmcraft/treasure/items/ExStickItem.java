package com.vtmcraft.treasure.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Rarity;

import java.util.Objects;

public class ExStickItem extends Item {
    public ExStickItem() {
        super(new FabricItemSettings().maxCount(1).fireproof().rarity(Rarity.EPIC));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Objects.requireNonNull(context.getPlayer()).getItemCooldownManager().set(this, 150);

        var world = context.getWorld();
        if (!(world instanceof ServerWorld)) {
            return ActionResult.SUCCESS;
        }

        EntityType.LIGHTNING_BOLT.spawn((ServerWorld) world, context.getBlockPos(), SpawnReason.NATURAL);
        return ActionResult.CONSUME;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
