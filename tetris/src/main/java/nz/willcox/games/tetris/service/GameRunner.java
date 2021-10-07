package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import javax.inject.Inject;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameRunner {

    private final GameCreator gameCreator;
    private final CurrentShapeMovementService currentShapeMovementService;
    private final RandomNextShapeService randomNextShapeService;

    private GameData playerOneGameData;

    @Inject
    public GameRunner(
            GameCreator gameCreator,
            CurrentShapeMovementService currentShapeMovementService,
            RandomNextShapeService randomNextShapeService
    ) {
        this.gameCreator = gameCreator;
        this.currentShapeMovementService = currentShapeMovementService;
        this.randomNextShapeService = randomNextShapeService;
    }

    public void initialiseOnePlayer() {
        playerOneGameData = gameCreator.createGame();

        final List<Row> rowData = playerOneGameData.getRowData();
        rowData.get(1).getBlocks().set(4, BlockColours.GREEN_BLOCK);


        new Timer().scheduleAtFixedRate(new GameActionTimerTask(), 1000, 100);
    }

    public GameData getPlayerOneGameData() {
        return playerOneGameData;
    }

    private class GameActionTimerTask extends TimerTask {

        @Override
        public void run() {

            if (!playerOneGameData.getCurrentShape().hasShape()) {
                final List<ShapeBlock> nextShapeBlocks = playerOneGameData.getNextShape().getShapeBlocks();
                playerOneGameData.getCurrentShape().setNewShapeBlocks(nextShapeBlocks);
                playerOneGameData.getNextShape().removeBlocks();
                playerOneGameData.getNextShape().setNewShapeBlocks(randomNextShapeService.getRandomNextShapeBlocks());

            }

            currentShapeMovementService.moveCurrentShapeDown(playerOneGameData);
        }
    }
}
