// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.sql.Driver;

import org.opencv.core.RotatedRect;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.HIDType;
import edu.wpi.first.wpilibj.simulation.JoystickSim;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.CollectBalls;
import frc.robot.commands.LoadBalls;
import frc.robot.commands.ManualDrive;
import frc.robot.commands.ServoMove;
import frc.robot.commands.ShooterAimer;
import frc.robot.commands.AutoClimb;
import frc.robot.commands.ManualClimb;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Ingester;
import frc.robot.subsystems.Servos;
import frc.robot.subsystems.Climber;
import frc.robot.JoystickPlus;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain = new DriveTrain();

  private final ManualDrive driveCommand = new ManualDrive(driveTrain);
 
  private final Servos servos = new Servos();

  private final Ingester ingester = new Ingester();

  private final CollectBalls collect = new CollectBalls(ingester);

  public static Controller climberStick = new Controller(new JoystickPlus(0));

  public static Controller driverStick = new Controller(new JoystickPlus(1));

  private final AutonomousCommand autonomousCommand = new AutonomousCommand(driveTrain);

  private final Climber climber = new Climber();

  private final AutoClimb autoClimb = new AutoClimb(climber);

  private final ManualClimb manualClimb = new ManualClimb(climber);

  private final ServoMove servoMove = new ServoMove(servos, climber);
  
  private final ShooterAimer shooterAimer = new ShooterAimer(driveTrain);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    //servos.setDefaultCommand(servoMove);
    // ingester.setDefaultCommand();
    climber.setDefaultCommand(manualClimb);
    driveTrain.setDefaultCommand(shooterAimer);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /*
    coDriverStick.btn_1.whileHeld(shootBall);
    coDriverStick.btn_12.whenPressed(turretPresets);
    */
    //controller might not actually have any buttons, might be useless code
    /*JoystickButton dL = new JoystickButton(driverStick.joystick, 1);
    dL.whenPressed(servoMove); */
    //JoystickButton collectButton = new JoystickButton(driverStick.joystick, 1);
    //collectButton.whileHeld(autonomousCommand);

    JoystickButton dR = new JoystickButton(climberStick.joystick, 6);
    dR.whenPressed(servoMove);
    JoystickButton manualButton = new JoystickButton(climberStick.joystick, 2);
    manualButton.whenHeld(autoClimb);

    Joystick joystick = new Joystick(0);
    JoystickButton joystickButton = new JoystickButton(joystick, 1);
    joystickButton.toggleWhenActive(shooterAimer);


    

    /**
   * Get the slider position of the HID.
   *
   * @return the z position
   */

    //dR.whenPressed(servoMove);
    //JoystickButton manualButton = new JoystickButton(driverStick.joystick, 2);
    //manualButton.whenHeld(autoClimb);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return driveCommand;
  }
}
