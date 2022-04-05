// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.AutonomousDriveToBall;
import frc.robot.commands.AutonomousShootBall;
import frc.robot.commands.AutonomousGroup;
import frc.robot.commands.ManualDrive;
import frc.robot.commands.ControlArm;
import frc.robot.commands.FlywheelShooter;
import frc.robot.commands.ControlHooks;
import frc.robot.commands.ShooterAimer;
import frc.robot.commands.UnlockIngester;
import frc.robot.commands.IngestBalls;
import frc.robot.commands.IndexBalls;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ClimberArm;
import frc.robot.subsystems.ClimberHooks;
import frc.robot.subsystems.Ingester;
import frc.robot.subsystems.IngesterPositioner;
import frc.robot.subsystems.Tachometer;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

import frc.robot.controllers.*;
import edu.wpi.first.wpilibj.Joystick;

public class RobotContainer {
  public static boolean inTeleop = true;
  public final static DriveTrain driveTrain = new DriveTrain();

  public final static Ingester ingester = new Ingester();
  public final static IngesterPositioner ingestPos = new IngesterPositioner();
  public final static UnlockIngester unlockIngester = new UnlockIngester(ingestPos);

  public final static Shooter shooter = new Shooter();

  public final static Tachometer tacheo = new Tachometer();
  public final static Indexer indexer = new Indexer();

  //public final static AimbotBall aimball = new AimbotBall(driveTrain);

  public static Tango2Controller tangoIIController = new Tango2Controller(new Tango2Joystick(1));
  public static FlightJoystickController flightJoystick = new FlightJoystickController(new Joystick(0));

  public final static ClimberHooks hooks = new ClimberHooks();
  public final static ClimberArm arm = new ClimberArm();
  public final static ControlArm controlArm = new ControlArm(arm);
  public final static ControlHooks controlHooks = new ControlHooks(hooks);
  // public final static AutoClimb autoClimb = new AutoClimb(climber);

  // declare new shooter airmer to be ran, for driveTrain
  public final static IndexBalls index = new IndexBalls(indexer, shooter);
  public final static IngestBalls ingest = new IngestBalls(ingester, indexer);
  public final static FlywheelShooter flywheelShooter = new FlywheelShooter(tacheo, shooter, indexer, index);
  public final static ShooterAimer adjustAim = new ShooterAimer(driveTrain);
  public final static AutonomousDriveToBall autonomousDrive = new AutonomousDriveToBall(driveTrain, hooks, arm, ingestPos, shooter, tacheo);
  public final static AutonomousShootBall autonomousShoot = new AutonomousShootBall(driveTrain, hooks, arm, ingestPos, shooter, tacheo);
  public final static ManualDrive driveCommand = new ManualDrive(driveTrain);
  //public final static AimbotBall aimBall = new AimbotBall(driveTrain); 

  //
  // public final static SequentialCommandGroup a = new
  // SequentialCommandGroup(flywheelShooter, autonomousCommand);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    CameraServer.startAutomaticCapture();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  public void configureButtonBindings() {
    /*
     * coDriverStick.btn_1.whileHeld(shootBall);
     * coDriverStick.btn_12.whenPressed(turretPresets);
     */
    // controller might not actually have any buttons, might be useless code
    /*
     * JoystickButton dL = new JoystickButton(driverStick.joystick, 1);
     * dL.whenPressed(servoMove);
     */
    // JoystickButton collectButton = new JoystickButton(driverStick.joystick, 1);
    // collectButton.whileHeld(autonomousCommand);

    // when press button "1" on frc will run shooterAimer, follow shooterAimer for
    // more info
    // Joystick joystick = new Joystick(0);
    // JoystickButton shooterButton = new JoystickButton(flightJoystick.joystick, 1);
    // shooterButton.whileActiveContinuous(adjustShooter);
    // shooterButton.whenReleased(() -> adjustShooter.cancel());

    // Trigger hookTrigger = new Trigger(() -> tangoIIController.getSlider() > .5);
    // hookTrigger.whileActiveContinuous(controlHooks);
    // hookTrigger.whenActive(() -> controlHooks.cancel());

    // Trigger driveTrigger = new Trigger(() -> tangoIIController.getSlider() <= .5);
    // driveTrigger.whileActiveContinuous(driveCommand);
    // driveTrigger.whenActive(() -> driveCommand.cancel());

    // JoystickButton flywheelButton = new JoystickButton(flightJoystick.joystick, 5);
    // flywheelButton.whenPressed(flywheelShooter);

    // JoystickButton ingestButton = new JoystickButton(flightJoystick.joystick, 7);
    // ingestButton.whenPressed(ingest);
    // ingestButton.whenReleased(index);

    // JoystickButton spitBallButton = new JoystickButton(driverStick.joystick, 6);
    // spitBallButton.whenHeld();
    // spitBallButton.whenReleased();

    //JoystickButton aimbotButton = new JoystickButton(flightJoystick.joystick, Constants.aimbotButtonNumber);
    //aimbotButton.whenHeld(aimBall);
    /**
     * Get the slider position of the HID.
     *
     * @return the z position
     */

    // dR.whenPressed(servoMove);
    // JoystickButton manualButton = new JoystickButton(driverStick.joystick, 2);
    // manualButton.whenHeld(autoClimb);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

   /*
    inTeleop = false;
    Timer t = new Timer();
    t.start();
    indexer.setIndexSpeed(.3);
    ingester.setIngestRollerSpeed(.5);

    // autonomousCommand.schedule();
    // while (!timer.hasElapsed(2)) {}
    // shooter.setShooterSpeed(.36); // do change lol .3 old
    while (t.get() <3) {
      // ingestPos.changeIngestAngle(-0.5);
      driveTrain.drive(.6 * (t.get() / 2.5), 0);

    }
    driveTrain.drive(0, 0);
    indexer.setIndexSpeed(0);
    ingester.setIngestRollerSpeed(0);
    // ingestPos.changeIngestAngle(0);
*/
  public void autonomousInit() {
    inTeleop = false;
    // autonomousCommand.schedule();
  }

  public void teleopInit() {
    inTeleop = true;
    configureButtonBindings();
    // driveTrain.setDefaultCommand(adjustAim);
    driveTrain.setDefaultCommand(driveCommand);
    //ingester.setDefaultCommand(ingest);
    // ingestPos.setDefaultCommand(unlockIngester);
    // hooks.setDefaultCommand(manualClimb);
    // driveTrain.setDefaultCommand(manualDrive);
    // indexer.setDefaultCommand(index);
  }
}
