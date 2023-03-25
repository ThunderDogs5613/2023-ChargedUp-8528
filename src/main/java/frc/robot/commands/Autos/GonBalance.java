package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain.DrivetrainSubsystem;
import frc.robot.subsystems.Drivetrain.States.SetAutonArcade;

public class GonBalance extends SequentialCommandGroup{
    public GonBalance() {

        addCommands(
            new SetAutonArcade(0.3, 0).withTimeout(1.5),
            new AutoBalance(DrivetrainSubsystem.getInstance())
        );
    }
}