package com.vtmcraft.treasure.items;

import com.vtmcraft.treasure.VTMTreasureSoundEvents;
import com.vtmcraft.treasure.models.TrumpetRecord;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Trumpet extends Item {
    private static final List<TrumpetRecord> TRUMPET_RECORDS = Arrays.stream(new TrumpetRecord[] {
            new TrumpetRecord(140, VTMTreasureSoundEvents.ZAKO),
            new TrumpetRecord(10, VTMTreasureSoundEvents.KIMO),
            new TrumpetRecord(10, VTMTreasureSoundEvents.SABI),
    }).toList();

    private int _lastTrumpetRecordIndex = -1;

    public Trumpet() {
        super(new FabricItemSettings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) return TypedActionResult.success(user.getStackInHand(hand));

        var tempTrumpetRecords = new ArrayList<>(TRUMPET_RECORDS);
        if (_lastTrumpetRecordIndex != -1) tempTrumpetRecords.remove(_lastTrumpetRecordIndex);

        var playTrumpetRecordIndex = new Random().nextInt(tempTrumpetRecords.size());
        TrumpetRecord trumpetRecord = tempTrumpetRecords.get(playTrumpetRecordIndex);

        _lastTrumpetRecordIndex = TRUMPET_RECORDS.indexOf(trumpetRecord);

        world.playSound(null, user.getBlockPos(), trumpetRecord.Sound(), SoundCategory.PLAYERS, 1.0f, 1.0f);
        user.getItemCooldownManager().set(this, trumpetRecord.CoolDownTime());

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.vtmtreasure.trumpet.tooltip"));
    }
}
