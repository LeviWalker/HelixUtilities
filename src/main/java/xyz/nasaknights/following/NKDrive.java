
package xyz.nasaknights.following;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Implement this with your drivetrain class to be compatible with
 * the "new" HelixFollower
 */
public interface NKDrive {

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
     * A method that will power the left and right sides of the drivetrain
     * @see {@link DifferentialDrive#tankDrive} (This is your best option!)
     */
    public void tankDrive(double left, double right);
}
