package xyz.nasaknights.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TunablePIDController extends PIDController {

    private String name;

    public TunablePIDController(double p, double i, double d) {
        super(p, i, d);
        this.name = "PID Tuning";
    }

    public void enableTuning() {
        new TuneCommand(this).schedule();
    }

    public void enableTuning(String name) {
        this.name = name;
        new TuneCommand(this).schedule();
    }

    private static class TuneCommand extends CommandBase {

        TunablePIDController tunablePIDController;
        private String pName, iName, dName;
        public TuneCommand(TunablePIDController tunablePIDController) {
            this.tunablePIDController = tunablePIDController;
            this.pName = this.tunablePIDController.name + " P Gain";
            this.iName = this.tunablePIDController.name + " I Gain";
            this.dName = this.tunablePIDController.name + " D Gain";
            updatePID();
        }

        void updatePID() {
            SmartDashboard.putNumber(this.pName, this.tunablePIDController.getP());
            SmartDashboard.putNumber(this.iName, this.tunablePIDController.getI());
            SmartDashboard.putNumber(this.dName, this.tunablePIDController.getD());
        }

        @Override
        public void execute() {
            final double p = SmartDashboard.getNumber(this.pName, 0);
            final double i = SmartDashboard.getNumber(this.iName, 0);
            final double d = SmartDashboard.getNumber(this.dName, 0);

            if ((p != this.tunablePIDController.getP())) this.tunablePIDController.setP(p);
            if ((i != this.tunablePIDController.getI())) this.tunablePIDController.setI(i);
            if ((d != this.tunablePIDController.getP())) this.tunablePIDController.setD(d);

            updatePID();
        }

    }

}