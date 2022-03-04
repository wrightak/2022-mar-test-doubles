package missile;

import org.junit.jupiter.api.Test;

import static missile.MissileLauncher.launchMissile;
import static org.assertj.core.api.Assertions.assertThat;

class MissileLauncherTest {
    @Test
    void givenGoodLaunchCodes_MissileIsLaunched() {
        // arrange
        MissileSpy missileSpy = new MissileSpy();

        // act
        launchMissile(missileSpy, new GoodLaunchCodeStub());

        // assert
        assertThat(missileSpy.launchWasCalled()).isTrue();
    }

    @Test
    void givenExpiredLaunchCodes_MissileIsNotLaunched_dummy() {
        launchMissile(new DummyMissile(), new ExpiredLaunchCodeStub());
    }

    @Test
    void givenExpiredLaunchCodes_MissileIsNotLaunched_Spy() {
        // arrange
        MissileSpy missileSpy = new MissileSpy();

        // act
        launchMissile(missileSpy, new ExpiredLaunchCodeStub());

        // assert
        assertThat(missileSpy.launchWasCalled()).isFalse();
    }
}
