package net.nikdo53.moresnifferflowers.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerPlayer;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import org.intellij.lang.annotations.Identifier;

public class ModNetworkingConstants {
    public static final ResourceLocation CORRUPTED_SLUDGE_PACKET_ID = new ResourceLocation(MoreSnifferFlowers.MOD_ID, "corrupted_sludge");

    public static void registerC2SPackets() {
    }

    public static void registerS2CPackets() {
        ServerPlayNetworking.registerGlobalReceiver(CORRUPTED_SLUDGE_PACKET_ID, CorruptedSludgePacket::)

    }
}
