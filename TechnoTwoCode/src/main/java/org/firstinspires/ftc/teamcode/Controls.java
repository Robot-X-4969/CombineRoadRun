package org.firstinspires.ftc.teamcode;

import com.technototes.library.command.WaitCommand;
import com.technototes.library.control.gamepad.CommandAxis;
import com.technototes.library.control.gamepad.CommandButton;
import com.technototes.library.control.gamepad.CommandGamepad;
import com.technototes.library.control.gamepad.Stick;

import org.firstinspires.ftc.teamcode.commands.arm.ArmCollectCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmLevel1Command;
import org.firstinspires.ftc.teamcode.commands.arm.ArmLevel3Command;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselLeftCommand;
import org.firstinspires.ftc.teamcode.commands.carousel.CarouselRightCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmTranslateCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.deposit.DumpVariableCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.ResetGyroCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.SetSpeedCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeSafeCommand;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstants.*;

public class Controls {

    public CommandGamepad gamepad;

    public Robot robot;

    public CommandAxis dumpAxis, toIntakeButton;
    public CommandButton neutralHubButton, specificHubButton;

    public CommandButton liftAdjustUpButton, liftAdjustDownButton, slideAdjustInButton, slideAdjustOutButton;

    public CommandButton intakeInButton, intakeOutButton;

    public CommandButton carouselLeftButton, carouselRightButton;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, snailSpeedButton;

    public Controls(CommandGamepad g, Robot r){
        gamepad = g;
        robot = r;

        dumpAxis = gamepad.leftTrigger.setTriggerThreshold(0.5);
        neutralHubButton = gamepad.leftBumper;
        specificHubButton = gamepad.rightBumper;
        toIntakeButton = gamepad.rightTrigger.setTriggerThreshold(0.3);

        liftAdjustUpButton = gamepad.dpadUp;
        liftAdjustDownButton = gamepad.dpadDown;
        slideAdjustInButton = gamepad.dpadRight;
        slideAdjustOutButton = gamepad.dpadLeft;

        intakeInButton = gamepad.cross;
        intakeOutButton = gamepad.circle;

        carouselLeftButton = gamepad.square;
        carouselRightButton = gamepad.triangle;

        resetGyroButton = gamepad.rightStickButton;
        snailSpeedButton = gamepad.leftStickButton;

        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;

        if(LIFT_CONNECTED) bindArmControls();
        if(DEPOSIT_CONNECTED) bindDepositControls();
        if(DRIVE_CONNECTED) bindDriveControls();
        if(INTAKE_CONNECTED) bindIntakeControls();
        if(CAROUSEL_CONNECTED) bindCarouselControls();
        if(CAP_CONNECTED) bindCapControls();
    }


    public void bindDepositControls(){
        dumpAxis.whilePressed(new DumpVariableCommand(robot.depositSubsystem, dumpAxis));
        toIntakeButton.whenPressed(new ArmRetractCommand(robot.depositSubsystem));
        specificHubButton.whenPressed(new WaitCommand(0.1).andThen(new ArmExtendCommand(robot.depositSubsystem)));
        neutralHubButton.whenPressed(new WaitCommand(0.1).andThen(new ArmExtendCommand(robot.depositSubsystem)));
        slideAdjustOutButton.whilePressed(new ArmTranslateCommand(robot.depositSubsystem, -0.01));
        slideAdjustInButton.whilePressed(new ArmTranslateCommand(robot.depositSubsystem, 0.01));
    }

    public void bindArmControls(){
        neutralHubButton.whenPressed(new ArmLevel1Command(robot.armSubsystem));
        specificHubButton.whenPressed(new ArmLevel3Command(robot.armSubsystem));
        toIntakeButton.whenPressed(new ArmCollectCommand(robot.armSubsystem));
    }

    public void bindDriveControls(){
        robot.drivebaseSubsystem.setDefaultCommand(new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
        snailSpeedButton.whilePressedOnce(new SetSpeedCommand(robot.drivebaseSubsystem));
    }

    public void bindIntakeControls(){
        if(DEPOSIT_CONNECTED) {
            toIntakeButton.whenPressed(new IntakeSafeCommand(robot.intakeSubsystem, robot.depositSubsystem));
            intakeInButton.whilePressedContinuous(new IntakeSafeCommand(robot.intakeSubsystem, robot.depositSubsystem));
        }else{
            toIntakeButton.whenPressed(new IntakeInCommand(robot.intakeSubsystem));
            intakeInButton.whilePressedContinuous(new IntakeInCommand(robot.intakeSubsystem));

        }

        intakeOutButton.whilePressedOnce(new IntakeOutCommand(robot.intakeSubsystem));
    }

    public void bindCarouselControls(){
        carouselLeftButton.whilePressedOnce(new CarouselLeftCommand(robot.carouselSubsystem));
        carouselRightButton.whilePressedOnce(new CarouselRightCommand(robot.carouselSubsystem));
    }

    public void bindCapControls() {

    }
}
