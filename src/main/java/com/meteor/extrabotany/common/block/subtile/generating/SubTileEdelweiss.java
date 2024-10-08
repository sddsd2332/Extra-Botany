package com.meteor.extrabotany.common.block.subtile.generating;

import com.meteor.extrabotany.common.core.config.ConfigHandler;
import com.meteor.extrabotany.common.lexicon.LexiconData;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;

import java.util.List;

public class SubTileEdelweiss extends SubTileGenerating{
	
	private static final String TAG_BURN_TIME = "burnTime";
	
	int burnTime;
	
	@Override
	public void onUpdate() {
		if(redstoneSignal > 0)
			return;
		super.onUpdate();
		
		if(burnTime > 0)
			burnTime--;
		
		int snowGolem = 1600;
		float range = 1F;
		AxisAlignedBB axis = new AxisAlignedBB(getPos().getX()-range, getPos().getY()-range, getPos().getZ()-range, getPos().getX()+range, getPos().getY()+range, getPos().getZ()+range);
		List<EntitySnowman> entities = getWorld().getEntitiesWithinAABB(EntitySnowman.class, axis);
		for(EntitySnowman entity : entities){
			if(mana < getMaxMana())
				if(entity instanceof EntitySnowman && entity.isEntityAlive() && !entity.isDead){
					entity.setDead();
					mana +=snowGolem * ConfigHandler.EFF_ELDELWEISS;
					burnTime+=20 * ConfigHandler.EFF_ELDELWEISS;
					break;
				}
		}
	}
	
	@Override
	public void writeToPacketNBT(NBTTagCompound cmp) {
		super.writeToPacketNBT(cmp);

		cmp.setInteger(TAG_BURN_TIME, burnTime);
	}

	@Override
	public void readFromPacketNBT(NBTTagCompound cmp) {
		super.readFromPacketNBT(cmp);

		burnTime = cmp.getInteger(TAG_BURN_TIME);
	}
	
	@Override
	public boolean canGeneratePassively() {
		return burnTime > 0;
	}
	
	@Override
	public int getColor() {
		return 0X4169E1;
	}
	
	@Override
	public int getValueForPassiveGeneration() {
		return 0;
	}
	
	@Override
	public int getMaxMana() {
		return 25000;
	}
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconData.edelweiss;
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toBlockPos(), 1);
	}

}
