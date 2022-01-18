package xyz.nasaknights.util.tunable;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TunableRegistry extends SubsystemBase {

    private static final TunableRegistry instance;
    private ArrayList<Tunable> tunables;

    static {
        instance = new TunableRegistry();
    }

    private TunableRegistry() {
        tunables = new ArrayList<>();
    }

    private void add(Tunable tunable) {
        tunables.add(tunable);
    }

    public static void register(Tunable tunable) {
        instance.add(tunable);
    }

    @Override
    public void periodic() {
        instance.tunables.forEach(tunable -> {
            if (tunable.isTuningEnabled()) tunable.tune();
        });
    }
}