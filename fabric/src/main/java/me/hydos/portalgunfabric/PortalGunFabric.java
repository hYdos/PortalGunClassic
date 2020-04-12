package me.hydos.portalgunfabric;

import net.fabricmc.api.ModInitializer;

public class PortalGunFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		Items.register();
		Keybinds.register();
		Sounds.register();
	}
}
