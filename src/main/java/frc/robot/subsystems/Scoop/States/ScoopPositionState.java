package frc.robot.subsystems.Scoop.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Constants;
import frc.robot.Constants.Constants.ScoopConstants;
import frc.robot.Constants.Constants.ScoopConstants.ScoopPos;
import frc.robot.subsystems.Scoop.ScoopSubsystem;

public class ScoopPositionState extends CommandBase{

    private ScoopPos position;
    private double upPower = Constants.BigStickConstants.bigStickPosRaiseSpeed;
    private double downPower = Constants.BigStickConstants.bigStickPosLowerSpeed;
    private double holdPosition;
    private double power;


  
    public ScoopPositionState(ScoopPos position) {
      addRequirements(ScoopSubsystem.getInstance());  
  
      this.position = position;
    }
  
    @Override
    public void initialize() {
  
      holdPosition = ScoopSubsystem.getInstance().getScoopPos();
  
      switch(position) {
        case START :
          ScoopSubsystem.getInstance().setScoopSetpoint(Constants.ScoopConstants.startPos);
 //         BigStickSubsystem.getInstance().setFeedForward(0.02);
          break;
  
          case UP :
          ScoopSubsystem.getInstance().setScoopSetpoint(Constants.ScoopConstants.straightUp);
//          ArmSubsystem.getInstance().setFeedForward(0.01);
          break; 
          
          case INSIDE :
          ScoopSubsystem.getInstance().setScoopSetpoint(Constants.ScoopConstants.insideFrame);
//          ArmSubsystem.getInstance().setFeedForward(0.01);
          break; 
          
          case BACK :
          ScoopSubsystem.getInstance().setScoopSetpoint(Constants.ScoopConstants.fullBack);
//          ArmSubsystem.getInstance().setFeedForward(0.01);
          break;  
          
        case HOLD :
          ScoopSubsystem.getInstance().setScoopSetpoint(holdPosition);
//        ArmSubsystem.getInstance().setFeedForward(0.0);
          break;
      }

      ScoopSubsystem.getInstance().enable();
    }
  
    public void execute() {
      System.out.println(ScoopSubsystem.getInstance().getScoopPos());
    }
  
    @Override
    public void end(boolean interrupted) {
        ScoopSubsystem.getInstance().disable();
    }
}