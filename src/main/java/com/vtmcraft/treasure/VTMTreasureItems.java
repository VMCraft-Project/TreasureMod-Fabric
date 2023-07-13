package com.vtmcraft.treasure;

import com.vtmcraft.treasure.items.StrawberryMilk;
import com.vtmcraft.treasure.items.Trumpet;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.vtmcraft.treasure.VTMTreasure.MOD_ID;

public class VTMTreasureItems {
    public static final Trumpet TRUMPET = new Trumpet();
    public static final StrawberryMilk STRAWBERRY_MILK = new StrawberryMilk();

    public static void registerItems(){
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "trumpet"), TRUMPET);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "strawberry_milk"), STRAWBERRY_MILK);
    }
}
