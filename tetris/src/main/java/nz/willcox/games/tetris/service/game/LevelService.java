package nz.willcox.games.tetris.service.game;

import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Level;
import nz.willcox.games.tetris.model.game.Lines;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LevelService {

    private static final int NUM_LINES_PER_LEVEL = 10;

    @Inject
    public LevelService() {}

    public void updateLevel(GameData gameData) {
        final Lines lines = gameData.getLines();
        final Level level = gameData.getLevel();

        final int newLevel = lines.getLines()/NUM_LINES_PER_LEVEL;
        level.setLevel(newLevel);
    }
}
