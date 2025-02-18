package net.nikdo53.moresnifferflowers.client;

import io.github.fabricators_of_create.porting_lib.event.client.MouseInputEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.nikdo53.moresnifferflowers.init.ModItems;
import net.nikdo53.moresnifferflowers.networking.DyespriaModePacket;
import net.nikdo53.moresnifferflowers.networking.ModPacketHandler;

@Environment(EnvType.CLIENT)
public class ClientEvents {

    public static void init() {
        MouseInputEvents.BEFORE_SCROLL.register(ClientEvents::onInputMouseScrolling);
    }

    private static boolean onInputMouseScrolling(double v, double v1) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player.isCrouching() && v1 != 0 && player.getMainHandItem().is(ModItems.DYESPRIA.get())) {
            ModPacketHandler.CHANNEL.sendToServer(new DyespriaModePacket((int) v1));
            return true;
        }
        return false;
    }
}

