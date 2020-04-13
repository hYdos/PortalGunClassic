package me.hydos.portalgunfabric.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import me.hydos.portalgunfabric.items.BluePortalGunItem;
import me.hydos.portalgunfabric.items.OrangePortalGunItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {


    @Shadow
    @Final
    private MinecraftClient client;

    private static final Identifier LEFT_EMPTY = new Identifier("portalgunfabric", "textures/overlay/lempty.png");
    private static final Identifier RIGHT_EMPTY = new Identifier("portalgunfabric", "textures/overlay/rempty.png");

    @Shadow
    private int scaledHeight;

    @Shadow
    private int scaledWidth;

    @Inject(method = "renderCrosshair", at = @At("TAIL"))
    public void render(CallbackInfo ci) {
        //mixin is client side so its safe to use minecraftClient
        assert client.player != null;
        Item mainItem = client.player.getMainHandStack().getItem();
        if (mainItem instanceof OrangePortalGunItem || mainItem instanceof BluePortalGunItem && this.client.options.perspective == 0) {
            renderPortalGunOverlay();
        }
    }

    private void renderPortalGunOverlay() {
        RenderSystem.defaultBlendFunc();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.pushMatrix();
        RenderSystem.scalef(0.3f, 0.3f, 0.3f);
        //draw Left
        client.getTextureManager().bindTexture(LEFT_EMPTY);
        drawTexture((this.scaledWidth + 190), (this.scaledHeight + 340) / 2, 0, 0, 256, 256);

        //draw Right
        client.getTextureManager().bindTexture(RIGHT_EMPTY);
        drawTexture((this.scaledWidth + 190), (this.scaledHeight + 360) / 2, 0, 0, 256, 256);

        RenderSystem.popMatrix();

    }


}
