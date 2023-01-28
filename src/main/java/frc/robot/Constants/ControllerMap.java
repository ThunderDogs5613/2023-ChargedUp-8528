package frc.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;

public class ControllerMap {

    private static Joystick driveStick;

    public static Joystick getDriveStick(){
        if (driveStick == null){
            driveStick = new Joystick(RobotMap.DRIVE_STICK_ID);
        }
        return driveStick;
    }
    

    public static class GenericHID{
        public static class Axis{
            public static final int STICK_X = 0;
            public static final int STICK_Y = 1;
            public static final int STICK_THROT = 2;
            public static final int STICK_ROT = 3;
        }

        public static class Button{
            public static final int TRIGGER = 1;
            public static final int B2 = 2;
            public static final int B3 = 3;
            public static final int B4 = 4;
            public static final int B5 = 5;
            public static final int B6 = 6;
            public static final int PINKY = 7;
        }
    }   
}