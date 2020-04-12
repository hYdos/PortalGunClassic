package me.hydos.portalgunfabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class Packets implements ClientModInitializer {

    public static final Identifier S2C_PLAY_CHANGE_PORTAL_TYPE = new Identifier("portalgunfabric", "changeportaltype");

    @Override
    public void onInitializeClient(){
        ClientSidePacketRegistry.INSTANCE.register(S2C_PLAY_CHANGE_PORTAL_TYPE,
                (packetContext, attachedData) -> packetContext.getTaskQueue().execute(() -> {
                    MinecraftClient client = MinecraftClient.getInstance();

                    assert client.player != null;
//                    PortalGunItem item = (PortalGunItem) client.player.inventory.getMainHandStack();
//                    if(item.portalType == PortalType.BLUE){
//                        item.portalType = PortalType.ORANGE;
//                    }else{
//                        item.portalType = PortalType.BLUE;
//                    }
//
                }));
    }


}
