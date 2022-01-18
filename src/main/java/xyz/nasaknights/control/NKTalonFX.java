package xyz.nasaknights.control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import xyz.nasaknights.util.tunable.NKTunableMotorBase;

public class NKTalonFX extends WPI_TalonFX implements NKTunableMotorBase {

    private ControlMode m_lastMode;
    private double m_lastOutput;
    private boolean m_lock = false;
    protected int m_id;
    private double m_minOutput;
    private double m_maxOutput;
    private boolean m_useOutputRange = false;

    public NKTalonFX(int deviceNumber) {
        super(deviceNumber);
        m_id = deviceNumber;
    }

    @Override
    public void set(ControlMode mode, double output) {
        if (mode != m_lastMode || output != m_lastOutput) {
            m_lastMode = mode;
            m_lastOutput = this.m_useOutputRange? Math.max(Math.min(m_maxOutput, output), m_minOutput) : output;
            super.set(mode, output);
        }
    }

    public void set(double power) {
        this.set(ControlMode.PercentOutput, power);
    }

    public double getPosition() {
        return super.getSensorCollection().getIntegratedSensorPosition();
    }

    public double getAbsolutePosition() {
        return super.getSensorCollection().getIntegratedSensorAbsolutePosition();
    }

    public double getVelocity() {
        return super.getSensorCollection().getIntegratedSensorVelocity();
    }

    public void resetPosition() {
        this.setPosition(0, 0);
    }

    public void setPosition(double newPosition, int timeoutMs) {
        super.getSensorCollection().setIntegratedSensorPosition(newPosition, timeoutMs);
    }

    @Override
    public void setP(int slot, double p) {
        this.config_kP(slot, p, 0);
    }

    @Override
    public void setI(int slot, double i) {
        this.config_kI(slot, i, 0);
    }
    @Override
    public void setD(int slot, double d) {
        this.config_kD(slot, d, 0);
    }

    @Override
    public void setF(int slot, double f) {
        this.config_kF(slot, f, 0);
    }

    @Override
    public void setIZone(int slot, int iZone) {
        this.config_IntegralZone(slot, iZone);
    }

    @Override
    public void setMaxIntegralAccumulator(int slot, double maxAccumulator) {
        this.configMaxIntegralAccumulator(slot, maxAccumulator);
    }

    @Override
    public void setOutputRange(double min, double max) {
        this.m_useOutputRange = true;
        this.m_minOutput = min;
        this.m_maxOutput = max;
    }

    @Override
    public boolean isTuningLocked() {
        return m_lock;
    }

    @Override
    public void setTuningLock(boolean lock) {
        this.m_lock = lock;
    }

    @Override
    public int getID() {
        return m_id;
    }

    @Override
    public void resetOutputRange() {
        this.m_useOutputRange = false;
    }
}