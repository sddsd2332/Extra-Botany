package com.meteor.extrabotany.common.block.fluid;

import com.meteor.extrabotany.common.lib.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class ManaFluid extends Fluid{

    public ManaFluid(String fluidName) {
        super(fluidName, new ResourceLocation(Reference.MOD_ID, "blocks/fluid/" + fluidName + "_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluid/" + fluidName + "_flow"));
        this.setViscosity(1200);
        this.setTemperature(100);
        this.setLuminosity(12);
    }

}
