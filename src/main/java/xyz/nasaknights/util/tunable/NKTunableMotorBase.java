package xyz.nasaknights.util.tunable;

public interface NKTunableMotorBase extends NKMotorBase {
    public boolean isTuningLocked();
    public void setTuningLock(boolean lock);
}