package com.vtmcraft.treasure;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static com.vtmcraft.treasure.VTMTreasure.MOD_ID;

public class VTMTreasureSoundEvents {
    public static final SoundEvent SABI = SoundEvent.of(new Identifier(MOD_ID, "sabi"));
    public static final SoundEvent ZAKO = SoundEvent.of(new Identifier(MOD_ID, "zako"));
    public static final SoundEvent KIMO = SoundEvent.of(new Identifier(MOD_ID, "kimo"));

    public static void registerSoundEvents(){
        Registry.register(Registries.SOUND_EVENT, new Identifier(MOD_ID, "sabi"), SABI);
        Registry.register(Registries.SOUND_EVENT, new Identifier(MOD_ID, "zako"), ZAKO);
        Registry.register(Registries.SOUND_EVENT, new Identifier(MOD_ID, "kimo"), KIMO);
    }
}
