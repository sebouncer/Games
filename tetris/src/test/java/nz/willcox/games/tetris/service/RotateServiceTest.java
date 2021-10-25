package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RotateServiceTest {

    private RotateService rotateService;

    @Before
    public void setup() {
        rotateService = new RotateService();
    }

    @Test
    public void rotateXFromPositiveToZero() {
        final LocationPoint locationPoint = new LocationPoint.Builder().topX(20).topY(0).build();
        final LocationPoint rotatePoint = new LocationPoint.Builder().topX(0).topY(0).build();

        rotateService.rotateBlock(locationPoint, rotatePoint);

        assertThat(locationPoint.getTopX(), is(0));
        assertThat(locationPoint.getTopY(), is(20));
    }

    @Test
    public void rotateYFromPositiveToZero() {
        final LocationPoint locationPoint = new LocationPoint.Builder().topX(0).topY(20).build();
        final LocationPoint rotatePoint = new LocationPoint.Builder().topX(0).topY(0).build();

        rotateService.rotateBlock(locationPoint, rotatePoint);

        assertThat(locationPoint.getTopX(), is(-20));
        assertThat(locationPoint.getTopY(), is(0));
    }
}
