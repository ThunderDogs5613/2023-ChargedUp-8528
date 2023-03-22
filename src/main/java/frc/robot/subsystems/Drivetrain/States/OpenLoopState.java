package frc.robot.subsystems.Drivetrain.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ControllerMap;
import frc.robot.subsystems.Drivetrain.DrivetrainSubsystem;

public class OpenLoopState extends CommandBase {
    
    DrivetrainSubsystem drive = DrivetrainSubsystem.getInstance();

    public OpenLoopState() {
        addRequirements(drive);
    }

    @Override
  public void execute() {
    double throttle = ControllerMap.getDriveStick().getRawAxis(ControllerMap.DriveController.Axis.STICK_Y)*-1.43;
    double rotation = ControllerMap.getDriveStick().getRawAxis(ControllerMap.DriveController.Axis.STICK_X)*-0.7;
    
    DrivetrainSubsystem.getInstance().setArcade(throttle, rotation);
  }
}
