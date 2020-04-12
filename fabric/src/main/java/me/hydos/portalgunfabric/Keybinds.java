package me.hydos.portalgunfabric;

import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class Keybinds {

    public static FabricKeyBinding portalSwitchKeybind;

    public static void register(){
        portalSwitchKeybind = FabricKeyBinding.Builder.create(
                new Identifier("portalgunfabric", "switch"),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "Portal Gun"
        ).build();

        KeyBindingRegistry.INSTANCE.addCategory("Portal Gun");
        KeyBindingRegistry.INSTANCE.register(portalSwitchKeybind);

        registerEvents();

    }

    private static void registerEvents(){
        ClientTickCallback.EVENT.register(e ->
        {
            if(portalSwitchKeybind.isPressed()){

            }
        });
    }

}
