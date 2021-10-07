package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.factory.SquareFactory;
import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static nz.willcox.games.tetris.Constants.NUM_COLUMNS;
import static nz.willcox.games.tetris.Constants.NUM_ROWS;
import static nz.willcox.games.tetris.model.game.BlockColours.EMPTY_BLOCK;

public class GameCreator {

    private static final int INITIAL_SCORE = 0;
    private final SquareFactory squareFactory;

    @Inject
    public GameCreator(SquareFactory squareFactory) {
        this.squareFactory = squareFactory;
    }

    public GameData createGame() {


        final LocationPoint startMidLocation = new LocationPoint.Builder()
                .topX((NUM_COLUMNS -1)/2 * Constants.BLOCK_WIDTH)
                .topY(0 - Constants.BLOCK_HEIGHT)
                .build();

        final List<ShapeBlock> shapeBlocks = squareFactory.create(startMidLocation);

        final CurrentShape currentShape = new CurrentShape();
        currentShape.setNewShapeBlocks(shapeBlocks);

        final List<Row> rowData = createRows();
        return new GameData.Builder()
                .score(INITIAL_SCORE)
                .rowData(rowData)
                .currentShape(currentShape)
                .build();
    }

    private List<Row> createRows() {
        final List<Row> rowData = new ArrayList<>();
        for (int i = 0; i < NUM_ROWS; i++) {
            rowData.add(createRow());
        }
        return rowData;
    }

    private Row createRow() {
        final List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < NUM_COLUMNS; i++) {
            blocks.add(EMPTY_BLOCK);
        }
        return new Row.Builder().blocks(blocks).build();
    }
}
