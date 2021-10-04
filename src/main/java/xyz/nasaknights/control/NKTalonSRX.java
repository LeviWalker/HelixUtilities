package xyz.nasaknights.control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class NKTalonSRX extends WPI_TalonSRX {

    private ControlMode m_lastMode;
    private double m_lastOutput;

    public NKTalonSRX(int deviceNumber) {
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
}