package frc.robot.subsystems.BigStick;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.Constants;
import frc.robot.Constants.RobotMap;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BigStickSubsystem extends SubsystemBase {
    private boolean isUsingPID = false;//anti-error comment
    private CANSparkMax motorBS;
    private RelativeEncoder encoder;
    private SlewRateLimiter stickLimiter;
    private double stickOutput;
    private double limitedOutput;
    
    private ArmFeedforward feedForward = new ArmFeedforward(0,0,0);
    private PIDController stickPID = new PIDController(Constants.BigStickConstants.kP, Constants.BigStickConstants.kI, Constants.BigStickConstants.kD);
    private double minIn = Constants.BigStickConstants.minThrotIn;
    private double maxIn = Constants.BigStickConstants.maxThrotIn;
    private double minOut = Constants.BigStickConstants.minEncoderOut;
    private double maxOut = Constants.BigStickConstants.maxEncoderOut;

    private static BigStickSubsystem instance;

    private BigStickSubsystem() {
    
//
      motorBS = new CANSparkMax(RobotMap.BIG_STICK_MOTOR_ID, MotorType.kBrushless);
      encoder = motorBS.getEncoder();
      stickLimiter = new SlewRateLimiter(1.8);   
    }

    public static synchronized BigStickSubsystem getInstance() {
        if(instance == null){
          instance = new BigStickSubsystem();
        }
        return instance;
    }
    
    public void setPower(double power) {
      motorBS.set(power);
    }

    public double getPrecisionPos(double actualInVal) {
      /*normVal=(inVal-minIn)/maxIn-minIn
        outVal=normVal*(maxOut-minOut)+minOut
      */
      double inVal = actualInVal*100;
      double normVal = (inVal-minIn)/(maxIn-minIn);
      double outVal = (normVal*(maxOut-minOut))+minOut;
      double actualOutVal = outVal*-1;
      return actualOutVal;
    }

    public void useOutput(double output, double setpoint) {
      stickOutput = output;
      //limitedOutput = armLimiter.calculate(armOutput);
      setPower(output);
    }

    protected double getMeasurement() {
      return getBigStickPos();
    } 

    public double getBigStickPos() {
      return encoder.getPosition();
    }
    
    public void enable() {
      isUsingPID = true;
    }

    public void disable() {
      isUsingPID = false;
    }

    public void setStickSetpoint(double setpoint) {
      stickPID.setSetpoint(setpoint);
    }
    
    public void periodic() {
      if(isUsingPID) {
        setPower(stickPID.calculate(getMeasurement()));
      }
    }
}