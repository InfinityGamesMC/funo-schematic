package host.funo.schematic;

import io.gomint.world.block.*;

public class BlockConverter {

    public static Class<? extends Block> getType( byte id ) {
        switch( id ) {
            case 1:
                return BlockStone.class;
            case 2:
                return BlockGrassBlock.class;
            case 3:
                return BlockDirt.class;
            case 4:
                return BlockCobblestone.class;
            case 7:
                return BlockBedrock.class;
            case 17:
                return BlockLog.class;
            case 18:
                return BlockLeaves.class;
        }
        return BlockAir.class;
    }
}
