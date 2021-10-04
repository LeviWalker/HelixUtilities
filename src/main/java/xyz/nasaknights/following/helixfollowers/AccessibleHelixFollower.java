package xyz.nasaknights.following.helixfollowers;

import com.team2363.commands.HelixFollower;
import com.team2363.controller.PIDController;
import com.team319.trajectory.Path;

/**
 * HelixFollower but command methods are available to call outside the class.
 * However, they are not overridable (except for the {@link #interrupted()}
 * method), so you can pick your own poison.
 */
public abstract class AccessibleHelixFollower extends HelixFollower {

    private PIDController distanceController = new PIDController(0, 0, 0, 0.001);
    private PIDController headingController = new PIDController(0, 0, 0, 0.001);

    public AccessibleHelixFollower(Path path) {
        super(path);
    }

    @Override
    public final void initialize() {
        super.initialize();
    }

    @Override
    public final void execute() {
        super.execute();
    }

    @Override
    public final boolean isFinished() {
        return super.isFinished();
    }

    @Override
    public final void end() {
        super.end();
    }

    @Override
    public void interrupted() {
        super.end();
    }

    @Override
    public PIDController getDistanceController() {
        return distanceController;
    }

    @Override
    public PIDController getHeadingController() {
        return headingController;
    }

    /**
     * Sets the proportonial gain for the distance PID controller
     * @param p the new proportional gain
     */
    public final AccessibleHelixFollower setDistanceControllerP(final double p) {
        distanceController.setP(p);
        return this;
    }

    /**
     * Sets the proportonial gain for the heading PID controller
     * @param p the new proportional gain
     */
    public final AccessibleHelixFollower setHeadingControllerP(final double p) {
        headingController.setP(p);
        return this;
    }

}