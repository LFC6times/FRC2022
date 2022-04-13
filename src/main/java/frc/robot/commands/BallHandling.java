package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.BottomIndexer;
import frc.robot.subsystems.TopIndexer;
import frc.robot.RobotContainer;
import frc.robot.Constants;

/**
 * Handles all index/ingest/shooting code
 */

public class BallHandling extends CommandBase {
  private final Shooter shoot;
  private final BottomIndexer bottomIndex;
  private final TopIndexer topIndex;
  private final Timer timer = new Timer();

  private double ingestSpeed = -0.4;
  private double indexSpeed = 0.2;
  private double shootIndexSpeed = 0.8;
  /**
   * allowed variance of RPM lower threshold (in case of PID inaccuracy),
   * and upper (in case of tachometer RPM spikes)
   */
  private double delta1 = 50;
  private double delta2 = 2000;
  public double desiredRPM = 1000;
  public boolean previousLimitState;
  private boolean bottomBallLoaded = false;

  private ShootingStates state = ShootingStates.INDEX_FIRST_BALL;

  public BallHandling(Shooter shoot, BottomIndexer bottomIndex, TopIndexer topIndex) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shoot = shoot;
    this.bottomIndex = bottomIndex;
    this.topIndex = topIndex;
    addRequirements(shoot, bottomIndex, topIndex);
  }

  private enum ShootingStates {
    INDEX_FIRST_BALL,
    INDEX_SECOND_BALL,
    PREPARE_TO_SHOOT,
    ACCELERATE_FLYWHEEL
  }

  @Override
  public void initialize() {
    timer.start(); // Note to future us: remember to always timer.start();
  }

  @Override
  public void execute() {
    // RESETS THE INDEXING
    if (RobotContainer.secondaryJoystick.joystick.getRawButtonPressed(Constants.resetIndexerAndIngesterButtonNumber)) {
      state = ShootingStates.INDEX_FIRST_BALL;
    }

    // ROLLS THE BOTTOM INDEXER
    if (RobotContainer.primaryJoystick.joystick.getRawButton(Constants.rollIngesterButtonNumber) && !bottomBallLoaded) {
      bottomIndex.setIndexSpeed(ingestSpeed);
    } else if (!bottomBallLoaded) {
      bottomIndex.setIndexSpeed(0);
    }

    // SHOOTS THE BALL
    if (RobotContainer.secondaryJoystick.joystick.getRawButton(Constants.shootBallsButtonNumber)) {
      timer.reset();
      // System.out.println("AAAAA PREP TO SHOOT");
      topIndex.setIndexSpeed(shootIndexSpeed);
      state = ShootingStates.ACCELERATE_FLYWHEEL;
    }

    // SWITCH STATES FOR INDEXING AND SHOOTING SEQUENCE
    switch (state) {
      case INDEX_FIRST_BALL:
        // bottomIndex.setIndexSpeed(ingestSpeed);
        bottomBallLoaded = false;
        topIndex.setIndexSpeed(indexSpeed);
        if (shoot.getTopShooterLim()) {
          topIndex.setIndexSpeed(0);
          state = ShootingStates.INDEX_SECOND_BALL;
        }
        break;

      case INDEX_SECOND_BALL:
        // bottomIndex.setIndexSpeed(ingestSpeed);

        if (shoot.getBottomShooterLim()) {
          bottomBallLoaded = true;
          bottomIndex.setIndexSpeed(0);
          state = ShootingStates.PREPARE_TO_SHOOT;
        }
        break;

      case PREPARE_TO_SHOOT:
        timer.reset();
        break;

      case ACCELERATE_FLYWHEEL:
        // System.out.println("ACC FLYWHEEL");
        // shoot.setShooterPower(shooterPOWER);
        if (timer.hasElapsed(0.5)) {
          bottomIndex.setIndexSpeed(-shootIndexSpeed);
          // topIndex.setIndexSpeed(0);

        }
        if (timer.hasElapsed(2)) {
          bottomIndex.setIndexSpeed(0);
          topIndex.setIndexSpeed(0);
          state = ShootingStates.INDEX_FIRST_BALL;
        }
        /*
         * currentRPM = Tachometer.getShooterRPM();
         * System.out.println(currentRPM);
         * desiredRPM = shoot.getRPMValue();
         * //shoot.setShooterToRPM();
         * shoot.setShooterPower(0.8);
         * if (currentRPM >= desiredRPM - delta1 && currentRPM <= desiredRPM + delta2) {
         * // if(currentRPM >= desiredRPM) {
         * 
         * state = ShootingStates.SHOOT_FIRST_BALL;
         * }
         */

        break;

      default:
        System.err.println("No state is true");
        break;
    }

  }

  public void resetSwitchState() {
    state = ShootingStates.INDEX_FIRST_BALL;
  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
