package org.firstinspires.ftc.teamcode.bot2.commands.shooter;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.bot2.subsystems.ShooterSubsystem;

import java.util.function.DoubleSupplier;

public class ShooterSetSpeed2Command implements Command {
    public ShooterSubsystem subsystem;
    public DoubleSupplier supplier;
    public double curr;
    public ShooterSetSpeed2Command(ShooterSubsystem sub, DoubleSupplier sup){
//        addRequirements(sub);
        subsystem = sub;
        supplier = sup;
    }

    @Override
    public void init() {
        curr = supplier.getAsDouble();

    }

    @Override
    public void execute() {
        subsystem.setVelocity(curr*(1+getRuntime().seconds()/2));
    }

    @Override
    public boolean isFinished() {
        return curr-subsystem.getVelocity() < 30;
    }

    @Override
    public void end(boolean cancel) {
        subsystem.setVelocity(curr);
    }
}