package com.github.bartimaeusnek.bartworks.system.material.werkstoff_loaders.recipe;

import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;
import com.github.bartimaeusnek.bartworks.system.material.werkstoff_loaders.IWerkstoffRunnable;
import com.github.bartimaeusnek.bartworks.system.oregen.BW_WorldGenRoss128bc;

import static gregtech.api.enums.OrePrefixes.ore;

public class Ross128bcWerkstoffLoader implements IWerkstoffRunnable {
    /*
    And what the hell is this thing doing? Adding even more ore layers?
     */
    @Override
    public void run(Werkstoff werkstoff) {
        if (werkstoff.hasItemType(ore)){
            new BW_WorldGenRoss128bc("ore.mix.ross128bc." + werkstoff.getDefaultName(), true, 30, 60, 60, 6, 16, werkstoff, werkstoff, werkstoff, werkstoff);

        }
    }
}
