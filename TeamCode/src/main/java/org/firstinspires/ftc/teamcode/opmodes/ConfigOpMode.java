package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.OldInstantCommand;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.Log;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@Disabled
@TeleOp(name = "config")
public class ConfigOpMode extends CommandOpMode implements Loggable {

    @Log.Number(name = "pivot")
    public Servo pivot;
    @Log.Number(name = "arm")
    public Servo arm;
    @Override
    public void uponInit() {
        pivot = new Servo("indexpivot");
        arm = new Servo("indexarm");
        super.telemetry.setDisplayFormat(Telemetry.DisplayFormat.HTML);
        driverGamepad.dpad.up.whenPressed(new OldInstantCommand(()->pivot.setPosition(pivot.getPosition()+0.05)));
        driverGamepad.dpad.down.whenPressed(new OldInstantCommand(()->pivot.setPosition(pivot.getPosition()-0.05)));
        driverGamepad.dpad.left.whenPressed(new OldInstantCommand(()->arm.setPosition(arm.getPosition()+0.05)));
        driverGamepad.dpad.right.whenPressed(new OldInstantCommand(()->arm.setPosition(arm.getPosition()-0.05)));
    }

}
