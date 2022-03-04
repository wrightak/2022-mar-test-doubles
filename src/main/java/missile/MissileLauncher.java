package missile;

public class MissileLauncher {
    static void launchMissile(Missile missile, LaunchCode launchCode) {
        if (launchCode.isExpired()) {
            return;
        }

        missile.launch();
    }
}
