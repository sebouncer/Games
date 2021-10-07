package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;

import javax.inject.Inject;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameRunner {

    private final GameCreator gameCreator;
    private final CurrentShapeMovementService currentShapeMovementService;

    private GameData playerOneGameData;

    @Inject
    public GameRunner(
            GameCreator gameCreator,
            CurrentShapeMovementService currentShapeMovementService
    ) {
        this.gameCreator = gameCreator;
        this.currentShapeMovementService = currentShapeMovementService;
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
            currentShapeMovementService.moveCurrentShapeDown(playerOneGameData);
        }
    }
}
