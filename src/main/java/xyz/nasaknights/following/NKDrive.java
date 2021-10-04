
package xyz.nasaknights.following;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * Implement this with your drivetrain class to be compatible with
 * the "new" HelixFollower
 */
public interface NKDrive extends Subsystem {

    PIDController leftVelocityController = null;
    static PIDController rightVelocityController = null;

    /**
     * @return The maximum speed for your robot in feet per second.
     */
    public double getMaxFPS();

    /**
     * Resets the encoders for the drivetrain.
     */
    public void resetEncoders();

    /**
     * @see {@link Math#toRadians} for deg to rad conversion
     * @return the angle the robot is heading in (in radians)
     */
    public double getCurrentHeadingRadians();

    /**
     * @return the distance the left side of the drivetrain has traveled (in feet)
     */
    public double getLeftEncoderFeet();

    /**
     * @return the distance the right side of the drivetrain has traveled (in feet)
     */
    public double getRightEncoderFeet();

    /**
     * A method to encourage you to use a PID loop to make sure the left side
     * of the drivetrain is moving at the desired velocity. If you do not
     * implement a PID loop, your results will be wildly inconsistent.
     * @param velocity The target velocity in feet per second
     */
    public void setLeftVelocityFPS(double velocity);

    /**
     * A method to encourage you to use a PID loop to make sure the right side
     * of the drivetrain is moving at the desired velocity. If you do not
     * implement a PID loop, your results will be wildly inconsistent.
     * @param velocity The target velocity in feet per second
     */
    public void setRightVelocityFPS(double velocity);
}
