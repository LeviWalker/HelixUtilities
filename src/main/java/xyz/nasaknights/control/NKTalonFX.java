package xyz.nasaknights.control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class NKTalonFX extends WPI_TalonFX {

    private ControlMode m_lastMode;
    private double m_lastOutput;

    public NKTalonFX(int deviceNumber) {
        super(deviceNumber);
    }

    @Override
    public void set(ControlMode mode, double output) {
        if (mode != m_lastMode || output != m_lastOutput) {
            m_lastMode = mode;
            m_lastOutput = output;
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
}