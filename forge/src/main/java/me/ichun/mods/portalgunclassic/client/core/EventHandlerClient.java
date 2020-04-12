package me.ichun.mods.portalgunclassic.client.core;

import me.ichun.mods.portalgunclassic.client.portal.PortalStatus;
import me.ichun.mods.portalgunclassic.common.PortalGunClassic;
import me.ichun.mods.portalgunclassic.common.packet.PacketSwapType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.lwjgl.input.Keyboard;

public class EventHandlerClient
{
    public static final ResourceLocation txLEmpty = new ResourceLocation("portalgunclassic", "textures/overlay/lempty.png");
    public static final ResourceLocation txLFull = new ResourceLocation("portalgunclassic", "textures/overlay/lfull.png");
    public static final ResourceLocation txREmpty = new ResourceLocation("portalgunclassic", "textures/overlay/rempty.png");
    public static final ResourceLocation txRFull = new ResourceLocation("portalgunclassic", "textures/overlay/rfull.png");


    public KeyBinding keySwitch = new KeyBinding("key.portalgunclassic.switch", Keyboard.KEY_G, "key.categories.portalgun");
    public KeyBinding keyReset = new KeyBinding("key.portalgunclassic.reset", Keyboard.KEY_R, "key.categories.portalgun");

    public boolean keySwitchDown = false;
    public boolean keyResetDown = false;

    public PortalStatus status = null;
    public int teleportCooldown = 0;

    public boolean justTeleported = false;
    public double mX = 0D;
    public double mY = 0D;
    public double mZ = 0D;

    @SubscribeEvent
    public void onModelRegistry(ModelRegistryEvent event)
    {
        ModelLoader.setCustomModelResourceLocation(PortalGunClassic.itemPortalGun, 0, new ModelResourceLocation("portalgunclassic:pg_blue", "inventory"));
        ModelLoader.setCustomModelResourceLocation(PortalGunClassic.itemPortalGun, 1, new ModelResourceLocation("portalgunclassic:pg_orange", "inventory"));
        ModelLoader.setCustomModelResourceLocation(PortalGunClassic.itemPortalCore, 0, new ModelResourceLocation("portalgunclassic:pg_core", "inventory"));
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END)
        {
            Minecraft mc = Minecraft.getMinecraft();
            if(mc.player != null && (mc.player.getHeldItemMainhand().getItem() == PortalGunClassic.itemPortalGun || mc.player.getHeldItemOffhand().getItem() == PortalGunClassic.itemPortalGun))
            {
                if(!keySwitchDown && keySwitch.isKeyDown())
                {
                    PortalGunClassic.channel.sendToServer(new PacketSwapType(false, 0));
                }
                if(!keyResetDown && keyReset.isKeyDown())
                {
                    PortalGunClassic.channel.sendToServer(new PacketSwapType(true, GuiScreen.isShiftKeyDown() ? 1 : 0));
                }
                keySwitchDown = keySwitch.isKeyDown();
                keyResetDown = keyReset.isKeyDown();
            }
        }
        else
        {
            Minecraft mc = Minecraft.getMinecraft();
            if(teleportCooldown > 0 && !mc.isGamePaused())
            {
                teleportCooldown--;
            }
            if(justTeleported)
            {
                if(mc.player != null && mc.player.motionX == 0.0D && mc.player.motionY == 0.0D && mc.player.motionZ == 0.0D)
                {
                    justTeleported = false;
                    mc.player.motionX = mX;
                    mc.player.motionY = mY;
                    mc.player.motionZ = mZ;
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END)
        {
            Minecraft mc = Minecraft.getMinecraft();
            if(mc.currentScreen == null && !mc.gameSettings.hideGUI && mc.player != null && (mc.player.getHeldItemMainhand().getItem() == PortalGunClassic.itemPortalGun || mc.player.getHeldItemOffhand().getItem() == PortalGunClassic.itemPortalGun))
            {
                //is holding a portal gun

                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

                ScaledResolution reso = new ScaledResolution(mc);
                double size = 40;
                double x1 = reso.getScaledWidth() / 2D - size + 1;
                double x2 = reso.getScaledWidth() / 2D + size + 1;
                double y1 = reso.getScaledHeight() / 2D - size + 1;
                double y2 = reso.getScaledHeight() / 2D + size + 1;

                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferbuilder = tessellator.getBuffer();

                mc.getTextureManager().bindTexture(status != null && status.blue ? txLFull : txLEmpty);
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                bufferbuilder.pos(x2, y2, 0.0D).tex(1D, 1D).color(5, 130, 255, 255).endVertex();
                bufferbuilder.pos(x2, y1, 0.0D).tex(1D, 0D).color(5, 130, 255, 255).endVertex();
                bufferbuilder.pos(x1, y1, 0.0D).tex(0D, 0D).color(5, 130, 255, 255).endVertex();
                bufferbuilder.pos(x1, y2, 0.0D).tex(0D, 1D).color(5, 130, 255, 255).endVertex();
                tessellator.draw();

                mc.getTextureManager().bindTexture(status != null && status.orange ? txRFull : txREmpty);
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                bufferbuilder.pos(x2, y2, 0.0D).tex(1D, 1D).color(255, 176, 6, 255).endVertex();
                bufferbuilder.pos(x2, y1, 0.0D).tex(1D, 0D).color(255, 176, 6, 255).endVertex();
                bufferbuilder.pos(x1, y1, 0.0D).tex(0D, 0D).color(255, 176, 6, 255).endVertex();
                bufferbuilder.pos(x1, y2, 0.0D).tex(0D, 1D).color(255, 176, 6, 255).endVertex();
                tessellator.draw();
            }
        }
    }

    @SubscribeEvent
    public void onConnectToServerEvent(FMLNetworkEvent.ClientConnectedToServerEvent event)
    {
        status = null;
        justTeleported = false;
    }
}
