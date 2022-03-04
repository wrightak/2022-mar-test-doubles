package missile;

import org.junit.jupiter.api.Test;

import static missile.MissileLauncher.launchMissile;
import static org.assertj.core.api.Assertions.assertThat;

class MissileLauncherTest {
    @Test
    void givenGoodLaunchCodes_MissileIsLaunched() {
        MissileSpy missileSpy = new MissileSpy();

        launchMissile(missileSpy, new GoodLaunchCodeStub(), new FakeUsedLaunchCodes());

        assertThat(missileSpy.launchWasCalled()).isTrue();
    }

    @Test
    void givenExpiredLaunchCodes_MissileIsNotLaunched_dummy() {
        launchMissile(new DummyMissile(), new ExpiredLaunchCodeStub(), new FakeUsedLaunchCodes());
    }

    @Test
    void givenExpiredLaunchCodes_MissileIsNotLaunchedAndDisabled_Spy() {
        MissileSpy missileSpy = new MissileSpy();

        launchMissile(missileSpy, new ExpiredLaunchCodeStub(), new FakeUsedLaunchCodes());

        assertThat(missileSpy.launchWasCalled()).isFalse();
        assertThat(missileSpy.disableWasCalled()).isTrue();
    }

    @Test
    void givenUnsignedLaunchCodes_MissileIsNotLaunchedAndDisabled_Spy() {
        MissileSpy missileSpy = new MissileSpy();

        launchMissile(missileSpy, new UnsignedLaunchCodeStub(), new FakeUsedLaunchCodes());

        assertThat(missileSpy.launchWasCalled()).isFalse();
        assertThat(missileSpy.disableWasCalled()).isTrue();
    }

    @Test
    void givenExpiredLaunchCodes_CodeRedAbort_Mock() {
        MissileMock missileMock = new MissileMock();

        launchMissile(missileMock, new ExpiredLaunchCodeStub(), new FakeUsedLaunchCodes());

        missileMock.verifyCodeRedAbort();
    }

    @Test
    void givenUnsignedLaunchCodes_CodeRedAbort_Mock() {
        MissileMock missileMock = new MissileMock();

        launchMissile(missileMock, new UnsignedLaunchCodeStub(), new FakeUsedLaunchCodes());

        missileMock.verifyCodeRedAbort();
    }

    @Test
    void reusedLaunchCode_CodeRedAbort() {
        GoodLaunchCodeStub launchCode = new GoodLaunchCodeStub();
        MissileMock missileMock1 = new MissileMock();
        MissileMock missileMock2 = new MissileMock();
        FakeUsedLaunchCodes fakeUsedLaunchCodes = new FakeUsedLaunchCodes();

        launchMissile(missileMock1, launchCode, fakeUsedLaunchCodes);
        launchMissile(missileMock2, launchCode, fakeUsedLaunchCodes);

        missileMock2.verifyCodeRedAbort();
    }
}
