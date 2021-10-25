package com.github.bartimaeusnek.bartworks.system.material.GT_Enhancement;


import com.github.bartimaeusnek.bartworks.common.configs.ConfigHandler;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.github.bartimaeusnek.bartworks.util.BW_Util;
import gregtech.GT_Mod;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;


import static com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader.*;
import static gregtech.api.enums.GT_Values.*;
import static gregtech.api.enums.Materials.*;
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
            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(dust, Materials.Potassium, 2L), GT_OreDictUnificator.get(dust, Materials.Sulfur, 2L), Materials.Oxygen.getGas(7000L), PotassiumDisulfate.getMolten(11000), null, 42, 90);

            // Better DT Tower Recipes for PGM Processing (bigger recipes I/O wise, reduced time)
            GT_Values.RA.addDistillationTowerRecipe(AcidicOsmiumSolution.getFluidOrGas(10000), new FluidStack[]{OsmiumSolution.getFluidOrGas(6000), Materials.Water.getFluid(4000)}, null, 375, Voltage.IV.getVoltage());
            GT_Values.RA.addDistillationTowerRecipe(HotRutheniumTetroxideSollution.getFluidOrGas(10000), new FluidStack[]{Materials.Water.getFluid(5000), RutheniumTetroxide.getFluidOrGas(5000)}, Materials.Salt.getDust(10), 375, Voltage.HV.getVoltage());

            // Multiply recipe by 9x to avoid the tiny dusts
            GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{}, new FluidStack[]{PTConcentrate.getFluidOrGas(18000), AmmoniumChloride.getFluidOrGas(1800)}, new FluidStack[]{PDAmmonia.getFluidOrGas(1800), Materials.NitrogenDioxide.getGas(9000),Materials.DilutedSulfuricAcid.getFluid(9000)}, new ItemStack[]{PTSaltCrude.get(dust, 16), PTRawPowder.get(dust,2)}, 1200, 30);
            GT_Values.RA.addCentrifugeRecipe(PTConcentrate.get(cell,18),null, AmmoniumChloride.getFluidOrGas(1800), PDAmmonia.getFluidOrGas(1800), PTSaltCrude.get(dust, 16), PTRawPowder.get(dust,2), Materials.NitrogenDioxide.getCells(9), Materials.DilutedSulfuricAcid.getCells(9), null, null, null, 1200, 30);
            GT_Values.RA.addChemicalRecipe(PTMetallicPowder.get(dust, 9), GT_Utility.getIntegratedCircuit(1), AquaRegia.getFluidOrGas(9000), PTConcentrate.getFluidOrGas(9000), PTResidue.get(dust), 250);
            GT_Values.RA.addChemicalRecipe(PDMetallicPowder.get(dust, 9), GT_Utility.getIntegratedCircuit(1), PDAmmonia.getFluidOrGas(9000), null, PDSalt.get(dust, 16), PDRawPowder.get(dust, 2), 250);

            // Adjustment of wrong recipe
            GT_Values.RA.addChemicalRecipe(Materials.Zinc.getDust(1), null, RHSulfateSolution.getFluidOrGas(1000), null, ZincSulfate.get(dust,1), CrudeRhMetall.get(dust), 300);

            // Balancing of Ruthenium
            GT_Values.RA.addBlastRecipe(LeachResidue.get(dust, 10), Materials.Saltpeter.getDust(8), Materials.SaltWater.getFluid(1000), GT_ModHandler.getSteam(1000), SodiumRuthenate.get(dust, 2), IrOsLeachResidue.get(dust, 6), 200, 120, 775);

            // Osmium Adjustment
            GT_Values.RA.addBlastRecipe(IrOsLeachResidue.get(dust, 4), GT_Utility.getIntegratedCircuit(11), Materials.HydrochloricAcid.getFluid(1000), AcidicOsmiumSolution.getFluidOrGas(3000), IrLeachResidue.get(dust, 2), null, 200, 120, 775);


        } else {
            // Do nothing
            //TODO: Add slightly harder replacement material processing recipes, but ones that are still significantly easier than what is currently implemented.

            //Here is a test recipe to determine if it loaded correctly and the toggle worked
            GT_Values.RA.addMixerRecipe(Materials.Redstone.getDust(1), GT_Utility.getIntegratedCircuit(24), null, null, Materials.Radon.getGas(144L), Xenon.getFluidOrGas(144), Materials.Glowstone.getDust(1), 20, 32);

        }

        //Fusion recipes stolen from processingloaders/AdditionalRecipes to make it more obvious that I modified them.
        //v-- This recipe makes Californium, which is fine
        GT_Values.RA.addFusionReactorRecipe(Materials.Plutonium.getMolten(48), Materials.Beryllium.getMolten(48), WerkstoffLoader.Californium.getMolten(96), 240, 49152, 480000000);
        //v-- Same recipe, but with P241 instead, because why not?
        GT_Values.RA.addFusionReactorRecipe(Materials.Plutonium241.getMolten(48), Materials.Beryllium.getMolten(48), WerkstoffLoader.Californium.getMolten(96), 240, 49152, 480000000);

        //v-- Original Recipe took twice as long, now we can produce Oganesson in step with Califonium production without having to buffer the output fluid from one of the fusion reactors.
        //GT_Values.RA.addFusionReactorRecipe(WerkstoffLoader.Californium.getMolten(48), WerkstoffLoader.Calcium.getMolten(48), WerkstoffLoader.Oganesson.getFluidOrGas(48), 480, 49152, 600000000);
        GT_Values.RA.addFusionReactorRecipe(WerkstoffLoader.Californium.getMolten(48), WerkstoffLoader.Calcium.getMolten(48), WerkstoffLoader.Oganesson.getFluidOrGas(96), 240, 49152, 600000000);

        GT_Values.RA.addFusionReactorRecipe(Uranium235.getMolten(48), Bismuth.getPlasma(48), Berkelium.getFluidOrGas(96), 40, 49152, 360000000);
        GT_Values.RA.addFusionReactorRecipe(Berkelium.getFluidOrGas(48), Flerovium.getMolten(48), Tennessine.getFluidOrGas(96), 480, 49152, 360000000);

    }

    public static void addReverseUUMrecipesBW(){
        Materials tOutputFluid;
        if (GT_Mod.gregtechproxy.mReverseUUMrecipes) {
            tOutputFluid = UUMatter;
        } else {
            tOutputFluid = UUAmplifier;
        }
        // Standard value for this parameter is 512, anything less than 512 will make it cheaper, more than 512 and it increases the cost; in ticks needed for recipe completion.
        int tMult = GT_Mod.gregtechproxy.mReverseUUMRecipeCostMultiplier;
        // Standard value for this parameter is 30, anything less than 30 will make it cheaper, more than 30 and it increases the cost; in EU/t
        int tEUt = GT_Mod.gregtechproxy.mReverseUUMRecipeEUCost;
        GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(24), Neon.get(cell, 1), null, tOutputFluid.getFluid(Neon.getStats().getMass()), ItemList.Cell_Empty.get(1), (int) Neon.getStats().getMass() * tMult, tEUt);
        GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(24), Krypton.get(cell, 1), null, tOutputFluid.getFluid(Krypton.getStats().getMass()), ItemList.Cell_Empty.get(1), (int) Krypton.getStats().getMass() * tMult, tEUt);
        GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(24), Zirconium.get(dust, 1), null, tOutputFluid.getFluid(Zirconium.getStats().getMass()), null, (int) Zirconium.getStats().getMass() * tMult, tEUt);
        GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(24), Ruthenium.get(dust, 1), null, tOutputFluid.getFluid(Ruthenium.getStats().getMass()), null, (int) Ruthenium.getStats().getMass() * tMult, tEUt);
        GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(24), Rhodium.get(dust, 1), null, tOutputFluid.getFluid(Rhodium.getStats().getMass()), null, (int) Rhodium.getStats().getMass() * tMult, tEUt);
        GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(24), Xenon.get(cell, 1), null, tOutputFluid.getFluid(Xenon.getStats().getMass()), ItemList.Cell_Empty.get(1), (int) Xenon.getStats().getMass() * tMult, tEUt);
        GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(24), Californium.get(dust, 1), null, tOutputFluid.getFluid(Californium.getStats().getMass()), null, (int) Californium.getStats().getMass() * tMult, tEUt);
        GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(24), Oganesson.get(cell, 1), null, tOutputFluid.getFluid(Oganesson.getStats().getMass()), ItemList.Cell_Empty.get(1), (int) Oganesson.getStats().getMass() * tMult, tEUt);
        GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(24), Tiberium.get(dust, 1), null, tOutputFluid.getFluid(Tiberium.getStats().getMass()), null, (int) Tiberium.getStats().getMass() * tMult, tEUt);

    }


    enum Voltage {
        ULV, LV, MV,
        HV, EV, IV,
        LUV, ZPM, UV,
        UHV, UEV, UIV,
        UMV, UXV, OpV,
        MAX;
        public int getVoltage() {
            return (int) (V[this.ordinal()] * 0.9);
        }

    }
}
