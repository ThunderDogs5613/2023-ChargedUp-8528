package frc.robot.subsystems.BigStick.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Constants;
import frc.robot.Constants.Constants.BigStickConstants.BigStickPos;
import frc.robot.subsystems.BigStick.BigStickSubsystem;

public class PositionState extends CommandBase{

    private BigStickPos position;
    private double upPower = Constants.BigStickConstants.bigStickPosRaiseSpeed;
    private double downPower = Constants.BigStickConstants.bigStickPosLowerSpeed;
    private double holdPosition;
    private double power;


  
    public PositionState(BigStickPos position) {
      addRequirements(BigStickSubsystem.getInstance());  
  
      this.position = position;
    }
  
    @Override
    public void initialize() {
  
      holdPosition = BigStickSubsystem.getInstance().getBigStickPos();
  
      switch(position) {
        case DOWN :
          BigStickSubsystem.getInstance().setStickSetpoint(Constants.BigStickConstants.down);
 //         BigStickSubsystem.getInstance().setFeedForward(0.02);
          break;
  
        case STOW :
          BigStickSubsystem.getInstance().setStickSetpoint(Constants.BigStickConstants.stowed);
//          ArmSubsystem.getInstance().setFeedForward(0.01);
          break;  
          
        case DOCK :
          BigStickSubsystem.getInstance().setStickSetpoint(Constants.BigStickConstants.dockingPos);
//          ArmSubsystem.getInstance().setFeedForward(0.2);
          break;
  
        case UP :
          BigStickSubsystem.getInstance().setStickSetpoint(Constants.BigStickConstants.straighUp);
  //        ArmSubsystem.getInstance().setFeedForward(0.05);
          break;

        case BACK :
          BigStickSubsystem.getInstance().setStickSetpoint(Constants.BigStickConstants.straighUp);
    //        ArmSubsystem.getInstance().setFeedForward(0.05);
              break;
  
        case HOLD :
        BigStickSubsystem.getInstance().setStickSetpoint(holdPosition);
//        ArmSubsystem.getInstance().setFeedForward(0.0);
          break;
      }
      BigStickSubsystem.getInstance().enable();
    }
  
    public void execute() {
      System.out.println(BigStickSubsystem.getInstance().getBigStickPos());
    }
  
    @Override
    public void end(boolean interrupted) {
      BigStickSubsystem.getInstance().disable();
    }
}