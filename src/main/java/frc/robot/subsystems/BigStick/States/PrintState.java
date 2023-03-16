// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.BigStick.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BigStick.BigStickSubsystem;

public class PrintState extends CommandBase {

  public static double position;

  public PrintState() {
    addRequirements(BigStickSubsystem.getInstance());

  }


  @Override
  public void initialize() {
    //ArmSubsystem.getInstance().setPower(0);
    System.out.println("initialize print Statementy");
  }


  @Override
  public void execute() {
    position = BigStickSubsystem.getInstance().getBigStickPos();
    System.out.println(position);
  }


  @Override
  public void end(boolean interrupted) {}


  @Override
  public boolean isFinished() {
    return false;
  }
}