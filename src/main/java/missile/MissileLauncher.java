package missile;

public class MissileLauncher {
    static void launchMissile(Missile missile, LaunchCode launchCode) {
        if (launchCode.isExpired() || !launchCode.isSigned()) {
            missile.disable();
            return;
        }

        missile.launch();
    }
}
