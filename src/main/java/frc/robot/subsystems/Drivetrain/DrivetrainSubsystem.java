// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Drivetrain;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;



public class DrivetrainSubsystem extends SubsystemBase {

  private CANSparkMax motorRF, motorRR, motorLF, motorLR;
  private MotorControllerGroup leftDrive, rightDrive;
  //private DifferentialDrive chassisDrive;
  private SlewRateLimiter throttleRateLimiter, autonThrotLimiter;

  private static DrivetrainSubsystem instance;


 private DrivetrainSubsystem() {
  motorRF = new CANSparkMax(RobotMap.RIGHT_FRONT_MOTOR_ID, MotorType.kBrushless);
  motorRR = new CANSparkMax(RobotMap.RIGHT_REAR_MOTOR_ID, MotorType.kBrushless);
  motorLF = new CANSparkMax(RobotMap.LEFT_FRONT_MOTOR_ID, MotorType.kBrushless);
  motorLR = new CANSparkMax(RobotMap.LEFT_REAR_MOTOR_ID, MotorType.kBrushless);
  motorRF.setInverted(true);
  motorRR.setInverted(true);

  rightDrive = new MotorControllerGroup(motorRF, motorRR);
  leftDrive = new MotorControllerGroup(motorLF, motorLR);

  //chassisDrive = new DifferentialDrive(leftDrive, rightDrive);

    throttleRateLimiter = new SlewRateLimiter(1.8);
    autonThrotLimiter = new SlewRateLimiter(1.5);
    new SlewRateLimiter(2);
  }

  public static synchronized DrivetrainSubsystem getInstance() {
    if(instance == null){
      instance = new DrivetrainSubsystem();
    }
    return instance;
  }

  public void setArcade(double throttle, double rotation) {
    WheelSpeeds speeds = DifferentialDrive.arcadeDriveIK(throttleRateLimiter.calculate(throttle ), rotation, true);
    double leftPower = speeds.left;
    double rightPower = speeds.right;
    
    //double limitedThrottle = throttleRateLimiter.calculate(throttle);

    //limitedThrottle = setDeadband(limitedThrottle, .02, 1);
   /*if (limitedThrottle > .02) {  limitedThrottle = limitedThrottle * -limitedThrottle; }
    else if (limitedThrottle < -.02) { limitedThrottle = limitedThrottle * limitedThrottle;  }
    else {  limitedThrottle = 0;  }*/


    /*if (rotation > .02) {  rotation = rotation * rotation * .25;  }
    else if (rotation < - .02) {  rotation = rotation * rotation * -.25;  }
    else {  rotation = 0; }*/
    
    //double leftPower = limitedThrottle + rotation;
    //double rightPower = limitedThrottle - rotation;

    setPower(leftPower, rightPower);
  }
  
 /*public void setCurvature(double throttle, double rotation, boolean rotationInPlace) {
    WheelSpeeds speeds = DifferentialDrive.curvatureDriveIK(throttle, -rotation, rotationInPlace);
    double leftPower = speeds.left;
    double rightPower = speeds.right;
    setPower(leftPower, rightPower);
  }*/
  public void setPower(double leftPower, double rightPower) {
    rightDrive.set(rightPower);
    leftDrive.set(leftPower);
  }

  public void setAutonPower(double inThrottle, double inRotation) {
    double throttle = autonThrotLimiter.calculate(inThrottle);
    //double rotation = autonRotLimiter.calculate(inRotation);
    double rotation = inRotation;

    double leftPower = throttle + rotation;
    double rightPower = throttle - rotation;

    setPower(leftPower, rightPower);
  }

  @Override
  public void periodic() {
  }
}