package com.github.bartimaeusnek.bartworks.system.material.GT_Enhancement;


import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import gregtech.common.items.ItemComb;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import gregtech.common.items.CombType;


import static com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader.*;
import static gregtech.api.enums.GT_Values.*;
import static gregtech.api.enums.OrePrefixes.*;
import static gregtech.api.enums.OrePrefixes.ingotHot;

public class CustomRecipeList {
    public static void addCustomRecipes(){

        //Easier "Replacement Material" processing in the Mixer
        GT_Values.RA.addMixerRecipe(PTMetallicPowder.get(dust, 1), null, null, null, null, null, Materials.Platinum.getDust(1), 20, 32);
        GT_Values.RA.addMixerRecipe(PDMetallicPowder.get(dust, 1), null, null, null, null, null, Materials.Palladium.getDust(1), 20, 32);
        GT_Values.RA.addMixerRecipe(IrOsLeachResidue.get(dust, 1), null, null, null, null, null, Materials.Osmium.getDust(1), 20, 32);
        GT_Values.RA.addMixerRecipe(IrLeachResidue.get(dust, 1), null, null, null, null, null, Materials.Iridium.getDust(1), 20, 32);
        GT_Values.RA.addMixerRecipe(CrudeRhMetall.get(dust,1), null, null, null, null, null, Rhodium.get(dust, 1), 20, 32);

        //Easier Aqua Regia, Because Working with cells Suck!
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{null}, new FluidStack[]{Materials.DilutedSulfuricAcid.getFluid(1000), Materials.NitricAcid.getFluid(1000)}, new FluidStack[]{AquaRegia.getFluidOrGas(2000)}, new ItemStack[]{null}, 30, 30);

        //Easier "Replacement Material" processing in the EBF
        GT_Values.RA.addBlastRecipe(PTMetallicPowder.get(dust, 1), GT_Utility.getIntegratedCircuit(12), Materials.Nitrogen.getGas(144), null, GT_OreDictUnificator.get(ingot, Materials.Platinum, 1), null, 1200, 1900, 3500);
        GT_Values.RA.addBlastRecipe(PDMetallicPowder.get(dust,1), GT_Utility.getIntegratedCircuit(12), Materials.Nitrogen.getGas(144), null, GT_OreDictUnificator.get(ingotHot, Materials.Palladium, 1), null, 3640, 120, 1828);
        GT_Values.RA.addBlastRecipe(IrOsLeachResidue.get(dust, 1), GT_Utility.getIntegratedCircuit(12), Materials.Nitrogen.getGas(144), null, GT_OreDictUnificator.get(ingotHot, Materials.Osmium, 1), null, 1000, 30720, 4500);
        GT_Values.RA.addBlastRecipe(IrLeachResidue.get(dust, 1), GT_Utility.getIntegratedCircuit(12), Materials.Nitrogen.getGas(144), null, GT_OreDictUnificator.get(ingotHot, Materials.Iridium, 1), null, 1200, 7680, 4500);
        GT_Values.RA.addBlastRecipe(CrudeRhMetall.get(dust, 1), GT_Utility.getIntegratedCircuit(12), Materials.Nitrogen.getGas(144), null, Rhodium.get(ingotHot, 1), null, 1200, 2237, 3500);

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
