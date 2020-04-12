package me.hydos.portalgunfabric.items;

import me.hydos.portalgunfabric.Sounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class OrangePortalGunItem extends Item {

    public OrangePortalGunItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand)
    {
        if (!world.isClient) {
            world.playSound(
                    null,
                    playerEntity.getBlockPos(),
                    Sounds.ORANGE_SHOOT_SOUND_EVENT,
                    SoundCategory.MASTER,
                    1f,
                    1f
            );
            playerEntity.getItemCooldownManager().set(this, 20);
        }
        return new TypedActionResult<>(ActionResult.FAIL, playerEntity.getStackInHand(hand));
    }



}
