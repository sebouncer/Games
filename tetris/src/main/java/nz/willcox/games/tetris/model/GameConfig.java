package nz.willcox.games.tetris.model;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GameConfig {

    private int numberOfPlayers = 0;

    @Inject
    public GameConfig() {}

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
