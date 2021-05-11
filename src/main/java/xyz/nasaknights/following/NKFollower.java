package xyz.nasaknights.following;

import com.team319.trajectory.Path;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * NASAKnight's implementation of HelixFollower grafted onto the "new"
 * command structure for 2020 and after.
 */
public class NKFollower extends CommandBase {
    private NKHelixHelper m_helixHelper;

    private static NKDrive m_drivetrain;

    public static final void initNKFollower(NKDrive drivetrain) {
        m_drivetrain = drivetrain;
    }

    public static final NKDrive getDrivetrain() {
        if (m_drivetrain == null)
            throw new NullPointerException("Failed to initialize the HelixHelper drivetrain."
                                            + "Please call the NKHelixHelper.initHelixHelper"
                                            + "method. Thank you for using HelixHelper.");
        return m_drivetrain;
    }

    protected NKFollower(final NKHelixHelper helper) {
        m_helixHelper = helper;
    }

    @Override
    public final void initialize() {
        m_helixHelper.init();
    }

    @Override
    public final void execute() {
        m_helixHelper.exec();
    }

    @Override
    public final void end(boolean interrupted) {
        m_helixHelper.finish(interrupted);
    }

    public final boolean isFinished() {
        return m_helixHelper.isDone();
    }

    public final void setDistanceControllerP(final double p) {
        m_helixHelper.setDistanceControllerP(p);
    }

    public final void setHeadingControllerP(final double p) {
        m_helixHelper.setHeadingControllerP(p);
    }

    public final NKFollower reverse() {
        m_helixHelper.reverse();
        return this;
    }

    public final NKFollower mirror() {
        m_helixHelper.mirror();
        return this;
    }

    public static final NKFollower createNKFollower(final Path path) {
        return new NKFollower(new NKHelixHelper(path));
    }

    public static final NKFollower createNKFollower(final NKHelixHelper helper) {
        return new NKFollower(helper);
    }
}