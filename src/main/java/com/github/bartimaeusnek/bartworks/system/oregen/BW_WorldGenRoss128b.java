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
import gregtech.api.enums.Materials;
import gregtech.api.interfaces.ISubTagContainer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;

import static com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader.*;
import static com.github.bartimaeusnek.crossmod.galacticraft.GalacticraftProxy.uo_dimensionList;
import static gregtech.api.enums.Materials.*;

public class BW_WorldGenRoss128b extends BW_OreLayer {

    public Block getDefaultBlockToReplace(){
        return Blocks.stone;
    }

    @Override
    public int[] getDefaultDamageToReplace() {
        return new int[]{0};
    }

    @Override
    public String getDimName() {
        return StatCollector.translateToLocal("planet.Ross128b");
    }

    public BW_WorldGenRoss128b(String aName, boolean aDefault, int aMinY, int aMaxY, int aWeight, int aDensity, int aSize, ISubTagContainer top, ISubTagContainer bottom, ISubTagContainer between, ISubTagContainer sprinkled) {
        super(aName, aDefault, aMinY, aMaxY, aWeight, aDensity, aSize, top, bottom, between, sprinkled);
    }

    public static void initOres() {
        new BW_WorldGenRoss128b("ore.mix.ross128.Thorianit", true, 30, 60, 60, 4, 16, Thorianit, Uraninite, Lepidolite, Spodumene);
        new BW_WorldGenRoss128b("ore.mix.ross128.carbon", true, 5, 25, 60, 4, 16, Graphite, Diamond, Coal, Graphite);
        new BW_WorldGenRoss128b("ore.mix.ross128.bismuth", true, 5, 80, 60, 4, 16, Bismuthinit, Stibnite, Bismuth, Bismutite);
        new BW_WorldGenRoss128b("ore.mix.ross128.TurmalinAlkali", true, 5, 80, 60, 4, 16, Olenit, FluorBuergerit, ChromoAluminoPovondrait, VanadioOxyDravit);
        new BW_WorldGenRoss128b("ore.mix.ross128.Roquesit", true, 30, 50, 60, 4, 16, Arsenopyrite, Ferberite, Loellingit, Roquesit);
        new BW_WorldGenRoss128b("ore.mix.ross128.Tungstate", true, 5, 40, 60, 4, 16, Ferberite, Huebnerit, Loellingit, Scheelite);
        new BW_WorldGenRoss128b("ore.mix.ross128.CopperSulfits", true, 40, 70, 60, 4, 16, Djurleit, Bornite, Wittichenit, Tetrahedrite);
        new BW_WorldGenRoss128b("ore.mix.ross128.Forsterit", true, 20, 90, 60, 4, 16, Forsterit, Fayalit, DescloiziteCUVO4, DescloiziteZNVO4);
        new BW_WorldGenRoss128b("ore.mix.ross128.Hedenbergit", true, 20, 90, 60, 4, 16, Hedenbergit, Fayalit, DescloiziteCUVO4, DescloiziteZNVO4);
        new BW_WorldGenRoss128b("ore.mix.ross128.RedZircon", true, 10, 80, 60, 4, 16, Fayalit, FuchsitAL, RedZircon, FuchsitCR);
        new BW_WorldGenRoss128b("ore.mix.ross128b.Ruthenium2", true, 10, 80, 70, 4, 16, Ruthenium, Ruthenium, Ruthenium, Ruthenium);
        new BW_WorldGenRoss128b("ore.mix.ross12b.usefull", true, 5, 90, 70, 4, 16, Osmium, Iridium, Platinum, Ruthenium);
        new BW_WorldGenRoss128b("ore.mix.ross12b.Rhodium12b", true, 5, 90, 70, 4, 16, Rhodium, Rhodium, Rhodium, Rhodium);
    }

    public static void initundergroundFluids() {
        String ross128b = StatCollector.translateToLocal("planet.Ross128b");
        uo_dimensionList.SetConfigValues(ross128b, ross128b, "veryheavyoil", "liquid_extra_heavy_oil", 0, 625, 40, 5);
        uo_dimensionList.SetConfigValues(ross128b, ross128b, "lava", FluidRegistry.getFluidName(FluidRegistry.LAVA), 0, 32767, 5, 5);
        uo_dimensionList.SetConfigValues(ross128b, ross128b, "gas_natural_gas", "gas_natural_gas", 0, 625, 65, 5);
    }

    @Override
    public boolean isGenerationAllowed(World aWorld, int aDimensionType, int aAllowedDimensionType) {
        return aDimensionType == ConfigHandler.ross128BID;
    }

}
