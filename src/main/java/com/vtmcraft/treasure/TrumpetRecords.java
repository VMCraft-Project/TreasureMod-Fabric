package com.vtmcraft.treasure;

import com.vtmcraft.treasure.models.TrumpetRecord;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;


import java.util.HashMap;
import java.util.Set;

import static com.vtmcraft.treasure.VTMTreasure.MOD_ID;

public class TrumpetRecords {
    private static final HashMap<Identifier, TrumpetRecord> TRUMPET_TYPE = new HashMap<>();

    public static void init() {
        addTrumpetType(new Identifier(MOD_ID, "zako"), 140, VTMTreasureSoundEvents.ZAKO);
        addTrumpetType(new Identifier(MOD_ID, "sabi"), 10, VTMTreasureSoundEvents.SABI);
        addTrumpetType(new Identifier(MOD_ID, "kimo"), 10, VTMTreasureSoundEvents.KIMO);
    }

    private static void addTrumpetType(Identifier id, int coolDownTime, SoundEvent soundEvent) {
        addTrumpetType(id, new TrumpetRecord(coolDownTime, soundEvent));
    }

    private static void addTrumpetType(Identifier id, TrumpetRecord trumpetRecord) {
        TRUMPET_TYPE.put(id, trumpetRecord);
    }

    public static TrumpetRecord getTrumpetType(Identifier identifier) {
        return TRUMPET_TYPE.get(identifier);
    }

    public static Set<Identifier> getIds() {
        return TRUMPET_TYPE.keySet();
    }
}
