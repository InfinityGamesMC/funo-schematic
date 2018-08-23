package host.funo.schematic;

import io.gomint.server.world.WorldAdapter;
import io.gomint.world.World;
import io.gomint.math.BlockPosition;
import io.gomint.taglib.NBTStream;

import java.io.*;
import java.nio.ByteOrder;
import java.util.zip.GZIPInputStream;

public class FunoSchematic {
    private SchematicData schematicData;

    public FunoSchematic( SchematicData data ) {
        this.schematicData = data;
    }

    /**
     * Paste the current schematic into the world at the given position.
     *
     * @param world the world in which to paste the schematic
     * @param position the position at which to paste the schematic
     */
    public void paste( World world, BlockPosition position ) {
        for ( int x = 0; x < this.schematicData.width; x++ ) {
            for ( int y = 0; y < this.schematicData.height; y++ ) {
                for ( int z = 0; z < this.schematicData.length; z++ ) {
                    int index = y * this.schematicData.width * this.schematicData.length + z * this.schematicData.width + x;

                    WorldAdapter worldAdapter = (WorldAdapter) world;
                    worldAdapter.setBlockId( position, -1, this.schematicData.blocks[index] );
                    worldAdapter.setBlockData( position, -1, this.schematicData.blockData[index] );
                }
            }
        }
    }

    /**
     * @return the schematic data
     */
    public SchematicData getSchematicData() {
        return this.schematicData;
    }


    public static class SchematicLoader {
        private File schematicFile;

        /**
         * @param schematic the schematic file to load
         */
        public SchematicLoader( File schematic ) {
            this.schematicFile = schematic;
        }

        /**
         * Loads a schematic file.
         *
         * @return the schematic file;
         */
        public FunoSchematic load() throws Exception {
            NBTStream nbt = new NBTStream( new GZIPInputStream( new FileInputStream( this.schematicFile ) ), ByteOrder.BIG_ENDIAN );
            FunoSchematic.SchematicData data = new FunoSchematic.SchematicData();

            nbt.addListener( ( path, value ) -> {
                switch( path ) {
                    case "Schematic.Width":
                        data.width = (short) value;
                        break;
                    case "Schematic.Length":
                        data.length = (short) value;
                        break;
                    case "Schematic.Height":
                        data.height = (short) value;
                        break;
                    case "Schematic.Materials":
                        //data.materials = (String) value;
                        break;
                    case "Schematic.Blocks":
                        data.blocks = (byte[]) value;
                        break;
                    case "Schematic.Data":
                        data.blockData = (byte[]) value;
                        break;
                }
            } );

            nbt.parse();
            return new FunoSchematic( data );
        }
    }

    public static class SchematicData {
        public short width;
        public short length;
        public short height;
        //public String materials;
        public byte[] blocks;
        public byte[] blockData;

        public SchematicData() {}

        public SchematicData( short width, short length, short height ) {
            this.width = width;
            this.length = length;
            this.height = height;
        }

        public SchematicData( short width, short length, short height, byte[] blocks ) {
            this.width = width;
            this.length = length;
            this.height = height;
            this.blocks = blocks;
        }

        public SchematicData( short width, short length, short height, byte[] blocks, byte[] blockData ) {
            this.width = width;
            this.length = length;
            this.height = height;
            this.blocks = blocks;
            this.blockData = blockData;
        }
    }
}
