package missile;

import static org.assertj.core.api.Assertions.assertThat;

public class MissileMock implements Missile {
    private boolean launchWasCalled = false;
    private boolean disableWasCalled = false;

    @Override
    public void launch() {
        launchWasCalled = true;
    }

    @Override
    public void disable() {
        disableWasCalled = true;
    }

    void verifyCodeRedAbort() {
        assertThat(launchWasCalled).isFalse();
        assertThat(disableWasCalled).isTrue();
    }
}
