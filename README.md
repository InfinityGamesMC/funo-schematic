# funo-schematic
A simple schematic library for GoMint.

### Loading a schematic
```java
FunoSchematic schematic = new FunoSchematic.Loader(new File("test.schematic")).load();

// To access schematic data
FunoSchematic.SchematicData data = schematic.getSchematicData();
```

### Creating a schematic
```java
// First method
FunoSchematic schematic = new FunoSchematic(new FunoSchematic.SchematicData(length, width, height));
FunoSchematic schematic = new FunoSchematic(new FunoSchematic.SchematicData(length, width, height, blocks));
FunoSchematic schematic = new FunoSchematic(new FunoSchematic.SchematicData(length, width, height, blocks, blockData));

// Second method
FunoSchematic.SchematicData data = new FunoSchematic.SchematicData();
data.length = 100;
data.width = 100;
data.height = 100;
data.blocks = new byte[]{1, 2, 3};
data.blockData = new byte[]{1, 2, 3};

FunoSchematic schematic = new FunoSchematic(data);
```

NOTE: Block ids and block data must be be at the same index in their respective byte arrays!


### Social Networks
[![Join the Discord](http://puu.sh/v9UB9/944431c790.png)](https://discord.gg/A4nXfEW)
[![Twitter](http://puu.sh/v9V9H/ad70c8acf7.png)](https://twitter.com/FunoNetwork)
