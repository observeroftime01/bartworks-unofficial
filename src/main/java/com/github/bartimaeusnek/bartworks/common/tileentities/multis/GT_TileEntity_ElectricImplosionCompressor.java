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

package com.github.bartimaeusnek.bartworks.common.tileentities.multis;

import com.github.bartimaeusnek.bartworks.util.BW_Tooltip_Reference;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_ImplosionCompressor;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

import static com.github.bartimaeusnek.bartworks.common.loaders.ItemRegistry.BW_BLOCKS;

public class GT_TileEntity_ElectricImplosionCompressor extends GT_MetaTileEntity_ImplosionCompressor {

    public static GT_Recipe.GT_Recipe_Map eicMap;
    private Boolean piston = null;

    public GT_TileEntity_ElectricImplosionCompressor(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public GT_TileEntity_ElectricImplosionCompressor(String aName) {
        super(aName);
    }

    @Override
    public boolean checkRecipe(ItemStack aStack) {

        if (this.mEnergyHatches.get(0).getEUVar() <= 0 || this.mEnergyHatches.get(1).getEUVar() <= 0)
            return false;

        ArrayList<ItemStack> tInputList = this.getStoredInputs();
        int tInputList_sS = tInputList.size();

        for (int i = 0; i < tInputList_sS - 1; ++i) {
            for (int j = i + 1; j < tInputList_sS; ++j) {
                if (GT_Utility.areStacksEqual(tInputList.get(i), tInputList.get(j))) {
                    if (tInputList.get(i).stackSize < tInputList.get(j).stackSize) {
                        tInputList.remove(i--);
                        tInputList_sS = tInputList.size();
                        break;
                    }

                    tInputList.remove(j--);
                    tInputList_sS = tInputList.size();
                }
            }
        }

        ItemStack[] tInputs = tInputList.toArray(new ItemStack[0]);
        if (tInputList.size() > 0) {
            GT_Recipe tRecipe = GT_TileEntity_ElectricImplosionCompressor.eicMap.findRecipe(this.getBaseMetaTileEntity(), false, 9223372036854775807L, null, tInputs);
            if (tRecipe != null && tRecipe.isRecipeInputEqual(true, null, tInputs)) {
                this.mEfficiency = 10000 - (this.getIdealStatus() - this.getRepairStatus()) * 1000;
                this.mEfficiencyIncrease = 10000;
                this.mEUt = -tRecipe.mEUt;
                this.mMaxProgresstime = Math.max(1, tRecipe.mDuration);
                this.mOutputItems = new ItemStack[]{tRecipe.getOutput(0), tRecipe.getOutput(1)};
                this.sendLoopStart((byte) 20);
                this.updateSlots();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onRunningTick(ItemStack aStack) {
        if (this.mRuntime % 10 == 0)
            this.togglePiston();
        return super.onRunningTick(aStack);
    }

    public void stopMachine() {
        this.resetPiston();
        super.stopMachine();
    }

    @Override
    public void onFirstTick(IGregTechTileEntity aBaseMetaTileEntity) {
        if (this.piston == null)
            this.piston = true;
    }

    private void resetPiston() {
        if (this.getBaseMetaTileEntity().getWorld().isRemote)
            return;
        if (!this.piston && this.mMachine) {
            int xDir = ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getBackFacing()).offsetX;
            int zDir = ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getBackFacing()).offsetZ;
            int aX = this.getBaseMetaTileEntity().getXCoord(), aY = this.getBaseMetaTileEntity().getYCoord(), aZ = this.getBaseMetaTileEntity().getZCoord();
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if (!(Math.abs(x) == 1 && Math.abs(z) == 1))
                        this.getBaseMetaTileEntity().getWorld().setBlock(xDir + aX + x, aY + 2, zDir + aZ + z, GregTech_API.sBlockMetal5, 2, 3);
                }
            }
            GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(5), 10, 1.0F, aX, aY, aZ);
            this.piston = !this.piston;
        }
    }

    private void togglePiston() {
        if (this.getBaseMetaTileEntity().getWorld().isRemote)
            return;
        int xDir = ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getBackFacing()).offsetX;
        int zDir = ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getBackFacing()).offsetZ;
        int aX = this.getBaseMetaTileEntity().getXCoord(), aY = this.getBaseMetaTileEntity().getYCoord(), aZ = this.getBaseMetaTileEntity().getZCoord();
        boolean hax = false;
        if (this.piston) {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if (!(Math.abs(x) == 1 && Math.abs(z) == 1)) {
                        if (this.getBaseMetaTileEntity().getBlock(xDir + aX + x, aY + 2, zDir + aZ + z) != GregTech_API.sBlockMetal5 && this.getBaseMetaTileEntity().getMetaID(xDir + aX + x, aY + 2, zDir + aZ + z) != 2) {
                            hax = true;
                        }
                        this.getBaseMetaTileEntity().getWorld().setBlockToAir(xDir + aX + x, aY + 2, zDir + aZ + z);
                    }
                }
            }
        } else {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if (!(Math.abs(x) == 1 && Math.abs(z) == 1))
                        this.getBaseMetaTileEntity().getWorld().setBlock(xDir + aX + x, aY + 2, zDir + aZ + z, GregTech_API.sBlockMetal5, 2, 3);
                }
            }
        }
        GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(5), 10, 1.0F, aX, aY, aZ);
        this.piston = !this.piston;
        if (hax)
            this.explodeMultiblock();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setBoolean("piston", this.piston);
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        this.piston = aNBT.getBoolean("piston");
        super.loadNBTData(aNBT);
    }

    @Override
    @SuppressWarnings("ALL")
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack itemStack) {
        int xDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetX;
        int zDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetZ;

        for (int x = -1; x <= 1; x++) {
            for (int y = -2; y < 7; y++) {
                for (int z = -1; z <= 1; z++) {
                    IGregTechTileEntity te = aBaseMetaTileEntity.getIGregTechTileEntityOffset(xDir + x, y, z + zDir);
                    if (y == -2 || y == 6) {
                        if (!(x == 0 && z == 0)) {
                            if (!this.addMaintenanceToMachineList(te, 16) && !this.addInputToMachineList(te, 16) && !this.addOutputToMachineList(te, 16)) {
                                Block tBlock = aBaseMetaTileEntity.getBlockOffset(xDir + x, y, zDir + z);
                                byte tMeta = aBaseMetaTileEntity.getMetaIDOffset(xDir + x, y, zDir + z);
                                if ((tBlock != GregTech_API.sBlockCasings2 || tMeta != 0) && (tBlock != GregTech_API.sBlockCasings3 || tMeta != 4)) {
                                    return false;
                                }
                            }
                        } else if (x == 0 && z == 0) {
                            if (y == -2)
                                if (!this.addEnergyInputToMachineList(te, 16))
                                    return false;
                            if (y == 6)
                                if (!this.addEnergyInputToMachineList(te, 16))
                                    return false;
                        }
                    } else if ((y > -2 && y < 1) || (y > 3 && y < 6)) {
                        if (y == 0 && xDir + x == 0 && zDir + z == 0)
                            continue;
                        Block tBlock = aBaseMetaTileEntity.getBlockOffset(xDir + x, y, zDir + z);
                        byte tMeta = aBaseMetaTileEntity.getMetaIDOffset(xDir + x, y, zDir + z);
                        if (x == 0 && z == 0) {
                            if (tBlock != BW_BLOCKS[2] || tMeta != 0)
                                return false;
                        } else {
                            if (tBlock != BW_BLOCKS[2] || tMeta != 1)
                                return false;
                        }

                    } else if (y == 1) {
                        if (!GT_Utility.areStacksEqual(new ItemStack(aBaseMetaTileEntity.getBlockOffset(xDir + x, 1, zDir + z), 1, aBaseMetaTileEntity.getMetaIDOffset(xDir + x, 1, zDir + z)), Materials.Neutronium.getBlocks(1)))
                            return false;
                    } else if (y == 2) {
                        if (!this.piston) {
                            if (Math.abs(x) == 1 && Math.abs(z) == 1) {
                                if (!GT_Utility.areStacksEqual(new ItemStack(aBaseMetaTileEntity.getBlockOffset(xDir + x, 2, zDir + z), 1, aBaseMetaTileEntity.getMetaIDOffset(xDir + x, 2, zDir + z)), Materials.Neutronium.getBlocks(1)))
                                    return false;
                            }
                        } else if (!GT_Utility.areStacksEqual(new ItemStack(aBaseMetaTileEntity.getBlockOffset(xDir + x, 2, zDir + z), 1, aBaseMetaTileEntity.getMetaIDOffset(xDir + x, y, zDir + z)), Materials.Neutronium.getBlocks(1)))
                            return false;
                    } else if (y == 3) {
                        if (!GT_Utility.areStacksEqual(new ItemStack(aBaseMetaTileEntity.getBlockOffset(xDir + x, 3, zDir + z), 1, aBaseMetaTileEntity.getMetaIDOffset(xDir + x, 3, zDir + z)), Materials.Neutronium.getBlocks(1)))
                            return false;
                    }
                }
            }
        }
        return true;
    }


    @Override
    public int getPollutionPerTick(ItemStack itemStack) {
        return 0;
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new GT_TileEntity_ElectricImplosionCompressor(this.mName);
    }

    @Override
    public String[] getDescription() {
        return BW_Tooltip_Reference.getTranslatedBrandedTooltip("tooltip.tile.eic.0.name");
    }
}
