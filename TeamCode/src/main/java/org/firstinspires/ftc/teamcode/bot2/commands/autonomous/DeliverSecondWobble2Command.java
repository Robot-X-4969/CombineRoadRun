package org.firstinspires.ftc.teamcode.bot2.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.bot2.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleLowerThenOpenCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.bot2.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.WobbleSubsystem;

public class DeliverSecondWobble2Command extends SequentialCommandGroup {
    public DeliverSecondWobble2Command(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s){
        super(new ParallelCommandGroup(
//                        new TrajectoryCommand(d,  new Pair<>(0.0, s.correctedPos(-5, 30, 0)),
//                                new Pair<>(s.correctedTan(0), s.correctedSecondWobbleDropPos())),
                new TrajectoryCommand(d, 10, new Pair<>(-45.0, s.correctedSecondWobbleDropPos())),
                        new WobbleRaiseCommand(w)),
                new WobbleLowerThenOpenCommand(w));
    }
}