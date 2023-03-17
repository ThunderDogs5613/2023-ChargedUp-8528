package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain.States.SetAutonArcade;

public class lilpushpull extends SequentialCommandGroup{
    public lilpushpull() {

        addCommands(
            new SetAutonArcade(-0.25, 0).withTimeout(3),
            new SetAutonArcade(1, 0).withTimeout(0.5)
        );
    }
}
