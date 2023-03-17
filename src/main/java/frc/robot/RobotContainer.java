// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ControllerMap;
import frc.robot.Constants.Constants;
import frc.robot.Constants.Constants.BigStickConstants.BigStickPos;
import frc.robot.subsystems.BigStick.BigStickSubsystem;
import frc.robot.subsystems.BigStick.States.PositionState;
import frc.robot.subsystems.BigStick.States.PrintState;
import frc.robot.subsystems.Drivetrain.*;
import frc.robot.subsystems.Drivetrain.States.OpenLoopState;
import frc.robot.subsystems.Yoinker.YoinkerSubsystem;
import frc.robot.subsystems.Yoinker.States.SpitState;
import frc.robot.subsystems.Yoinker.States.SwallowState;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.*;
import frc.robot.commands.Autos.lilpushpull;


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
    Trigger swallow = driveStick.button(ControllerMap.DriveController.Button.TRIGGER).onTrue(
      new SwallowState().repeatedly()
    );
    Trigger spit = driveStick.button(ControllerMap.DriveController.Button.B3).onTrue(
      new SpitState().repeatedly()
    );
    Trigger trig3 = driveStick.button(ControllerMap.DriveController.Button.B4).onTrue(
      new PositionState(BigStickPos.SHELF_YOINK)
      //new PrintState().repeatedly()
    );
    Trigger trig4 = driveStick.button(ControllerMap.DriveController.Button.B5).onTrue(
      new PositionState(BigStickPos.SCORE_CUBE)
    );
    Trigger trig5 = driveStick.button(ControllerMap.DriveController.Button.B6).onTrue(
      new PositionState(BigStickPos.STARTUP)
    );
  }

  public Command getAutonomousCommand() {
   return new lilpushpull();
  }
}