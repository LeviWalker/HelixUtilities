package xyz.nasaknights.util;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TunableFeedForward extends SimpleMotorFeedforward {

    private String name;
    private double sGain, vGain, aGain;

    public TunableFeedForward(double kS, double kV, double kA) {
        super(kS, kV, kA);
        this.name = "FF Tuning";
    }

    public TunableFeedForward(double kS, double kV) {
        this(kS, kV, 0);
    }

    public void enableTuning() {
        new TuneCommand(this).schedule();
    }

    public void enableTuning(String name) {
        this.name = name;
        new TuneCommand(this).schedule();
    }

    public void setS(double s) {
        sGain = s;
    }

    public void setV(double v) {
        vGain = v;
    }

    public void setA(double a) {
        aGain = a;
    }

    public double getS() {
        return sGain;
    }

    public double getV() {
        return vGain;
    }

    public double getA() {
        return aGain;
    }

    @Override
    public double calculate(double velocity, double acceleration) {
        return (sGain * Math.signum(velocity)) + (vGain * velocity) + (aGain * acceleration);
    }

    @Override
    public double calculate(double velocity) {
        return this.calculate(velocity, 0);
    }

    @Override
    public double maxAchievableVelocity(double maxVoltage, double acceleration) {
        // Assume max velocity is positive
        return (maxVoltage - sGain - acceleration * aGain) / vGain;
    }

    @Override
    public double minAchievableVelocity(double maxVoltage, double acceleration) {
        // Assume min velocity is negative, sGain flips sign
        return (-maxVoltage + sGain - acceleration * aGain) / vGain;
    }

    @Override
    public double maxAchievableAcceleration(double maxVoltage, double velocity) {
        return (maxVoltage - sGain * Math.signum(velocity) - velocity * vGain) / aGain;
    }

    @Override
    public double minAchievableAcceleration(double maxVoltage, double velocity) {
        return this.maxAchievableAcceleration(-maxVoltage, velocity);
    }

    private static class TuneCommand extends CommandBase {
        TunableFeedForward tunableFeedForward;
        private String sName, vName, aName;
        public TuneCommand(TunableFeedForward tunableFeedForward) {
            this.tunableFeedForward = tunableFeedForward;
            this.sName = this.tunableFeedForward.name + " S Gain";
            this.vName = this.tunableFeedForward.name + " V Gain";
            this.aName = this.tunableFeedForward.name + " A Gain";
            updateFF();
        }

        void updateFF() {
            SmartDashboard.putNumber(this.sName, this.tunableFeedForward.getS());
            SmartDashboard.putNumber(this.vName, this.tunableFeedForward.getV());
            SmartDashboard.putNumber(this.aName, this.tunableFeedForward.getA());
        }

        @Override
        public void execute() {
            final double s = SmartDashboard.getNumber(this.sName, 0);
            final double v = SmartDashboard.getNumber(this.vName, 0);
            final double a = SmartDashboard.getNumber(this.aName, 0);

            if ((s != this.tunableFeedForward.getS())) this.tunableFeedForward.setS(s);
            if ((v != this.tunableFeedForward.getV())) this.tunableFeedForward.setV(v);
            if ((a != this.tunableFeedForward.getA())) this.tunableFeedForward.setA(a);

            updateFF();
        }

    }

}