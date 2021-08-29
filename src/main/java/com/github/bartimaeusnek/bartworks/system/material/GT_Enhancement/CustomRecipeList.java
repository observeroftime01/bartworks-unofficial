package com.github.bartimaeusnek.bartworks.system.material.GT_Enhancement;


import com.github.bartimaeusnek.bartworks.common.configs.ConfigHandler;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;


import static com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader.*;
import static gregtech.api.enums.GT_Values.*;
import static gregtech.api.enums.OrePrefixes.*;

public class CustomRecipeList {
    public static void addCustomRecipes() {

        if (ConfigHandler.easierreplacementprocessing) {

            /*There is a toggle in the config (hopefully) that if enabled, will
            allow you to bypass the complex processing chains involved. I'm going
            to leave this up to the individual to decide, since I like the idea
            and effort that went in to it, I just don't like playing this way on
            my own server. So, we give the choice to the user.
             */

            //Easier "Replacement Material" processing in the Mixer
            GT_Values.RA.addMixerRecipe(PTMetallicPowder.get(dust, 1), null, null, null, null, null, Materials.Platinum.getDust(1), 20, 32);
            GT_Values.RA.addMixerRecipe(PDMetallicPowder.get(dust, 1), null, null, null, null, null, Materials.Palladium.getDust(1), 20, 32);
            GT_Values.RA.addMixerRecipe(IrOsLeachResidue.get(dust, 1), null, null, null, null, null, Materials.Osmium.getDust(1), 20, 32);
            GT_Values.RA.addMixerRecipe(IrLeachResidue.get(dust, 1), null, null, null, null, null, Materials.Iridium.getDust(1), 20, 32);
            GT_Values.RA.addMixerRecipe(CrudeRhMetall.get(dust, 1), null, null, null, null, null, Rhodium.get(dust, 1), 20, 32);

            //Easier Aqua Regia, Because Working with cells Suck!
            GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{null}, new FluidStack[]{Materials.DilutedSulfuricAcid.getFluid(1000), Materials.NitricAcid.getFluid(1000)}, new FluidStack[]{AquaRegia.getFluidOrGas(2000)}, new ItemStack[]{null}, 30, 30);

            //Easier "Replacement Material" processing in the EBF
            GT_Values.RA.addBlastRecipe(PTMetallicPowder.get(dust, 1), GT_Utility.getIntegratedCircuit(12), Materials.Nitrogen.getGas(144), null, GT_OreDictUnificator.get(ingot, Materials.Platinum, 1), null, 1200, 1900, 3500);
            GT_Values.RA.addBlastRecipe(PDMetallicPowder.get(dust, 1), GT_Utility.getIntegratedCircuit(12), Materials.Nitrogen.getGas(144), null, GT_OreDictUnificator.get(ingotHot, Materials.Palladium, 1), null, 3640, 120, 1828);
            GT_Values.RA.addBlastRecipe(IrOsLeachResidue.get(dust, 1), GT_Utility.getIntegratedCircuit(12), Materials.Nitrogen.getGas(144), null, GT_OreDictUnificator.get(ingotHot, Materials.Osmium, 1), null, 1000, 30720, 4500);
            GT_Values.RA.addBlastRecipe(IrLeachResidue.get(dust, 1), GT_Utility.getIntegratedCircuit(12), Materials.Nitrogen.getGas(144), null, GT_OreDictUnificator.get(ingotHot, Materials.Iridium, 1), null, 1200, 7680, 4500);
            GT_Values.RA.addBlastRecipe(CrudeRhMetall.get(dust, 1), GT_Utility.getIntegratedCircuit(12), Materials.Nitrogen.getGas(144), null, Rhodium.get(ingotHot, 1), null, 1200, 2237, 3500);

            //Magneto Reso Dust, because Zirconium isn't oredicted, and I have no idea otherwise how to get Zirconia or Cubic Zirconia.
            GT_Values.RA.addMixerRecipe(Prasiolite.get(dust, 3), BismuthTellurite.get(dust, 4), Zirconium.get(dust, 1), GT_OreDictUnificator.get(dust, Materials.SteelMagnetic, 1L), GT_Utility.getIntegratedCircuit(2), null, null,null, null, null, null, MagnetoResonaticDust.get(dust, 9), 1086, 20);

            //Easier Potassium Disulfate, skipping the time-limiting fluid extraction step
            GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{GT_OreDictUnificator.get(dust, Materials.Potassium, 2L), GT_OreDictUnificator.get(dust, Materials.Sulfur, 2L), GT_Utility.getIntegratedCircuit(2)}, new FluidStack[]{Materials.Oxygen.getGas(7000L)}, new FluidStack[]{PotassiumDisulfate.getMolten(11000)}, null, 42, 90);



        } else {
            // Do nothing
            //TODO: Add slightly harder replacement material processing recipes, but ones that are still significantly easier than what is currently implemented.

            //Here is a test recipe to determine if it loaded correctly and the toggle worked
            GT_Values.RA.addMixerRecipe(Materials.Redstone.getDust(1), GT_Utility.getIntegratedCircuit(24), null, null, Materials.Radon.getGas(144L), Xenon.getFluidOrGas(144), Materials.Glowstone.getDust(1), 20, 32);

        }

        //Fusion recipes stolen from processingloaders/AdditionalRecipes to make it more obvious that I modified them.
        //v-- This recipe makes Californium, which is fine
        GT_Values.RA.addFusionReactorRecipe(Materials.Plutonium.getMolten(48), Materials.Beryllium.getMolten(48), WerkstoffLoader.Californium.getMolten(48), 240, 49152, 480000000);
        //v-- Same recipe, but with P241 instead, because why not?
        GT_Values.RA.addFusionReactorRecipe(Materials.Plutonium241.getMolten(48), Materials.Beryllium.getMolten(48), WerkstoffLoader.Californium.getMolten(48), 240, 49152, 480000000);

        //v-- Original Recipe took twice as long, now we can produce Oganesson in step with Califonium production without having to buffer the output fluid from one of the fusion reactors.
        //GT_Values.RA.addFusionReactorRecipe(WerkstoffLoader.Californium.getMolten(48), WerkstoffLoader.Calcium.getMolten(48), WerkstoffLoader.Oganesson.getFluidOrGas(48), 480, 49152, 600000000);
        GT_Values.RA.addFusionReactorRecipe(WerkstoffLoader.Californium.getMolten(48), WerkstoffLoader.Calcium.getMolten(48), WerkstoffLoader.Oganesson.getFluidOrGas(48), 240, 49152, 600000000);

    }

    enum Voltage {
        ULV, LV, MV,
        HV, EV, IV,
        LUV, ZPM, UV,
        UHV, UEV, UIV,
        UMV, UXV, OpV,
        MAX;
        public int getVoltage() {
            return (int) (V[this.ordinal()] / 1.5);
        }

    }
}
