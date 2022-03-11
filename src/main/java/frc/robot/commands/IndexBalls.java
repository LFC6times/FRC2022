package frc.robot.commands;

import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class IndexBalls extends CommandBase {
  private final Indexer index;
  private final Shooter shoot;
  public double speed = .25;
  public final double FLYWHEEL_SPEED = -0.3;

  public IndexBalls(Indexer indexer, Shooter shooter) {
    index = indexer;
    shoot = shooter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(index);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("init indexballs");
    index.setIndexSpeed(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    index.setIndexSpeed(-speed);
    shoot.setShooterSpeed(FLYWHEEL_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shoot.setShooterSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}