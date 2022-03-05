// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //PWM ports declaration  
    public static final int frontLeftMotorPort = 0;
    public static final int frontRightMotorPort = 1;
    public static final int rearLeftMotorPort = 2;
    public static final int rearRighttMotorPort = 3;
    public static final int armAngleTalonPort = 4;
    public static final int armHookTalonPort = 5;
    public static final int indexerWheels = 6;


    //DIO port declaration
    public static final int topLimitSwitchClimberPort = 0;
    public static final int bottomLimitSwitchClimberPort = 1;
    public static final int shooterBottomLimitPort = 2;
    public static final int shooterShootingLimitPort = 3;
    public static final int shooterTachometerPort = 4;
    
    
}
