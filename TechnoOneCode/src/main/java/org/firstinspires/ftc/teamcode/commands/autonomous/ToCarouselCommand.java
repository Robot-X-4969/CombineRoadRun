package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.carousel.CarouselSpinCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeSafeCommand;
import org.firstinspires.ftc.teamcode.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class ToCarouselCommand extends SequentialCommandGroup {
    public ToCarouselCommand(DrivebaseSubsystem drive, LiftSubsystem lift, DepositSubsystem deposit, CarouselSubsystem carousel){
        super(new TrajectorySequenceCommand(drive, AutonomousConstants.DUCK_DEPOSIT_TO_CAROUSEL)
                .alongWith(new ArmRetractCommand(deposit), new LiftCollectCommand(lift)),
                new CarouselSpinCommand(carousel).withTimeout(3));
    }
}
