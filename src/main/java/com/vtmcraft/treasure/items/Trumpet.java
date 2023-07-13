package com.vtmcraft.treasure.items;

import com.vtmcraft.treasure.TrumpetRecords;
import com.vtmcraft.treasure.models.TrumpetRecord;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.vtmcraft.treasure.VTMTreasure.MOD_ID;

public class Trumpet extends Item {
    public static final String TRUMPET_TYPE_NBT_KEY = "trumpet_type";

    public Trumpet() {
        super(new FabricItemSettings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        if (world.isClient) return TypedActionResult.success(stack);

        var trumpetRecord = getTrumpetType(stack);
        if (trumpetRecord == null) return TypedActionResult.fail(stack);

        world.playSound(null, user.getBlockPos(), trumpetRecord.Sound(), SoundCategory.PLAYERS, 1.0f, 1.0f);
        user.getItemCooldownManager().set(this, trumpetRecord.CoolDownTime());

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        var key = getTrumpetTypeKey(stack);
        if (key != null) {
            tooltip.add(Text.translatable(MOD_ID + ".trumpet." + key.getNamespace() + "." + key.getPath()).formatted(Formatting.DARK_GRAY));
        }
    }

    private Identifier getTrumpetTypeKey(ItemStack stack) {
        var nbt = stack.getNbt();
        if (nbt != null && nbt.contains(TRUMPET_TYPE_NBT_KEY)) {
            return new Identifier(nbt.getString(TRUMPET_TYPE_NBT_KEY));
        }

        return null;
    }

    private TrumpetRecord getTrumpetType(ItemStack stack) {
        return TrumpetRecords.getTrumpetType(getTrumpetTypeKey(stack));
    }
}
