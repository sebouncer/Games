package nz.willcox.games.tetris;

public class Application {

    private final TetrisFrame tetrisFrame;

    public Application(TetrisFrame tetrisFrame) {
        this.tetrisFrame = tetrisFrame;
    }

    public static void main(String[] args) {
        final TetrisComponent tetrisComponent = DaggerTetrisComponent.create();

        tetrisComponent.getTetrisFrame().setup();
    }
}
