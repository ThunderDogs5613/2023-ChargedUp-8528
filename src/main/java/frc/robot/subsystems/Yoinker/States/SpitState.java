package frc.robot.subsystems.Yoinker.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Constants;
import frc.robot.subsystems.Yoinker.YoinkerSubsystem;

public class SpitState extends CommandBase{
//line moved down to avoid error, vscode is stupid
    private YoinkerSubsystem yoink = YoinkerSubsystem.getInstance();
    public SpitState() {
        addRequirements(yoink);
    }

    @Override
    public void execute() {
        yoink.setPower(Constants.YoinkerConstants.YoinkinMaxSpeed);
    }

    public void end(boolean interrupted) {
        yoink.setPower(0);
    }
}
