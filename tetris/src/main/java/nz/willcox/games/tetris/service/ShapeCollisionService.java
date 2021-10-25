package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;
import nz.willcox.games.tetris.model.game.shape.TetrisShape;

import javax.inject.Inject;
import java.util.List;

import static nz.willcox.games.tetris.Constants.BLOCK_HEIGHT;
import static nz.willcox.games.tetris.Constants.NUM_COLUMNS;
import static nz.willcox.games.tetris.Constants.NUM_ROWS;

public class ShapeCollisionService {

    @Inject
    public ShapeCollisionService() {}

    public boolean doesShapeCollide(GameData gameData, TetrisShape tetrisShape) {

        final List<Row> rowData = gameData.getRowData();

        boolean collision = false;

        for (ShapeBlock shapeBlock : tetrisShape.getShapeBlocks()) {
            int col = getColumn(shapeBlock.getLocationPoint().getTopX());
            int topRow = getRow(getTopOfBlock(shapeBlock));
            int bottomRow = getRow(getBottomOfBlock(shapeBlock));

            if (col < 0 || col >= NUM_COLUMNS) {
                collision = true;
            }
            collision = collision || rowCollision(rowData, topRow, col) || rowCollision(rowData, bottomRow, col);
        }

        return collision;
    }

    private boolean rowCollision(List<Row> rowData, int row, int col) {
        if (row >= NUM_ROWS) {
            return false;
        }

        final Block topRowDirectionBlock = rowData.get(row).getBlocks().get(col);
        return (topRowDirectionBlock != BlockColours.EMPTY_BLOCK);
    }

    private int getBottomOfBlock(ShapeBlock shapeBlock) {
        return getTopOfBlock(shapeBlock) + BLOCK_HEIGHT - 1;
    }

    private int getTopOfBlock(ShapeBlock shapeBlock) {
        return shapeBlock.getLocationPoint().getTopY();
    }

    private int getColumn(int topX) {
        return topX / Constants.BLOCK_WIDTH;
    }

    private int getRow(int topY) {
        return NUM_ROWS-1 - (topY/Constants.BLOCK_HEIGHT);
    }
}
