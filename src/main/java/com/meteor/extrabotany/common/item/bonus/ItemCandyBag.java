package com.meteor.extrabotany.common.item.bonus;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.meteor.extrabotany.api.item.WeightCategory;
import com.meteor.extrabotany.client.ClientProxy;
import com.meteor.extrabotany.common.lib.LibItemsName;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCandyBag extends ItemBonusBase {

	public static List<WeightCategory> category = new ArrayList<WeightCategory>();

	public ItemCandyBag() {
		super(LibItemsName.CANDYBAG);
	}

	@Nonnull
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslationKey(ItemStack stack) {
		String name = super.getTranslationKey(stack);
		if (ClientProxy.christmas)
			name = name.replaceAll("candybag", "candybagchris");
		return name;
	}

	@Override
	public List<WeightCategory> getWeightCategory(ItemStack stack) {
		return category;
	}

}
