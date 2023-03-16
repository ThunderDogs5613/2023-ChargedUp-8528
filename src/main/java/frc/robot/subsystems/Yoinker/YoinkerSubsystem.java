package frc.robot.subsystems.Yoinker;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RobotMap;

public class YoinkerSubsystem extends SubsystemBase {
    private CANSparkMax motorYY;

    private static YoinkerSubsystem instance;

    private YoinkerSubsystem() {
      motorYY = new CANSparkMax(RobotMap.YOINK_MOTOR_ID, MotorType.kBrushless);
    }

    public static synchronized YoinkerSubsystem getInstance() {
      if(instance == null){
         instance = new YoinkerSubsystem();
      }
      return instance;
    }
    
    public void setPower(double power) {
      motorYY.set(power);
    }
}
