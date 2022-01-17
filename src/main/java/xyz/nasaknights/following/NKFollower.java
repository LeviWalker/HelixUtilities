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

    /**
     * Initializes the NKFollower program, but gives you more customization
     * of your Path, NKHelixHelper, and NKFollower objects.
     * @param drivetrain Your drivetrain object or instance of NKDrive
     *                   (however you decide to organize your code)
     */
    public static final void initNKFollower(NKDrive drivetrain) {
        m_drivetrain = drivetrain;
    }

    /**
     * One and done method call that initializes the NKFollower program,
     * schedules an NKFollower command (using a Path parameter), and returns
     * the reference to the NKFollower command.
     * @param drivetrain Your drivetrain object or instance of NKDrive
     *                   (however you decide to organize your code)
     * @param path The path of the command to follow
     * @return The NKFollower command. This can be used for debugging.
     * @see {@link #initNKFollower(NKDrive)} if you don't want to schedule
     *      the command just yet
     * @see {@link #initNKFollower(NKDrive, NKHelixHelper)} if you want to
     *      customize your NKHelixHelper "old" command before you run. This
     *      is intended to let you keep track of your HelixHelper objects.
     */
    public static final NKFollower initNKFollower(NKDrive drivetrain, Path path) {
        m_drivetrain = drivetrain;
        NKFollower temp = createNKFollower(path);
        temp.schedule();
        return temp;
    }

    /**
     * One and done method call that initializes the NKFollower program,
     * schedules an NKFollower command (using a NKHelixHelper parameter),
     * and returns the reference to the NKFollower command.
     * @param drivetrain Your drivetrain object or instance of NKDrive
     *                   (however you decide to organize your code)
     * @param path The path of the command to follow
     * @return The NKFollower command. This can be used for debugging.
     * @see {@link #initNKFollower(NKDrive)} if you don't want to schedule
     *      the command just yet
     * @see {@link #initNKFollower(NKDrive, Path)} if you just want to
     *      input your path
     */
    public static final NKFollower initNKFollower(NKDrive drivetrain, NKHelixHelper helper) {
        m_drivetrain = drivetrain;
        NKFollower temp = createNKFollower(helper);
        temp.schedule();
        return temp;
    }

    /**
     * Extended verson of the {@link #initNKFollower(NKDrive, Path)} method.
     * This one allows your to pass in your target proportional gains after
     * tuning to clean up some code.
     * @param drivetrain Your drivetrain object or instance of NKDrive
     *                   (however you decide to organize your code)
     * @param path The path of the command to follow
     * @param distControllerP The proportional gain for the distance controller
     * @param headingControllerP The proportional gain for the heading controller
     * @return The NKFollower command. This can be used for debugging.
     * @see {@link #initNKFollower(NKDrive, Path)} if you don't want to set
     *      proportional gains for the distance and heading controllers just
     *      yet
     */
    public static final NKFollower initNKFollower(NKDrive drivetrain, Path path, double distControllerP, double headingControllerP) {
        m_drivetrain = drivetrain;
        NKFollower temp = createNKFollower(path);
        temp.setDistanceControllerP(distControllerP);
        temp.setHeadingControllerP(headingControllerP);
        temp.schedule();
        return temp;
    }

    /**
     * Extended verson of the {@link #initNKFollower(NKDrive, NKHelixHelper)} method.
     * This one allows your to pass in your target proportional gains after
     * tuning to clean up some code.
     * @param drivetrain Your drivetrain object or instance of NKDrive
     *                   (however you decide to organize your code)
     * @param helper The NKHelixHelper you wish to use
     * @param distControllerP The proportional gain for the distance controller
     * @param headingControllerP The proportional gain for the heading controller
     * @return The NKFollower command. This can be used for debugging.
     * @see {@link #initNKFollower(NKDrive, NKHelixHelper)} if you don't want to set
     *      proportional gains for the distance and heading controllers just
     *      yet
     */
    public static final NKFollower initNKFollower(NKDrive drivetrain, NKHelixHelper helper, double distControllerP, double headingControllerP) {
        m_drivetrain = drivetrain;
        NKFollower temp = createNKFollower(helper);
        temp.setDistanceControllerP(distControllerP);
        temp.setHeadingControllerP(headingControllerP);
        temp.schedule();
        return temp;
    }

    public static final NKDrive getDrivetrain() {
        if (m_drivetrain == null)
            throw new NullPointerException("Failed to initialize the HelixHelper drivetrain."
                                            + "Please call the NKHelixHelper.initHelixHelper"
                                            + "method. Thank you for using HelixHelper.");
        return m_drivetrain;
    }

    /**
     * The master NKFollower constructor
     * @param helper HelixHelper to imitate
     */
    protected NKFollower(final NKHelixHelper helper) {
        m_helixHelper = helper;
        addRequirements(getDrivetrain());
    }

    /**
     * NKFollower command constructor
     * @param path Path to follow.
     */
    public NKFollower(final Path path) {
        this(new NKHelixHelper(path));
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

    @Override
    public final boolean isFinished() {
        return m_helixHelper.isDone();
    }

    /**
     * Sets the proportional gain for the distance controller
     * @param p the new proportional gain
     */
    public final void setDistanceControllerP(final double p) {
        m_helixHelper.setDistanceControllerP(p);
    }

    /**
     * Sets the proportional gain for the heading (turning) controller
     * @param p the new proportional gain
     */
    public final void setHeadingControllerP(final double p) {
        m_helixHelper.setHeadingControllerP(p);
    }

    /**
     * Makes the path execute in a reverse fashion.
     * @return The reference to the NKFollower again (if you want
     *         to call the {@link #mirror()} method for example)
     */
    public final NKFollower reverse() {
        m_helixHelper.reverse();
        return this;
    }

    /**
     * Makes the path execute in a mirrored fashion.
     * @return The reference to the NKFollower again (if you want
     *         to call the {@link #reverse()} method for example)
     */
    public final NKFollower mirror() {
        m_helixHelper.mirror();
        return this;
    }

    /**
     * This method creates an NKFollower object with a Path parameter.
     * If you use this method, you'll need to call one of the
     * {@link #initNKFollower(NKDrive)} methods first.
     * @param path Path to follow.
     * @return The NKFollower command.
     */
    public static final NKFollower createNKFollower(final Path path) {
        return new NKFollower(new NKHelixHelper(path));
    }

    /**
     * This method creates an NKFollower object with a NKHelixHelper
     * parameter. If you use this method, you'll need to call one of
     * the {@link #initNKFollower(NKDrive)} methods first.
     * @param helper A NKHelixHelper object
     * @return The NKFollower command.
     */
    public static final NKFollower createNKFollower(final NKHelixHelper helper) {
        return new NKFollower(helper);
    }
}