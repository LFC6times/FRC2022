// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Ingester;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IngestBalls extends CommandBase {
  private final Ingester ingest;
  private final Indexer index;
  private double power;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IngestBalls(Ingester subsystem, Indexer indexer) {
    ingest = subsystem;
    index = indexer;
    power = 0.6;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem, indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      ingest.setIngestRollerSpeed(0);
      System.out.println("ingesting");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ingest.setIngestRollerSpeed(-power);
    index.setIndexSpeed(-0.6);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ingest.setIngestRollerSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
