package xyz.nasaknights.following;

import java.util.function.Consumer;

import com.team319.trajectory.Path;

import edu.wpi.first.wpilibj2.command.FunctionalCommand;

import xyz.nasaknights.following.helixfollowers.AccessibleHelixFollower;

/**
 * Uses a custom version of HelixFollower where the Command
 * methods are accessible outside of the class and its subclasses.
 * This helps reduce the amount of memory NKFollower2 takes over
 * that of the original NKFollower and this can be implemented
 * differently, according to preference.
 * @see {@link AccessibleHelixFollower} 
 */
public class NKFollower2 extends FunctionalCommand {
    private Consumer<Double> m_setDistP, m_setHeadingP;

    public NKFollower2(AccessibleHelixFollower follower) {
        super(follower::initialize,
              follower::execute,
              interrupted -> {
                  if (interrupted) follower.interrupted();
                  else follower.end();
              },
              follower::isFinished);

        m_setDistP = follower::setDistanceControllerP;
        m_setHeadingP = follower::setHeadingControllerP;
    }

    /**
     * NKFollower2 constructor that is compatible with NKDrive.
     * @param drive The drivetrain object or just any instance
     *              of NKDrive
     * @param path Path to follow
     */
    public NKFollower2(NKDrive drive, Path path) {
        this(createAccessibleHelixFollower(drive, path));
    }

    /**
     * NKFollower2 constructor that is compatible with NKDrive
     * and specified PID values for the distance and heading PID
     * controllers.
     * @param drive The drivetrain object or just any instance
     *              of NKDrive
     * @param path Path to follow
     * @param distP the proportional gain for the distance
     *              controller
     * @param headingP the proportional gain for the distance
     *                 controller
     */
    public NKFollower2(NKDrive drive, Path path, double distP, double headingP) {
        this(createAccessibleHelixFollower(drive, path)
                .setDistanceControllerP(distP)
                .setHeadingControllerP(headingP)
        );
    }

    public void setDistanceControllerP(double p) {
        m_setDistP.accept(p);
    }

    public void setHeadingControllerP(double p) {
        m_setHeadingP.accept(p);
    }

    private static AccessibleHelixFollower createAccessibleHelixFollower(NKDrive drive, Path path) {
        return new AccessibleHelixFollower(path) {
            @Override
            public void useOutputs(double left, double right) {
                drive.setLeftVelocityFPS(left);
                drive.setRightVelocityFPS(right);
            }
        
            @Override
            public void resetDistance() {
                drive.resetEncoders();
            }
        
            @Override
            public double getCurrentHeading() {
                return drive.getCurrentHeadingRadians();
            }
        
            @Override
            public double getCurrentDistance() {
                return (drive.getLeftEncoderFeet() + drive.getRightEncoderFeet())/2;
            }
        };
    }
}