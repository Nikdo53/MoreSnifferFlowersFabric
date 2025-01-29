package net.nikdo53.moresnifferflowers.networking;

import me.pepperbell.simplenetworking.C2SPacket;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.nikdo53.moresnifferflowers.items.DyespriaItem;
import net.minecraft.network.FriendlyByteBuf;

import java.util.concurrent.Executor;

public class DyespriaModePacket implements C2SPacket {

    private final int amount;

    public DyespriaModePacket(int amount) {
        this.amount = amount;
    }

    public DyespriaModePacket(FriendlyByteBuf buf){
        this.amount = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(amount);
    }

    @Override
    public void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl listener, PacketSender responseSender, SimpleChannel channel) {
        DyespriaModePacket.Handler.onMessage(this, server);

    }

    public static class Handler {
        public static boolean onMessage(DyespriaModePacket message, Executor executor) {
            executor.execute(() -> {
                var player = Minecraft.getInstance().player;
                assert player != null;
                var stack = player.getMainHandItem();
                if (stack.getItem() instanceof DyespriaItem dyespriaItem) {
                    dyespriaItem.changeMode(player, stack, message.amount);
                }
            });
            return false;
        }
    }

   /* public static void handle(DyespriaModePacket packet, Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> {
            var player = ctx.getSender();
            var stack = player.getMainHandItem();
            if(stack.getItem() instanceof DyespriaItem dyespriaItem) {
                dyespriaItem.changeMode(player, stack, packet.amount);
            }
        });
    }

    */
}
