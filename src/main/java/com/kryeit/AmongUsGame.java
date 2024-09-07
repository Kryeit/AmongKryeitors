package com.kryeit;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import xyz.nucleoid.fantasy.RuntimeWorldConfig;
import xyz.nucleoid.map_templates.MapTemplate;
import xyz.nucleoid.plasmid.game.GameOpenContext;
import xyz.nucleoid.plasmid.game.GameOpenProcedure;
import xyz.nucleoid.plasmid.game.world.generator.TemplateChunkGenerator;

public class AmongUsGame {
    public static GameOpenProcedure open(GameOpenContext<AmongUsGameConfig> context) {
        // get our config that got loaded by Plasmid
        AmongUsGameConfig config = context.config();

        // create a very simple map with a stone block at (0; 64; 0)
        MapTemplate template = MapTemplate.createEmpty();
        template.setBlockState(new BlockPos(0, 64, 0), Blocks.STONE.getDefaultState());

        // create a chunk generator that will generate from this template that we just created
        TemplateChunkGenerator generator = new TemplateChunkGenerator(context.server(), template);

        // set up how the world that this minigame will take place in should be constructed
        RuntimeWorldConfig worldConfig = new RuntimeWorldConfig()
                .setGenerator(generator)
                .setTimeOfDay(6000);

        return context.openWithWorld(worldConfig, (activity, world) -> {
            // to be implemented
        });
    }
}
