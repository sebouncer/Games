package nz.willcox.games.tetris.service.game;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;
import nz.willcox.games.tetris.model.game.shape.TetrisShape;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

import static nz.willcox.games.tetris.Constants.BLOCK_HEIGHT;
import static nz.willcox.games.tetris.Constants.BLOCK_WIDTH;
import static nz.willcox.games.tetris.Constants.NUM_COLUMNS;
import static nz.willcox.games.tetris.Constants.NUM_ROWS;

@Singleton
public class ShapeMovementService {

    private static final int DOWNWARDS_MOVEMENT = Constants.BLOCK_HEIGHT/4;

    @Inject
    public ShapeMovementService() {}

    public boolean willCollideOnMoveDown(GameData gameData) {
        final CurrentShape currentShape = gameData.getCurrentShape();
        final List<Row> rowData = gameData.getRowData();
        return willCollideOnMoveDown(currentShape, rowData);
    }

    public boolean willCollideOnMoveDown(CurrentShape currentShape, List<Row> rowData) {
        for (ShapeBlock shapeBlock : currentShape.getShapeBlocks()) {
            final int newLocationYPoint = getNewLocationYPointDown(shapeBlock);
            boolean collision = checkCollisions(rowData, shapeBlock.getLocationPoint().getTopX(), newLocationYPoint);
            if (collision) {
                return true;
            }
        }
        return false;
    }

    public void moveDown(CurrentShape currentShape) {
        final TetrisShape tetrisShape = currentShape.getTetrisShape();
        for (ShapeBlock shapeBlock : tetrisShape.getShapeBlocks()) {
            incrementLocationPointDown(shapeBlock.getLocationPoint());
        }
        incrementLocationPointDown(tetrisShape.getRotateLocationPoint());
    }

    private void incrementLocationPointDown(LocationPoint locationPoint) {
        locationPoint.setTopY(getNewLocationYPointDown(locationPoint));
    }

    private int getNewLocationYPointDown(ShapeBlock shapeBlock) {
        final LocationPoint locationPoint = shapeBlock.getLocationPoint();
        return getNewLocationYPointDown(locationPoint);
    }

    private int getNewLocationYPointDown(LocationPoint locationPoint) {
        return locationPoint.getTopY() + DOWNWARDS_MOVEMENT;
    }

    private boolean checkCollisions(
            List<Row> rowData,
            int newLocationXPoint,
            int newLocationYPoint
    ) {
        final int column = getColumn(newLocationXPoint);
        final int row = getRow(newLocationYPoint + BLOCK_HEIGHT - 1);

        if (row < 0) {
            return true;
        }

        if (row >= NUM_ROWS) {
            return false;
        }

        final Block block = rowData.get(row).getBlocks().get(column);
        if (block != BlockColours.EMPTY_BLOCK) {
            return true;
        }

        return false;
    }

    public boolean willCollideOnMoveSideways(GameData gameData, int sidewaysDirection) {
        final List<ShapeBlock> shapeBlocks = gameData.getCurrentShape().getShapeBlocks();

        final List<Row> rowData = gameData.getRowData();

        boolean collision = false;

        for (ShapeBlock shapeBlock : shapeBlocks) {
            int col = getColumn(shapeBlock.getLocationPoint().getTopX());
            int topRow = getRow(getTopOfBlock(shapeBlock));
            int bottomRow = getRow(getBottomOfBlock(shapeBlock));

            // left movement
            col = col + sidewaysDirection;

            if (col < 0 || col >= NUM_COLUMNS) {
                collision = true;
            }
            collision = collision || rowCollision(rowData, topRow, col) || rowCollision(rowData, bottomRow, col);
        }
        return collision;
    }

    public void moveSidewaysDirection(GameData gameData, int sidewaysDirection) {
        final TetrisShape tetrisShape = gameData.getCurrentShape().getTetrisShape();
        final int moveSideways = BLOCK_WIDTH * sidewaysDirection;
        for (ShapeBlock shapeBlock : tetrisShape.getShapeBlocks()) {
            moveSideways(shapeBlock.getLocationPoint(), moveSideways);
        }
        moveSideways(tetrisShape.getRotateLocationPoint(), moveSideways);
    }

    private void moveSideways(LocationPoint locationPoint, int moveSideways) {
        locationPoint.setTopX(locationPoint.getTopX() + moveSideways);
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
