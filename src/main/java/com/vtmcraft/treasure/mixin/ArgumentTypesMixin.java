package com.vtmcraft.treasure.mixin;

import com.mojang.brigadier.arguments.ArgumentType;
import com.vtmcraft.treasure.commands.arguent.TrumpetTypeArgumentType;
import net.minecraft.command.argument.ArgumentTypes;
import net.minecraft.command.argument.serialize.ArgumentSerializer;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.vtmcraft.treasure.VTMTreasure.MOD_ID;

@Mixin(ArgumentTypes.class)
public abstract class ArgumentTypesMixin {
    @SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
    @Invoker("register")
    private static <A extends ArgumentType<?>, T extends ArgumentSerializer.ArgumentTypeProperties<A>> ArgumentSerializer<A, T> register(Registry<ArgumentSerializer<?, ?>> registry, String id, Class<? extends A> clazz, ArgumentSerializer<A, T> serializer) {
        throw new AssertionError("Nope.");
    }

    @Inject(method = "register(Lnet/minecraft/registry/Registry;)Lnet/minecraft/command/argument/serialize/ArgumentSerializer;", at = @At("RETURN"))
    private static void registerInject(Registry<ArgumentSerializer<?, ?>> registry, CallbackInfoReturnable<ArgumentSerializer<?, ?>> cir) {
        register(registry, MOD_ID + ":trumpet_type", TrumpetTypeArgumentType.class, ConstantArgumentSerializer.of(TrumpetTypeArgumentType::identifier));
    }
}
