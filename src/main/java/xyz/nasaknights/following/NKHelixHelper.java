package xyz.nasaknights.following;

import com.team2363.commands.HelixFollower;
import com.team2363.controller.PIDController;
import com.team319.trajectory.Path;

import static xyz.nasaknights.following.NKFollower.getDrivetrain;

public class NKHelixHelper extends HelixFollower {

    private PIDController distanceController = new PIDController(10, 0, 0, 0.001);
    private PIDController headingController = new PIDController(15, 0, 0, 0.001);

    private static double m_maxFPS;

    public NKHelixHelper(Path path) {
        super(path);
        m_maxFPS = getDrivetrain().getMaxFPS();
    }

    public final void init() {
        super.initialize();
    }

    public final void exec() {
        super.execute();
    }

    public final void finish(final boolean interrupted) {
        super.end();
    }

    public final boolean isDone() {
        return super.isFinished();
    }

    /**
     * Sets the proportonial gain for the distance PID controller
     * @param p the new proportional gain
     */
    public final void setDistanceControllerP(final double p) {
        distanceController.setP(p);
    }

    /**
     * Sets the proportonial gain for the heading PID controller
     * @param p the new proportional gain
     */
    public final void setHeadingControllerP(final double p) {
        headingController.setP(p);
    }

    @Override
    public final void resetDistance() {
        getDrivetrain().resetEncoders();
    }

    @Override
    public final PIDController getHeadingController() {
        return headingController;
    }

    @Override
    public final PIDController getDistanceController() {
        return distanceController;
    }

    @Override
    public final double getCurrentDistance() {
        return (getDrivetrain().getLeftEncoderFeet() + getDrivetrain().getRightEncoderFeet()) / 2.0;
    }

    @Override
    public final double getCurrentHeading() {
        return getDrivetrain().getCurrentHeadingRadians();
    }

    @Override
    public final void useOutputs(final double left, final double right) {
        getDrivetrain().tankDrive(left / m_maxFPS, right / m_maxFPS);
    }
}