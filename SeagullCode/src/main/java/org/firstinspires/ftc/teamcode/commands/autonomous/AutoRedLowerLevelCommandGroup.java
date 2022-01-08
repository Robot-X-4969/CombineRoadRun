package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class AutoRedLowerLevelCommandGroup extends SequentialCommandGroup {
    public AutoRedLowerLevelCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake) {
        super(
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DEPOT_START_TO_ALLIANCE_HUB_LEVEL1),
                new DumpUnloadTopLevelCommand(bucket).withTimeout(1.5),
                new WaitCommand(0.3),
                new DumpCollectCommand(bucket),
                new IntakeInCommand(intake), // Intake command - spin the intake before arrived at the depot
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_ALLIANCE_HUB_LEVEL1_TO_DEPOT_COLLECT),

                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DEPOT_COLLECT_TO_ALLIANCE_HUB_LEVEL1).alongWith(new IntakeOutCommand(intake).sleep(0.5).andThen(new IntakeStopCommand(intake))),
                new DumpUnloadTopLevelCommand(bucket).withTimeout(1.5),
                new WaitCommand(0.3),
                new DumpCollectCommand(bucket), // Bucket dump command
                new IntakeInCommand(intake), // Intake command - spin the intake before arrived at the depot
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_ALLIANCE_HUB_LEVEL1_TO_DEPOT_COLLECT),

                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DEPOT_COLLECT_TO_ALLIANCE_HUB_LEVEL1).alongWith(new IntakeOutCommand(intake).sleep(0.5).andThen(new IntakeStopCommand(intake))),
                new DumpUnloadTopLevelCommand(bucket).withTimeout(1.5),
                new WaitCommand(0.3),
                new DumpCollectCommand(bucket), // Bucket dump command
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_ALLIANCE_HUB_LEVEL1_TO_DEPOT_COLLECT),
                CommandScheduler.getInstance()::terminateOpMode); //ending
    }
}