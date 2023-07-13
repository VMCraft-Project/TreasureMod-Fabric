package com.vtmcraft.treasure;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VTMTreasure implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("vtmtreasuremod");

    public static final String MOD_ID = "vtmtreasure";

    @Override
    public void onInitialize() {
        VTMTreasureItems.registerItems();
        VTMTreasureSoundEvents.registerSoundEvents();

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            content.addAfter(Items.TNT_MINECART, VTMTreasureItems.TRUMPET);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
            content.addAfter(Items.MILK_BUCKET, VTMTreasureItems.STRAWBERRY_MILK);
        });

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, table, setter) -> {
            if (id.getNamespace().equals("minecraft") & id.getPath().startsWith("chests/")){
                LootPool.Builder builder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(0.6f))
                        .with(ItemEntry.builder(VTMTreasureItems.TRUMPET));

                table.pool(builder);
            }
        });
    }
}