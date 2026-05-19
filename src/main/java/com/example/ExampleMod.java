package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class ExampleMod implements ModInitializer {

    @Override
    public void onInitialize() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {

            if (!(entity instanceof ServerPlayerEntity victim)) return;

            if (source.getAttacker() instanceof ServerPlayerEntity killer) {

                ItemStack weapon = killer.getMainHandStack();

                if (weapon.getName().getString().equals("Netherite Spear")) {
                    victim.networkHandler.disconnect(
                        Text.literal("Killed by the Netherite Spear")
                    );
                }
            }
        });
    }
}
