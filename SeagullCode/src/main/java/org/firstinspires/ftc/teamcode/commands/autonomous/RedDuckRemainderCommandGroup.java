
package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.carousel.AutoCarouselFastSpinCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.AutoCarouselSlowSpinCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class RedDuckRemainderCommandGroup extends SequentialCommandGroup {
    public RedDuckRemainderCommandGroup(DrivebaseSubsystem drive, DumpSubsystem dump, IntakeSubsystem intake, CarouselSubsystem carousel) {
        super(
                new AutoCarouselSlowSpinCommand(carousel).withTimeout(1),
                new AutoCarouselFastSpinCommand(carousel).withTimeout(0.9),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_CAROUSEL_TO_DUCK_COLLECT1),
                new IntakeInCommand(intake),
                new RedDuckCollectCommand(drive),
                new IntakeStopCommand(intake),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_COLLECT2_TO_ALLIANCE_HUB_LEVEL3),
                new AutonomousBucketDumpCommand(dump),
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_ALLIANCE_HUB_LEVEL3_TO_PARK),
                new DumpCollectCommand(dump)
        ); //ending
    }
}
