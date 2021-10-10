package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;
import nz.willcox.games.tetris.view.controls.PlayerEventListener;

import java.util.List;

import static nz.willcox.games.tetris.Constants.BLOCK_HEIGHT;
import static nz.willcox.games.tetris.Constants.BLOCK_WIDTH;
import static nz.willcox.games.tetris.Constants.NUM_COLUMNS;
import static nz.willcox.games.tetris.Constants.NUM_ROWS;

public class GamePlayerControlService implements PlayerEventListener {

    private static final int LEFT = -1;
    private static final int RIGHT = 1;

    private final GameData gameData;

    public GamePlayerControlService(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void leftAction() {
        System.out.println("Left!!!");
        moveDirection(LEFT);
    }

    private void moveDirection(int direction) {
        final List<ShapeBlock> shapeBlocks = gameData.getCurrentShape().getShapeBlocks();

        final List<Row> rowData = gameData.getRowData();

        boolean collision = false;

        for (ShapeBlock shapeBlock : shapeBlocks) {
            int col = getColumn(shapeBlock.getLocationPoint().getTopX());
            int topRow = getRow(getTopOfBlock(shapeBlock));
            int bottomRow = getRow(getBottomOfBlock(shapeBlock));

            // left movement
            col = col + direction;

            if (col < 0 || col >= NUM_COLUMNS) {
                collision = true;
            }
            collision = collision || rowCollision(rowData, topRow, col) || rowCollision(rowData, bottomRow, col);
        }

        if (!collision) {
            for (ShapeBlock shapeBlock : shapeBlocks) {
                shapeBlock.getLocationPoint().setTopX(shapeBlock.getLocationPoint().getTopX() + (BLOCK_WIDTH * direction));
            }
        }
    }

    private int getBottomOfBlock(ShapeBlock shapeBlock) {
        return getTopOfBlock(shapeBlock) + BLOCK_HEIGHT - 1;
    }

    private int getTopOfBlock(ShapeBlock shapeBlock) {
        return shapeBlock.getLocationPoint().getTopY();
    }

    private boolean rowCollision(List<Row> rowData, int row, int col) {
        if (row >= NUM_ROWS) {
            return false;
        }

        final Block topRowDirectionBlock = rowData.get(row).getBlocks().get(col);
        return (topRowDirectionBlock != BlockColours.EMPTY_BLOCK);
    }

    @Override
    public void downAction() {
        System.out.println("DOWN!!!");
    }

    @Override
    public void upAction() {
        System.out.println("Rotate!!!");
    }

    @Override
    public void rightAction() {
        System.out.println("Right!!!");
        moveDirection(RIGHT);
    }

    @Override
    public void buttonStart() {

    }

    @Override
    public void buttonOne() {

    }

    @Override
    public void buttonTwo() {

    }

    private int getColumn(int topX) {
        return topX / Constants.BLOCK_WIDTH;
    }

    private int getRow(int topY) {
        return NUM_ROWS-1 - (topY/Constants.BLOCK_HEIGHT);
    }
}
