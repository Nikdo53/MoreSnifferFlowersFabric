package net.nikdo53.moresnifferflowers.networking;

import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;

public class ModPacketHandler {
    private static final String PROTOCOL_VERSION = "2";
    public static final SimpleChannel CHANNEL = new SimpleChannel(MoreSnifferFlowers.prefix("channel"));

    @SuppressWarnings({"UnusedAssignment"})
    public static void init() {
        CHANNEL.initServerListener();
        EnvExecutor.runWhenOn(EnvType.CLIENT, () -> CHANNEL::initClientListener);
        int id = 0;

        CHANNEL.registerS2CPacket(CorruptedSludgePacket.class, id++);
        CHANNEL.registerC2SPacket(DyespriaModePacket.class, id++);
        CHANNEL.registerS2CPacket(DyespriaDisplayModeChangePacket.class, id++);
    }
}
