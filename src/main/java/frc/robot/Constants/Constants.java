// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Constants;

public final class Constants {

    public final class DrivetrainConstants{
        public static final double RobotMaxSpeed = 11;
    }

    public static final class BigStickConstants {
        public static final double kP = 0.03;
        public static final double kI = 0.00;
        public static final double kD = 0.005;

        public static final double bigStickPrecisionSpeed = 0.10;
        public static final double bigStickPosRaiseSpeed = 0.20;
        public static final double bigStickPosLowerSpeed = -0.65;
        public static final double maxThrotIn = 25;
        public static final double minThrotIn = -43;
        public static final double maxEncoderOut = 60;
        public static final double minEncoderOut = 0;

        public static final double down = 0;
        public static final double stowed = -9.33;
        public static final double dockingPos = -16.07;
        public static final double straighUp = -18.29;
        public static final double farBack = -37.38;

        public enum BigStickPos {
            DOWN, STOW, DOCK, UP, BACK, HOLD
        }
    }

    public static final class ScoopConstants {
        public static final double kP = 0.03;
        public static final double kI = 0.00;
        public static final double kD = 0.005;

        public static final double bigStickPrecisionSpeed = 0.10;
        public static final double bigStickPosRaiseSpeed = 0.20;
        public static final double bigStickPosLowerSpeed = -0.65;
        public static final double maxThrotIn = 25;
        public static final double minThrotIn = -43;
        public static final double maxEncoderOut = 60;
        public static final double minEncoderOut = 0;

        public static final double startPos = 0;
        public static final double straightUp = 3.81;

        public enum ScoopPos {
            START, UP, HOLD
        }
    }
    public static final class YoinkerConstants{
        public static final double YoinkinMaxSpeed = 1;
        public static final double YoinkiMinSpeed = -1;
        public static final double YoinkinIdleSpeed = 0;
    } 
}