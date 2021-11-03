package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerBlockColours {

    private final Map<Block, Color> colourBlocks;

    public PlayerBlockColours() {
        colourBlocks = new HashMap<>();
        defineBlockColors();
    }

    private void defineBlockColors() {
        colourBlocks.put(BlockColours.EMPTY_BLOCK, Color.WHITE);
        colourBlocks.put(BlockColours.YELLOW_BLOCK, Color.YELLOW);
        colourBlocks.put(BlockColours.GREEN_BLOCK, Color.GREEN);
        colourBlocks.put(BlockColours.ORANGE_BLOCK, Color.ORANGE);
        colourBlocks.put(BlockColours.DARK_BLUE_BLOCK, new Color(0, 0,255));
        colourBlocks.put(BlockColours.RED_BLOCK, Color.RED);
        colourBlocks.put(BlockColours.CYAN_BLOCK, Color.CYAN);
        colourBlocks.put(BlockColours.PURPLE_BLOCK, new Color(102, 0,153));
    }

    public Color getBlockColour(Block block) {
        return colourBlocks.get(block);
    }

    public Color getBlockBorderColour(Block block) {
        if (block != BlockColours.EMPTY_BLOCK) {
            return Color.BLACK;
        }
        return Color.LIGHT_GRAY;
    }
}
