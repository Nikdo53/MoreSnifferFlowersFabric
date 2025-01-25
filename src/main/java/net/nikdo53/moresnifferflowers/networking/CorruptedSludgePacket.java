package net.nikdo53.moresnifferflowers.networking;

import me.pepperbell.simplenetworking.S2CPacket;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.concurrent.Executor;

public record CorruptedSludgePacket(Vector3f start, Vector3f target, Vector3f direction) implements S2CPacket {
    public static void receive(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, PacketByteBufs buf, PacketSender responseSender) {

    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeVector3f(start);
        buf.writeVector3f(target);
        buf.writeVector3f(direction);

        buf.readVector3f();
    }

    @Override
    public void handle(Minecraft client, ClientPacketListener listener, PacketSender responseSender, SimpleChannel channel) {
        Handler.onMessage(this, client);
    }

    public static class Handler {

        public static boolean onMessage(CorruptedSludgePacket message, Executor executor) {
            executor.execute(new Runnable() {
                                 @Override
                                 public void run() {
                                     ClientLevel level = Minecraft.getInstance().level;

                                     assert level != null;
                                     if (level.isClientSide()) {
                                         float distance = message.start.distance(message.target);

                                         for (int i = 0; i < 15; i++) {
                                             double progress = (double) i / 15;
                                             Vector3f pos = new Vector3f(message.start).add(new Vector3f(message.direction).mul((float) (distance * progress)));
                                             level.addParticle(new DustParticleOptions(Vec3.fromRGB24(0x0443248).toVector3f(), 1.0F), pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
                                         }
                                     }
                                 }
                             });
                    return true;
        }
    }

   /* public class Handler {

        public static void handle(CorruptedSludgePacket packet, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handlePacket(packet, context));
            });
            context.get().setPacketHandled(true);
        }
        
        public static boolean handlePacket(CorruptedSludgePacket packet, Supplier<NetworkEvent.Context> context) {
            NetworkEvent.Context ctx = context.get();
            ctx.enqueueWork(() -> {
                var level = ctx.getSender().level();
                if (level.isClientSide()) {
                    float distance = packet.start.distance(packet.target);

                    for (int i = 0; i < 15; i++) {
                        double progress = (double) i / 15;
                        Vector3f pos = new Vector3f(packet.start).add(new Vector3f(packet.direction).mul((float) (distance * progress)));
                        level.addParticle(new DustParticleOptions(Vec3.fromRGB24(0x0443248).toVector3f(), 1.0F), pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
                    }
                }
            });

            ctx.setPacketHandled(true);
            return true;
        }   
    }

    */
}
