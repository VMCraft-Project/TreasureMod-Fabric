package com.vtmcraft.treasure;

import com.vtmcraft.treasure.commands.arguent.TrumpetTypeArgumentType;
import com.vtmcraft.treasure.items.Trumpet;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class VTMTreasureCommands {
    public static void init() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) ->
                dispatcher.register(literal("getTrumpet").requires(source -> source.hasPermissionLevel(2))
                        .then(argument("targets", EntityArgumentType.players())
                                .then(argument("id", new TrumpetTypeArgumentType()).executes(context -> {
                                    var players = EntityArgumentType.getPlayers(context, "targets");
                                    var id = TrumpetTypeArgumentType.getIdentifier(context, "id");

                                    players.forEach(player -> {
                                        var itemStack = new ItemStack(VTMTreasureItems.TRUMPET);
                                        itemStack.getOrCreateNbt().putString(Trumpet.TRUMPET_TYPE_NBT_KEY, id.toString());

                                        player.getInventory().insertStack(itemStack);
                                        player.getWorld()
                                                .playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2f, ((player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.7f + 1.0f) * 2.0f);
                                    });

                                    return 1;
                                }))
                        )
                )));
    }
}
