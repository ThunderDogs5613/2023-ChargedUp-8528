package frc.robot.subsystems.Yoinker.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Constants;
import frc.robot.subsystems.Yoinker.YoinkerSubsystem;

public class SwallowState extends CommandBase{
    private YoinkerSubsystem yoink = YoinkerSubsystem.getInstance();
    
    public SwallowState() {
        addRequirements(yoink);
    }

    @Override
    public void execute() {
        yoink.setPower(Constants.YoinkerConstants.YoinkinMaxSpeed*-1);
    }
}

