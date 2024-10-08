package com.meteor.extrabotany.common.block.subtile.functional;

import com.meteor.extrabotany.common.brew.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

import java.util.List;

public class SubTileNecrofleur extends SubTileFunctional{
	
	private static final int RANGE = 7;
	private static final float DAMAGE = 10;
	private static final int RANGE_MINI = 3;
	private static final float DAMAGE_MINI = 6;
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(redstoneSignal > 0)
			return;
		
		AxisAlignedBB axis = new AxisAlignedBB(getPos().getX()-getRange(), getPos().getY()-getRange(), getPos().getZ()-getRange(), getPos().getX()+getRange(), getPos().getY()+getRange(), getPos().getZ()+getRange());
		List<EntityLivingBase> entities = getWorld().getEntitiesWithinAABB(EntityLivingBase.class, axis);
		for(int i = 0; i < entities.size(); i++) {
			EntityLivingBase entity = entities.get(i);
			if(entity != null && entity.getHealth() < entity.getMaxHealth() * 0.5F && this.ticksExisted % 20 == 0 && this.mana >= 200) {
				entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(entity, entity), getDamage());
				entity.addPotionEffect(new PotionEffect(ModPotions.witchcurse, 300, 4));
				this.mana = 0;
			}
		}
	}

	@Override
	public int getMaxMana() {
		return 200;
	}
	
	public int getRange() {
		return RANGE;
	}
	
	public float getDamage() {
		return DAMAGE;
	}
	
	@Override
	public boolean acceptsRedstone() {
		return true;
	}

	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toBlockPos(), getRange());
	}
	
	public static class Mini extends SubTileNecrofleur {
		@Override public int getRange() { return RANGE_MINI; }
		@Override public float getDamage() { return DAMAGE_MINI; }
	}

}
