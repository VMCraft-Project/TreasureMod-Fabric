package com.vtmcraft.treasure;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetNbtLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.nbt.NbtCompound;

import static com.vtmcraft.treasure.items.TrumpetItem.TRUMPET_TYPE_NBT_KEY;

public class VTMTreasure implements ModInitializer {
    // public static final Logger LOGGER = LoggerFactory.getLogger("vtmtreasuremod");

    public static final String MOD_ID = "vtmtreasure";

    @SuppressWarnings({"UnstableApiUsage", "deprecation"})
    @Override
    public void onInitialize() {
        VTMTreasureItems.registerItems();
        VTMTreasureSoundEvents.registerSoundEvents();
        TrumpetRecords.init();
        VTMTreasureCommands.init();

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> TrumpetRecords.getIds().forEach(identifier -> {
            var stack = new ItemStack(VTMTreasureItems.TRUMPET_ITEM);
            stack.getOrCreateNbt().putString(TRUMPET_TYPE_NBT_KEY, identifier.toString());

            content.addAfter(Items.TNT_MINECART, stack);
        }));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> content.addAfter(Items.MILK_BUCKET, VTMTreasureItems.STRAWBERRY_MILK_ITEM));

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, table, setter) -> {
            if (id.getNamespace().equals("minecraft") & id.getPath().startsWith("chests/")){
                LootPool.Builder builder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(0.8f));

                TrumpetRecords.getIds().forEach(identifier -> {
                    var stack = new ItemStack(VTMTreasureItems.TRUMPET_ITEM);
                    stack.getOrCreateNbt().putString(TRUMPET_TYPE_NBT_KEY, identifier.toString());

                    var nbt = new NbtCompound();
                    nbt.putString(TRUMPET_TYPE_NBT_KEY, identifier.toString());

                    builder.with(ItemEntry.builder(VTMTreasureItems.TRUMPET_ITEM).apply(SetNbtLootFunction.builder(nbt)));
                });

                table.pool(builder);
            }
        });
    }
}