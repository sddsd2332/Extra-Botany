package com.meteor.extrabotany.client.render.entity.gaia;

import com.meteor.extrabotany.client.model.ModelSubspaceSpear;
import com.meteor.extrabotany.common.entity.gaia.EntitySubspaceLance;
import com.meteor.extrabotany.common.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderSubspaceLance extends Render<EntitySubspaceLance> {
	
	private static final ModelSubspaceSpear model = new ModelSubspaceSpear();
	private static final ResourceLocation SPEAR_TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/entity/spearsubspace.png");
	
	public RenderSubspaceLance(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(@Nonnull EntitySubspaceLance weapon, double d0, double d1, double d2, float par8, float par9) {
		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1F, 1F, 1F, 1F);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
		GlStateManager.disableLighting();
		GlStateManager.translate(d0, d1, d2);
		GlStateManager.translate(0.5F, 1.5F, 0.5F);
		GlStateManager.translate(0F, -1F, 0F);
		GlStateManager.translate(0F, 1F, 0F);
		GlStateManager.rotate(weapon.getRotation(), 0F, 1F, 0F);
		GlStateManager.rotate(90F, 1F, 0F, 0F);
		GlStateManager.rotate(weapon.getPitch(), 1F, 0F, 0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(SPEAR_TEXTURES);
		GlStateManager.scale(1F, -1F, -1F);
		model.render(0.07F);
		GlStateManager.color(1F, 1F, 1F);
		GlStateManager.scale(1F, -1F, -1F);
		GlStateManager.enableRescaleNormal();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Nonnull
	@Override
	protected ResourceLocation getEntityTexture(@Nonnull EntitySubspaceLance entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}
}
