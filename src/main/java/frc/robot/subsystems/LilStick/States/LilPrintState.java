package frc.robot.subsystems.LilStick.States;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ControllerMap;
import frc.robot.subsystems.LilStick.LilStickSubsystem;

public class LilPrintState extends CommandBase {

  public static double position;

  public LilPrintState() {
    addRequirements(LilStickSubsystem.getInstance());

  }


  @Override
  public void initialize() {
    //ArmSubsystem.getInstance().setPower(0);
    System.out.println("initialize print Statementy");
  }


  @Override
  public void execute() {
    position = LilStickSubsystem.getInstance().getBigStickPos();
    //System.out.println(position);
    
  
    SmartDashboard.putNumber("stickPosition", LilStickSubsystem.getInstance().getPrecisionPos(ControllerMap.getDriveStick().getRawAxis(ControllerMap.DriveController.Axis.STICK_THROT)));
  }


  @Override
  public void end(boolean interrupted) {}


  @Override
  public boolean isFinished() {
    return false;
  }
}