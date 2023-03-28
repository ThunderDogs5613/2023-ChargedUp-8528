// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ControllerMap;
import frc.robot.Constants.Constants;

import frc.robot.subsystems.BigStick.BigStickSubsystem;
import frc.robot.subsystems.BigStick.States.*;
import frc.robot.Constants.Constants.BigStickConstants.BigStickPos;

import frc.robot.Constants.Constants.ScoopConstants.ScoopPos;

import frc.robot.subsystems.Scoop.*;
import frc.robot.subsystems.Scoop.States.*;
import frc.robot.subsystems.Drivetrain.*;
import frc.robot.subsystems.Drivetrain.States.OpenLoopState;
import frc.robot.subsystems.Scoop.ScoopSubsystem;
import frc.robot.subsystems.Scoop.States.ScoopPrintState;
import frc.robot.subsystems.Yoinker.YoinkerSubsystem;
import frc.robot.subsystems.Yoinker.States.IdleState;
import frc.robot.subsystems.Yoinker.States.SpitState;
import frc.robot.subsystems.Yoinker.States.SwallowState;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.*;
import frc.robot.commands.Autos.AutoBalance;
import frc.robot.commands.Autos.GonBalance;
import frc.robot.commands.Autos.lilpushpull;


public class RobotContainer {



  DrivetrainSubsystem drive;
  YoinkerSubsystem yoink;
  BigStickSubsystem bigStick;
  ScoopSubsystem lilStick;
  CommandGenericHID driveStick = ControllerMap.getDriveStick();

  public RobotContainer() {
    initializeSubsystems();
    configureBindings();
    setAllDefaultCommands();
    SmartDashboard.putData(bigStick);
  }

  private void setAllDefaultCommands() {
    CommandScheduler.getInstance().setDefaultCommand(drive, new OpenLoopState());
    CommandScheduler.getInstance().setDefaultCommand(bigStick, new PrintState());
    CommandScheduler.getInstance().setDefaultCommand(lilStick, new ScoopPrintState());
    CommandScheduler.getInstance().setDefaultCommand(yoink, new IdleState());
  }

  private void initializeSubsystems() {
    drive = DrivetrainSubsystem.getInstance();
    bigStick = BigStickSubsystem.getInstance();
    lilStick = ScoopSubsystem.getInstance();
    yoink = YoinkerSubsystem.getInstance();
  }

  private void configureBindings() {
    Trigger swallow = driveStick.button(ControllerMap.DriveController.Button.TRIGGER).onTrue(
      new SwallowState().repeatedly()
    );

    Trigger scoopUp = driveStick.button(ControllerMap.DriveController.Button.B3).onTrue(
      new ScoopPositionState(ScoopPos.UP)
    );
    Trigger scoopDown = driveStick.button(ControllerMap.DriveController.Button.B4).onTrue(
      new ScoopPositionState(ScoopPos.START)
    );
    Trigger scoopBack = driveStick.button(ControllerMap.DriveController.Button.B5).onTrue(
      new ScoopPositionState(ScoopPos.BACK)
    );
    Trigger scoopInside = driveStick.button(ControllerMap.DriveController.Button.B6).onTrue(
      new ScoopPositionState(ScoopPos.INSIDE)
    );

    Trigger armDown = driveStick.button(ControllerMap.DriveController.Button.B7).onTrue(
      new PositionState(BigStickPos.DOWN)
    ); 
    Trigger armStow = driveStick.button(ControllerMap.DriveController.Button.B8).onTrue(
      new PositionState(BigStickPos.STOW)
      //new PrintState().repeatedly()
    );
    Trigger armUp = driveStick.button(ControllerMap.DriveController.Button.B9).toggleOnTrue(
      new PositionState(BigStickPos.UP)
    );
    
  }

  public Command getAutonomousCommand() {
   return new GonBalance();
  }
}