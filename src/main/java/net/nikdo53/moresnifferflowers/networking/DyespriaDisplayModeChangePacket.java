package net.nikdo53.moresnifferflowers.networking;

import me.pepperbell.simplenetworking.S2CPacket;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.nikdo53.moresnifferflowers.components.DyespriaMode;
import net.nikdo53.moresnifferflowers.items.DyespriaItem;
import net.minecraft.network.FriendlyByteBuf;

import java.util.concurrent.Executor;

public record DyespriaDisplayModeChangePacket(int dyespriaModeId) implements S2CPacket {
    public DyespriaDisplayModeChangePacket(FriendlyByteBuf buf) {
        this(buf.readByte());
    }
    
    public void encode(FriendlyByteBuf buf) {
        buf.writeByte(dyespriaModeId());
    }

    @Override
    public void handle(Minecraft client, ClientPacketListener listener, PacketSender responseSender, SimpleChannel channel) {
        Handler.onMessage(this, client);
    }

    public static class Handler {

        public static boolean onMessage(DyespriaDisplayModeChangePacket message, Executor ctx) {
            ctx.execute(() ->
                    ctx..displayClientMessage(DyespriaItem.getCurrentModeComponent(DyespriaMode.byIndex(message.dyespriaModeId)), true);
            return true;
        }
    }

   /* public class Handler {
        public static void handle(DyespriaDisplayModeChangePacket packet, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handlePacket(packet, context));
            });
            context.get().setPacketHandled(true);
        }
        
        public static boolean handlePacket(DyespriaDisplayModeChangePacket packet, Supplier<NetworkEvent.Context> context) {
            NetworkEvent.Context ctx = context.get();
            ctx.enqueueWork(() -> {
                ctx.getSender().displayClientMessage(DyespriaItem.getCurrentModeComponent(DyespriaMode.byIndex(packet.dyespriaModeId)), true);
            });

            ctx.setPacketHandled(true);
            return true;
        }   
    }

    */
}
