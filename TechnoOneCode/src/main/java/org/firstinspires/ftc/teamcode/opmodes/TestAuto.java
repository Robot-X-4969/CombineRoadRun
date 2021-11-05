package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.trajectorysequence.TrajectorySequence;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name="test")
@SuppressWarnings("unused")
public class TestAuto extends CommandOpMode implements Loggable {
    public Robot robot;


    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot = new Robot();
        robot.drivebaseSubsystem.setPoseEstimate(new Pose2d(0, -65, Math.toRadians(90)));

        TrajectorySequence s = robot.drivebaseSubsystem.trajectorySequenceBuilder()
                .lineToSplineHeading(new Pose2d(0, -40, Math.toRadians(120)))
                .setReversed(true)
                .splineTo(new Vector2d(30, -64), Math.toRadians(0))
                .strafeTo(new Vector2d(44, -64))
                .setReversed(false)
                .strafeTo(new Vector2d(30, -64))
                .splineTo(new Vector2d(0, -40), Math.toRadians(120))
                .setReversed(true)
                .splineTo(new Vector2d(30, -64), Math.toRadians(0))
                .strafeTo(new Vector2d(46, -64))
                .setReversed(false)
                .strafeTo(new Vector2d(30, -64))
                .splineTo(new Vector2d(0, -40), Math.toRadians(120))
                .setReversed(true)
                .splineTo(new Vector2d(30, -64), Math.toRadians(0))
                .strafeTo(new Vector2d(48, -64))
                .setReversed(false)
                .strafeTo(new Vector2d(30, -64))
                .splineTo(new Vector2d(0, -40), Math.toRadians(120))
                .setReversed(true)
                .splineTo(new Vector2d(30, -64), Math.toRadians(0))
                .strafeTo(new Vector2d(50, -64))
                .setReversed(false)
                .strafeTo(new Vector2d(30, -64))
                .splineTo(new Vector2d(0, -40), Math.toRadians(120))
                .setReversed(true)
                .splineTo(new Vector2d(30, -64), Math.toRadians(0))
                .strafeTo(new Vector2d(35, -64))
                .build();

        CommandScheduler.getInstance().scheduleForState(new SequentialCommandGroup(
                new TrajectorySequenceCommand(robot.drivebaseSubsystem, s),
                this::terminate
        ), OpModeState.RUN);

    }

}
