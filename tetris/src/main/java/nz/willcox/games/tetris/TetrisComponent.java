package nz.willcox.games.tetris;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = TetrisModule.class)
public interface TetrisComponent {
    TetrisFrame getTetrisFrame();
}
