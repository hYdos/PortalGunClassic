package me.hydos.portalgunfabric;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Sounds {

    private static final Identifier RESET_SOUND = new Identifier("portalgunfabric", "reset");
    public static SoundEvent RESET_SOUND_EVENT = new SoundEvent(RESET_SOUND);

    private static final Identifier BLUE_SHOOT_SOUND = new Identifier("portalgunfabric", "fireblue");
    public static SoundEvent BLUE_SHOOT_SOUND_EVENT = new SoundEvent(BLUE_SHOOT_SOUND);

    private static final Identifier ORANGE_SHOOT_SOUND = new Identifier("portalgunfabric", "firered");
    public static SoundEvent ORANGE_SHOOT_SOUND_EVENT = new SoundEvent(ORANGE_SHOOT_SOUND);

    public static void register(){
        Registry.register(Registry.SOUND_EVENT, RESET_SOUND, RESET_SOUND_EVENT);

        Registry.register(Registry.SOUND_EVENT, BLUE_SHOOT_SOUND, BLUE_SHOOT_SOUND_EVENT);

        Registry.register(Registry.SOUND_EVENT, ORANGE_SHOOT_SOUND, ORANGE_SHOOT_SOUND_EVENT);
    }

}
