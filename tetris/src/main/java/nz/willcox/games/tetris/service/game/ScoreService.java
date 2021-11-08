package nz.willcox.games.tetris.service.game;

import nz.willcox.games.tetris.model.game.Score;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ScoreService {

    private final int[] scoringMap;

    @Inject
    public ScoreService() {
        scoringMap = new int[5];
        scoringMap[0] = 0;
        scoringMap[1] = 40;
        scoringMap[2] = 100;
        scoringMap[3] = 300;
        scoringMap[4] = 1200;
    }

    public void addLinesToScore(Score score, int numOfLines) {
        int scoreToAdd = scoringMap[numOfLines];
        score.setScore(score.getScore() + scoreToAdd);
    }
}
