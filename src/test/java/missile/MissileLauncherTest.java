package missile;

import org.junit.jupiter.api.Test;

import static missile.MissileLauncher.launchMissile;
import static org.assertj.core.api.Assertions.assertThat;

class MissileLauncherTest {
    @Test
    void givenGoodLaunchCodes_MissileIsLaunched() {
        MissileSpy missileSpy = new MissileSpy();

        launchMissile(missileSpy, new GoodLaunchCodeStub());

        assertThat(missileSpy.launchWasCalled()).isTrue();
    }

    @Test
    void givenExpiredLaunchCodes_MissileIsNotLaunched_dummy() {
        launchMissile(new DummyMissile(), new ExpiredLaunchCodeStub());
    }

    @Test
    void givenExpiredLaunchCodes_MissileIsNotLaunchedAndDisabled_Spy() {
        MissileSpy missileSpy = new MissileSpy();

        launchMissile(missileSpy, new ExpiredLaunchCodeStub());

        assertThat(missileSpy.launchWasCalled()).isFalse();
        assertThat(missileSpy.disableWasCalled()).isTrue();
    }

    @Test
    void givenUnsignedLaunchCodes_MissileIsNotLaunchedAndDisabled_Spy() {
        MissileSpy missileSpy = new MissileSpy();

        launchMissile(missileSpy, new UnsignedLaunchCodeStub());

        assertThat(missileSpy.launchWasCalled()).isFalse();
        assertThat(missileSpy.disableWasCalled()).isTrue();
    }

    @Test
    void givenExpiredLaunchCodes_CodeRedAbort_Mock() {
        MissileMock missileMock = new MissileMock();

        launchMissile(missileMock, new ExpiredLaunchCodeStub());

        missileMock.verifyCodeRedAbort();
    }

    @Test
    void givenUnsignedLaunchCodes_CodeRedAbort_Mock() {
        MissileMock missileMock = new MissileMock();

        launchMissile(missileMock, new UnsignedLaunchCodeStub());

        missileMock.verifyCodeRedAbort();
    }
}
