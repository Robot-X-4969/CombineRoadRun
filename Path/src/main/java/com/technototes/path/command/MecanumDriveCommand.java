package com.technototes.path.command;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.technototes.control.gamepad.Stick;
import com.technototes.library.command.Command;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;


import java.util.function.DoubleSupplier;

public class MecanumDriveCommand implements Command {
    public MecanumDrivebaseSubsystem subsystem;
    public DoubleSupplier x, y, r;
    public MecanumDriveCommand(MecanumDrivebaseSubsystem sub, Stick stick1, Stick stick2) {
        //addRequirements(sub.dummySubsystem);
        subsystem = sub;
        x = stick1.getXSupplier();
        y = stick1.getYSupplier();
        r = stick2.getXSupplier();
    }

    @Override
    public void execute() {
             Vector2d input = new Vector2d(
                -y.getAsDouble()*subsystem.speed,
                -x.getAsDouble()*subsystem.speed
        ).rotated(-subsystem.getExternalHeading());

        subsystem.setWeightedDrivePower(
                new Pose2d(
                        input.getX(),
                        input.getY(),
                        -Math.pow(r.getAsDouble()*subsystem.speed, 3)
                )
        );
        subsystem.update();

    }

    @Override
    public void end(boolean cancel) {
        if(cancel) subsystem.setDriveSignal(new DriveSignal());
    }
}