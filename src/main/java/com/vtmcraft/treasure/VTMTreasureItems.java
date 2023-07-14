package com.vtmcraft.treasure;

import com.vtmcraft.treasure.items.ExStickItem;
import com.vtmcraft.treasure.items.StrawberryMilkItem;
import com.vtmcraft.treasure.items.TrumpetItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.vtmcraft.treasure.VTMTreasure.MOD_ID;

public class VTMTreasureItems {
    public static final TrumpetItem TRUMPET_ITEM = new TrumpetItem();
    public static final StrawberryMilkItem STRAWBERRY_MILK_ITEM = new StrawberryMilkItem();
    public static final ExStickItem EX_STICK_ITEM = new ExStickItem();

    public static void registerItems(){
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "trumpet"), TRUMPET_ITEM);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "strawberry_milk"), STRAWBERRY_MILK_ITEM);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "ex_stick"), EX_STICK_ITEM);
    }
}
