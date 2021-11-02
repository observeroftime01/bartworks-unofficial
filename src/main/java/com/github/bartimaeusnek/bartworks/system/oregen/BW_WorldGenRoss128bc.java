/*
 * Copyright (c) 2018-2020 bartimaeusnek
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.bartimaeusnek.bartworks.system.oregen;


import com.github.bartimaeusnek.bartworks.common.configs.ConfigHandler;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.interfaces.ISubTagContainer;
import net.minecraft.block.Block;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import static com.github.bartimaeusnek.crossmod.galacticraft.GalacticraftProxy.uo_dimensionList;
import static gregtech.api.enums.Materials.*;
import static gregtech.common.blocks.GT_Block_Ores_Abstract.aBlockedOres;

public class BW_WorldGenRoss128bc extends BW_OreLayer  {

    public BW_WorldGenRoss128bc(String aName, boolean aDefault, int aMinY, int aMaxY, int aWeight, int aDensity, int aSize, ISubTagContainer top, ISubTagContainer bottom, ISubTagContainer between, ISubTagContainer sprinkled) {
        super(aName, aDefault, aMinY, aMaxY, aWeight, aDensity, aSize, top, bottom, between, sprinkled);
    }

    @Override
    public Block getDefaultBlockToReplace() {
        return Block.getBlockFromName("GalacticraftCore:tile.moonBlock");
    }
    @Override
    public int[] getDefaultDamageToReplace(){
        int[] ret = new int[12];
        for (int i = 0; i < 12; i++) {
            if (i != 5 && i != 3)
                ret[i] = i;
        }
        return ret;
    }

    @Override
    public String getDimName() {
        return StatCollector.translateToLocal("moon.Ross128bc");
    }

    public static void init_Ores() {
        /*
        What the actual fuck is this? Generate literally every single ore as an ore layer?
         */
        for (int i = 1; i < GregTech_API.sGeneratedMaterials.length; i++) {
            if (GregTech_API.sGeneratedMaterials[i] != null){
                if ((GregTech_API.sGeneratedMaterials[i].mTypes & 0x8) != 0 && !aBlockedOres.contains(GregTech_API.sGeneratedMaterials[i])){
                    Materials tMat = GregTech_API.sGeneratedMaterials[i];
                    new BW_WorldGenRoss128bc("ore.mix.ross128bc." + tMat, true, 30, 60, 60, 6, 16, tMat, tMat, tMat, tMat);
                }
            }
        }



        /*
        new BW_WorldGenRoss128bc("ore.mix.ross128bc.plat", true, 30, 60, 60, 4, 16, Platinum, Platinum, Platinum, Platinum);
        new BW_WorldGenRoss128bc("ore.mix.ross128bc.pall", true, 30, 60, 60, 4, 16, Palladium, Palladium, Palladium, Palladium);
        new BW_WorldGenRoss128bc("ore.mix.ross128bc.osmi", true, 30, 60, 60, 4, 16, Osmium, Osmium, Osmium, Osmium);
        new BW_WorldGenRoss128bc("ore.mix.ross128bc.irid", true, 30, 60, 60, 4, 16, Iridium, Iridium, Iridium, Iridium);
        new BW_WorldGenRoss128bc("ore.mix.ross128bc.ruth", true, 30, 60, 60, 4, 16, Ruthenium, Ruthenium, Ruthenium, Ruthenium);
        new BW_WorldGenRoss128bc("ore.mix.ross128bc.rhod", true, 30, 60, 60, 4, 16, Rhodium, Rhodium, Rhodium, Rhodium);
        new BW_WorldGenRoss128bc("ore.mix.ross128bc.naqu", true, 30, 60, 60, 4, 16, Naquadah, NaquadahEnriched, Naquadria, Naquadria);
        new BW_WorldGenRoss128bc("ore.mix.ross128bc.plut", true, 30, 60, 60, 4, 16, Uranium, Plutonium, Uranium235, Plutonium241);
        new BW_WorldGenRoss128bc("ore.mix.ross128bc.neut", true, 30, 60, 60, 4, 16, Neutronium, CosmicNeutronium, InfinityCatalyst, Infinity);

         */
    }

    public static void init_undergroundFluids() {
        String ross128bc = StatCollector.translateToLocal("moon.Ross128bc");
        uo_dimensionList.SetConfigValues(ross128bc, ross128bc, SulfuricAcid.getFluid(1).getFluid().getName(), SulfuricAcid.getFluid(1).getFluid().getName(), 5, 3000, 15, 5);
        uo_dimensionList.SetConfigValues(ross128bc, ross128bc, Deuterium.getGas(1).getFluid().getName(), Deuterium.getGas(1).getFluid().getName(), 5, 3000, 30, 5);
        uo_dimensionList.SetConfigValues(ross128bc, ross128bc, Helium_3.getGas(1).getFluid().getName(), Helium_3.getGas(1).getFluid().getName(), 5, 3000, 40, 5);
        uo_dimensionList.SetConfigValues(ross128bc, ross128bc, Tritium.getGas(1).getFluid().getName(), Tritium.getGas(1).getFluid().getName(), 5, 3000, 30, 5);
    }

    @Override
    public boolean isGenerationAllowed(World aWorld, int aDimensionType, int aAllowedDimensionType) {
        return aDimensionType == ConfigHandler.ross128BCID;
    }

}
