package xyz.nasaknights.following;

import com.team2363.commands.HelixFollower;
import com.team2363.controller.PIDController;
import com.team319.trajectory.Path;

import edu.wpi.first.math.MathUtil;

import static xyz.nasaknights.following.NKFollower.getDrivetrain;

public class NKHelixHelper extends HelixFollower {

    protected PIDController distanceController = new PIDController(10, 0, 0, 0.001);
    protected PIDController headingController = new PIDController(15, 0, 0, 0.001);

    @SuppressWarnings("all")
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
        getDrivetrain().setLeftVelocityFPS(MathUtil.clamp(left, -m_maxFPS, m_maxFPS));
        getDrivetrain().setRightVelocityFPS(MathUtil.clamp(right, -m_maxFPS, m_maxFPS));
    }
}