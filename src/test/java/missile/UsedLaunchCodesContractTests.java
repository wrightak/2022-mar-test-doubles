package missile;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UsedLaunchCodesContractTests {
    @Test
    void containsReturnsTrue_afterAdding() {
        FakeUsedLaunchCodes fakeUsedLaunchCodes = new FakeUsedLaunchCodes();
        GoodLaunchCodeStub launchCode = new GoodLaunchCodeStub();

        fakeUsedLaunchCodes.add(launchCode);

        assertThat(fakeUsedLaunchCodes.contains(launchCode)).isTrue();
    }

    @Test
    void containsReturnsFalse_afterNotAdding() {
        FakeUsedLaunchCodes fakeUsedLaunchCodes = new FakeUsedLaunchCodes();
        GoodLaunchCodeStub launchCode = new GoodLaunchCodeStub();

        assertThat(fakeUsedLaunchCodes.contains(launchCode)).isFalse();
    }
}
