package frc.robot.subsystems.Scoop.States;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ControllerMap;
import frc.robot.subsystems.Scoop.ScoopSubsystem;

public class ScoopPrintState extends CommandBase {

  public static double position;

  public ScoopPrintState() {
    addRequirements(ScoopSubsystem.getInstance());

  }


  @Override
  public void initialize() {
    //ArmSubsystem.getInstance().setPower(0);
    System.out.println("initialize Lil print Statement");
  }


  @Override
  public void execute() {
    position = ScoopSubsystem.getInstance().getScoopPos();
    //System.out.println(position);
    
  
    SmartDashboard.putNumber("stickPosition", ScoopSubsystem.getInstance().getPrecisionPos(ControllerMap.getDriveStick().getRawAxis(ControllerMap.DriveController.Axis.STICK_THROT)));
  }


  @Override
  public void end(boolean interrupted) {}


  @Override
  public boolean isFinished() {
    return false;
  }
}