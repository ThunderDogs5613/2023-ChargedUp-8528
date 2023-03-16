// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ControllerMap;
import frc.robot.Constants.Constants;
import frc.robot.Constants.Constants.BigStickConstants.BigStickPos;
import frc.robot.commands.Autos;
import frc.robot.subsystems.BigStick.BigStickSubsystem;
import frc.robot.subsystems.BigStick.States.PositionState;
import frc.robot.subsystems.BigStick.States.PrintState;
import frc.robot.subsystems.Drivetrain.*;
import frc.robot.subsystems.Drivetrain.States.OpenLoopState;
import frc.robot.subsystems.Yoinker.YoinkerSubsystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.*;


public class RobotContainer {

  DrivetrainSubsystem drive;
  YoinkerSubsystem yoink;
  BigStickSubsystem stick;
  CommandGenericHID driveStick = ControllerMap.getDriveStick();

  public RobotContainer() {
    initializeSubsystems();
    configureBindings();
    setAllDefaultCommands();
  }

  private void setAllDefaultCommands() {
    CommandScheduler.getInstance().setDefaultCommand(drive, new OpenLoopState());
    CommandScheduler.getInstance().setDefaultCommand(stick, new PositionState(BigStickPos.STARTUP));
  }

  private void initializeSubsystems() {
    drive = DrivetrainSubsystem.getInstance();
    yoink = YoinkerSubsystem.getInstance();
    stick = BigStickSubsystem.getInstance();
  }

  private void configureBindings() {
    Trigger spit = driveStick.button(ControllerMap.DriveController.Button.TRIGGER);
    Trigger swallow = driveStick.button(ControllerMap.DriveController.Button.B2);
    Trigger stow = driveStick.button(ControllerMap.DriveController.Button.B3).onTrue(
      new PrintState().repeatedly()
    );
  }

//  public Command getAutonomousCommand() {
  //  return Autos.exampleAuto(m_exampleSubsystem);
//  }
}