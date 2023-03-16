package frc.robot.subsystems.BigStick.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Constants;
import frc.robot.subsystems.BigStick.BigStickSubsystem;

public class PrecisionUpState extends CommandBase{

    private BigStickSubsystem stick = BigStickSubsystem.getInstance();

    public PrecisionUpState() {
        addRequirements(stick);
    }

    @Override
    public void execute() {
        stick.setPower(Constants.BigStickConstants.bigStickPrecisionSpeed);
    }
}