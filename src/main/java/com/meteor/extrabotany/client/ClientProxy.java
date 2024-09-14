package com.meteor.extrabotany.client;

import com.google.common.collect.Sets;
import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.client.core.handler.ColorHandler;
import com.meteor.extrabotany.client.core.handler.EventHandlerClient;
import com.meteor.extrabotany.client.core.handler.MiscellaneousIcons;
import com.meteor.extrabotany.client.render.CosmeticItemRenderLayer;
import com.meteor.extrabotany.client.render.entity.*;
import com.meteor.extrabotany.client.render.entity.gaia.*;
import com.meteor.extrabotany.client.render.entity.judah.RenderJudahOath;
import com.meteor.extrabotany.client.render.entity.judah.RenderJudahSpear;
import com.meteor.extrabotany.client.render.tile.*;
import com.meteor.extrabotany.common.CommonProxy;
import com.meteor.extrabotany.common.block.tile.*;
import com.meteor.extrabotany.common.core.handler.PersistentVariableHandler;
import com.meteor.extrabotany.common.entity.*;
import com.meteor.extrabotany.common.entity.gaia.*;
import com.meteor.extrabotany.common.entity.judah.EntityJudahOath;
import com.meteor.extrabotany.common.entity.judah.EntityJudahSpear;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.Set;

public class ClientProxy extends CommonProxy{
	
	public static boolean christmas = false;
	public static boolean halloween = false;
	private final Set<GameProfile> skinRequested = Sets.newHashSet();
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		PersistentVariableHandler.setCacheFile(new File(Minecraft.getMinecraft().gameDir, "ExtraBotanyVars.dat"));
		Minecraft.getMinecraft().addScheduledTask(()->this.loadAndSave());
		//loadAndSave();
		MinecraftForge.EVENT_BUS.register(MiscellaneousIcons.INSTANCE);
		MinecraftForge.EVENT_BUS.register(EventHandlerClient.INSTANCE);
		initRenderers();
	}
	
	public void loadAndSave() {
		try {
			PersistentVariableHandler.load();
			PersistentVariableHandler.save();
		} catch (IOException e) {
			ExtraBotany.logger.fatal("Persistent Variables couldn't load!!");
		}
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		ColorHandler.init();
		LocalDateTime now = LocalDateTime.now();
		if (now.getMonth() == Month.DECEMBER && now.getDayOfMonth() >= 16 || now.getMonth() == Month.JANUARY && now.getDayOfMonth() <= 2)
			christmas = true;
		if(now.getMonth() == Month.OCTOBER)
			halloween = true;
		if(halloween)
			ExtraBotany.logger.info("Trick or treat?");
		if(christmas)
			ExtraBotany.logger.info("Happy Christmas!");
		
		Map<String, RenderPlayer> skinMap = Minecraft.getMinecraft().getRenderManager().getSkinMap();
		RenderPlayer render;
		render = skinMap.get("default");
		render.addLayer(new CosmeticItemRenderLayer());

		render = skinMap.get("slim");
		render.addLayer(new CosmeticItemRenderLayer());
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	
	private void initRenderers() {
		//ClientRegistry.bindTileEntitySpecialRenderer(TileGildedTinyPotato.class, new RenderGildedTinyPotato());
		ClientRegistry.bindTileEntitySpecialRenderer(TilePedestal.class, new RenderPedestal());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCocoonDesire.class, new RenderCocoonDesire());
		ClientRegistry.bindTileEntitySpecialRenderer(TileLivingrockBarrel.class, new RenderLivingrockBarrel());
		ClientRegistry.bindTileEntitySpecialRenderer(TileQuantumManaBuffer.class, new RenderQuantumManaBuffer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChargePad.class, new RenderChargePad());
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkPixie.class, RenderDarkPixie::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityFlyingBoat.class, RenderFlyingBoat::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityFlowerWeapon.class, RenderFlowerWeapon::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySkullMissile.class, RenderSkullMissile::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySkullLandmine.class, RenderSkullLandmine::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySkullMinion.class, RenderSkullMinion::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGaiaIII.class, RenderGaiaIII::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySplashGrenade.class, RenderSplashGrenade::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySwordDomain.class, RenderSwordDomain::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDomain.class, RenderDomain::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMagicArrow.class, RenderMagicArrow::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySubspace.class, RenderSubspace::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySubspaceSpear.class, RenderSubspaceSpear::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityJudahOath.class, RenderJudahOath::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityJudahSpear.class, RenderJudahSpear::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBottledStar.class, RenderBottledStar::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityVoid.class, RenderVoid::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityVoidHerrscher.class, RenderVoidHerrscher::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySubspaceLance.class, RenderSubspaceLance::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPhantomSword.class, RenderPhantomSword::new);
	}
	
    @Override
    public void setTinkersRenderColor(slimeknights.tconstruct.library.materials.Material material, int color) {
        material.setRenderInfo(color);
    }
    
    @Override
	public void preloadSkin(GameProfile customSkin) {
		if (!skinRequested.contains(customSkin)) {
			Minecraft.getMinecraft().getSkinManager().loadProfileTextures(customSkin, (typeIn, location, profileTexture) -> {}, true);
			skinRequested.add(customSkin);
		}
	}
	
}
