package frc.robot.subsystems.BigStick.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Constants;
import frc.robot.Constants.ControllerMap;
import frc.robot.subsystems.BigStick.BigStickSubsystem;

public class PrecisionControl extends CommandBase{

    private BigStickSubsystem stick = BigStickSubsystem.getInstance();

    public PrecisionControl() {
        addRequirements(stick);
    }

    @Override
    public void execute() {
        double armThrot = ControllerMap.getDriveStick().getRawAxis(ControllerMap.DriveController.Axis.STICK_THROT);
        stick.setStickSetpoint(stick.getPrecisionPos(armThrot));
    }
}