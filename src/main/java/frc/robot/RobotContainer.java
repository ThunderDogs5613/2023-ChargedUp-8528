// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.subsystems.Drivetrain.*;
import frc.robot.subsystems.Drivetrain.States.OpenLoopState;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {

  DrivetrainSubsystem drive;


  public RobotContainer() {
    initializeSubsystems();
    configureBindings();
    setAllDefaultCommands();
  }

  private void initializeSubsystems() {
    drive = DrivetrainSubsystem.getInstance();
  }

  private void configureBindings() {
  //  new Trigger(m_exampleSubsystem::exampleCondition)
  //      .onTrue(new ExampleCommand(m_exampleSubsystem));
  }

  private void setAllDefaultCommands() {
    CommandScheduler.getInstance().setDefaultCommand(drive, new OpenLoopState());
  }

//  public Command getAutonomousCommand() {
  //  return Autos.exampleAuto(m_exampleSubsystem);
//  }
}
