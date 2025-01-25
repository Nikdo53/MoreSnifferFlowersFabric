package net.nikdo53.moresnifferflowers.networking;

import net.abraxator.moresnifferflowers.components.DyespriaMode;
import net.abraxator.moresnifferflowers.items.DyespriaItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record DyespriaDisplayModeChangePacket(int dyespriaModeId) {
    public DyespriaDisplayModeChangePacket(FriendlyByteBuf buf) {
        this(buf.readByte());
    }
    
    public void encode(FriendlyByteBuf buf) {
        buf.writeByte(dyespriaModeId());
    }
    
    public class Handler {
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
}
