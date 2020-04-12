package me.hydos.portalgunfabric;

import me.hydos.portalgunfabric.items.BluePortalGunItem;
import me.hydos.portalgunfabric.items.OrangePortalGunItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {

    public static final OrangePortalGunItem ORANGE_PORTAL_GUN = new OrangePortalGunItem(new Item.Settings().maxCount(1));

    public static final BluePortalGunItem BLUE_PORTAL_GUN = new BluePortalGunItem(new Item.Settings().maxCount(1));

    public static final Item PORTAL_GUN_CORE = new Item(new Item.Settings().maxCount(16));

    public static void register(){
        Registry.register(Registry.ITEM, new Identifier("portalgunfabric", "blue_portal_gun"), BLUE_PORTAL_GUN);
        Registry.register(Registry.ITEM, new Identifier("portalgunfabric", "orange_portal_gun"), ORANGE_PORTAL_GUN);
        Registry.register(Registry.ITEM, new Identifier("portalgunfabric", "portal_gun_core"), PORTAL_GUN_CORE);

        FabricItemGroupBuilder.create(new Identifier("portalgunfabric", "portalgun"))
                .icon(() -> new ItemStack(BLUE_PORTAL_GUN))
                .appendItems(stacks ->
                {
                    stacks.add(new ItemStack(BLUE_PORTAL_GUN));
                    stacks.add(new ItemStack(PORTAL_GUN_CORE));
                }).build();

    }


}
