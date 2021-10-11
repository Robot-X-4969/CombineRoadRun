package org.firstinspires.ftc.samplecode.clawbot;

import org.firstinspires.ftc.teamcode.samplecode.clawbot.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.samplecode.clawbot.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.samplecode.clawbot.subsystems.DrivebaseSubsystem;

public class Robot {
    public DrivebaseSubsystem drivebaseSubsystem;
    public ArmSubsystem armSubsystem;
    public ClawSubsystem clawSubsystem;

    public Hardware hardware;
    public Robot(){
        hardware = new Hardware();

        drivebaseSubsystem = new DrivebaseSubsystem(hardware.leftMotor, hardware.rightMotor);
        armSubsystem = new ArmSubsystem(hardware.armMotor);
        clawSubsystem = new ClawSubsystem(hardware.clawServo);
    }
}
