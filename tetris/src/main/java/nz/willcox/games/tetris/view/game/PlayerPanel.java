package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.model.Listener;
import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;
import nz.willcox.games.tetris.view.controls.PlayerControls;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nz.willcox.games.tetris.Constants.BLOCK_HEIGHT;
import static nz.willcox.games.tetris.Constants.BLOCK_WIDTH;
import static nz.willcox.games.tetris.Constants.NUM_COLUMNS;
import static nz.willcox.games.tetris.Constants.NUM_ROWS;

public class PlayerPanel extends JPanel implements Listener {

    private static final int BLOCK_BORDER_WIDTH = 1;
    public static final int WIDTH = BLOCK_WIDTH * NUM_COLUMNS;
    public static final int HEIGHT = BLOCK_HEIGHT * NUM_ROWS;

    private final GameData gameData;
    private final PlayerControls playerControls;
    private final HashMap<Block, Color> colourBlocks;

    public PlayerPanel(
            GameData gameData,
            PlayerControls playerControls
    ) {
        this.gameData = gameData;
        this.playerControls = playerControls;
        this.colourBlocks = new HashMap<>();
        defineBlockColors();
        gameData.getCurrentShape().addListener(this);
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBlocks(g);
        drawCurrentShape(g);
    }

    private void drawCurrentShape(Graphics g) {
        final CurrentShape currentShape = gameData.getCurrentShape();
        if (currentShape != null) {
            final List<ShapeBlock> shapeBlocks = currentShape.getShapeBlocks();
            for (ShapeBlock shapeBlock : shapeBlocks) {
                final Block block = shapeBlock.getBlock();

                final LocationPoint locationPoint = shapeBlock.getLocationPoint();

                drawBlock(g, block, locationPoint);
            }
        }
    }

    private void drawBlocks(Graphics g) {
        final List<Row> rowData = gameData.getRowData();
        for (int i = 0; i < rowData.size(); i++) {
            final List<Block> blocks = rowData.get(i).getBlocks();
            for (int j = 0; j < blocks.size(); j++) {

                final LocationPoint locationPoint = new LocationPoint.Builder()
                        .topX(j*BLOCK_WIDTH)
                        .topY(HEIGHT - (i+1)*BLOCK_HEIGHT)
                        .build();
                drawBlock(g, blocks.get(j), locationPoint);
            }
        }
    }

    private void drawBlock(Graphics g, Block block, LocationPoint locationPoint) {
        g.setColor(getBlockBorderColour(block));
        g.fillRect(
                locationPoint.getTopX(),
                locationPoint.getTopY(),
                BLOCK_WIDTH,
                BLOCK_HEIGHT
        );
        g.setColor(getBlockColour(block));
        g.fillRect(
                locationPoint.getTopX() + BLOCK_BORDER_WIDTH,
                locationPoint.getTopY() + BLOCK_BORDER_WIDTH,
                BLOCK_WIDTH - (2*BLOCK_BORDER_WIDTH),
                BLOCK_HEIGHT -(2*BLOCK_BORDER_WIDTH)
        );
    }

    private Color getBlockColour(Block block) {
        return colourBlocks.get(block);
    }

    private Color getBlockBorderColour(Block block) {
        if (block != BlockColours.EMPTY_BLOCK) {
            return Color.BLACK;
        }
        return Color.LIGHT_GRAY;
    }

    public void destroy() {
        gameData.getCurrentShape().removeListener(this);
    }

    @Override
    public void eventTrigger() {
        repaint();
    }

    public static class Factory {
        public PlayerPanel create(
                GameData gameData,
                PlayerControls playerControls
        ) {
            return new PlayerPanel(gameData, playerControls);
        }
    }
}
