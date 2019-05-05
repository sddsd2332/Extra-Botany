package com.meteor.extrabotany.common.item.lens;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.item.lens.Lens;

import java.util.List;

public class LensPush extends Lens {

    private static final String TAG_HOME_ID = "homeID";

    @Override
    public void updateBurst(IManaBurst burst, EntityThrowable entity, ItemStack stack) {
        AxisAlignedBB axis = new AxisAlignedBB(entity.posX, entity.posY, entity.posZ, entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ);
        List<EntityLivingBase> entities = entity.world.getEntitiesWithinAABB(EntityLivingBase.class, axis);
        int homeID = entity.getEntityData().getInteger(TAG_HOME_ID);
        for (EntityLivingBase living : entities) {
            entity.getEntityData().setInteger(TAG_HOME_ID, living.getEntityId());
            break;
        }

        Entity result = entity.world.getEntityByID(homeID);
        if (result != null && result.getDistance(entity) < 2F && !burst.isFake()) {
            result.motionX = entity.motionX;
            result.motionY = entity.motionY;
            result.motionZ = entity.motionZ;
        }
    }

}
