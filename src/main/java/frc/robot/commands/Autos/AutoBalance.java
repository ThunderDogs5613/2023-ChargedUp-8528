package frc.robot.commands.Autos;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain.DrivetrainSubsystem;

public class AutoBalance extends CommandBase {

    DrivetrainSubsystem mDrivetrain;

    double speedK = 1.0;

    boolean isTriggered = false;

    Timer backupTimer;
    Timer checkTimer;

    SlewRateLimiter speedLimiter;

    public AutoBalance(DrivetrainSubsystem drivetrain) {
        mDrivetrain = drivetrain;

        addRequirements(mDrivetrain);

        backupTimer = new Timer();
        checkTimer = new Timer();

        speedLimiter = new SlewRateLimiter(4.5);
    }

    @Override
    public void initialize() {
        isTriggered = false;
        speedK = 1.0;
    }

    @Override
    public void execute() {

        //System.out.println(mDrivetrain.ahrs.getPitch());
        SmartDashboard.putNumber("Pitch", mDrivetrain.ahrs.getPitch());
        SmartDashboard.putNumber("Roll", mDrivetrain.ahrs.getRoll());
        SmartDashboard.putNumber("Yaw", mDrivetrain.ahrs.getYaw());

        if ((mDrivetrain.ahrs.getRoll() < 6.75) && (mDrivetrain.ahrs.getRoll() > -6.75)) {
            mDrivetrain.setArcade(0, 0);
        } else if (mDrivetrain.ahrs.getRoll() > 6.75) {
            mDrivetrain.setArcade(speedLimiter.calculate(0.22*speedK), 0);
            if (isTriggered) {
                isTriggered = false;
                speedK /= 1.8;
            }
        } else {
            mDrivetrain.setArcade(speedLimiter.calculate(-0.22*speedK), 0);
            if (isTriggered) {
                isTriggered = false;
                speedK /= 1.8;
            }
        }
    }

    @Override
    public void end(boolean isFinished) {
        mDrivetrain.setArcade(0, 0);
    } 
    
}
