package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.model.game.Block;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class BlocksCreator {

    private static final int BLANK_BLOCK_INDEX = 0;
    private static final int NUMBER_OF_COLOR_BLOCKS = 5;

    private final List<Block> blocks;

    @Inject
    public BlocksCreator() {
        blocks = new ArrayList<>();
        for (int i = 0; i <= NUMBER_OF_COLOR_BLOCKS; i++) {
            blocks.add(new Block.Builder().colourNumber(i).build());
        }
    }

    public Block getBlankBlock() {
        return blocks.get(BLANK_BLOCK_INDEX);
    }
}
