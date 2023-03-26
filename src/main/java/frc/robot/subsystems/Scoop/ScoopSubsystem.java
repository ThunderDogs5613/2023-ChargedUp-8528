package frc.robot.subsystems.Scoop;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.Constants;
import frc.robot.Constants.RobotMap;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ScoopSubsystem extends SubsystemBase{

    private boolean isUsingPID = false;
    private CANSparkMax motorLS;
    private RelativeEncoder encoderLS;
    private double stickOutput;

    private ArmFeedforward feedForward = new ArmFeedforward(0,0,0);
    private PIDController scoopPID = new PIDController(Constants.ScoopConstants.kP, Constants.ScoopConstants.kI, Constants.ScoopConstants.kD);
    private double minIn = Constants.ScoopConstants.minThrotIn;
    private double maxIn = Constants.ScoopConstants.maxThrotIn;
    private double minOut = Constants.ScoopConstants.minEncoderOut;
    private double maxOut = Constants.ScoopConstants.maxEncoderOut;


    private static ScoopSubsystem instance;

    private ScoopSubsystem() {
    
//
      motorLS = new CANSparkMax(RobotMap.LIL_STICK_MOTOR_ID, MotorType.kBrushless);
      encoderLS = motorLS.getEncoder();
    }

    public static synchronized ScoopSubsystem getInstance() {
        if(instance == null){
          instance = new ScoopSubsystem();
        }
        return instance;
    }
    
    public void setPower(double power) {
      motorLS.set(power);
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
      return getScoopPos();
    } 

    public double getScoopPos() {
      return encoderLS.getPosition();
    }
    
    public void enable() {
      isUsingPID = true;
    }

    public void disable() {
      isUsingPID = false;
    }

    public void setScoopSetpoint(double setpoint) {
      scoopPID.setSetpoint(setpoint);
    }
    
    public void periodic() {
      if(isUsingPID) {
        setPower(scoopPID.calculate(getMeasurement()));
      }
    }
}