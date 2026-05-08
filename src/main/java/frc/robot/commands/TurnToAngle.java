package frc.robot.commands;

import java.util.function.DoubleSupplier;

<<<<<<< HEAD
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
=======
import edu.wpi.first.wpilibj2.command.Command;
>>>>>>> 5ad2726df04907c16c7e32c8bd0e8ba4619a61ca
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utils.APPID;
import frc.robot.utils.Calculations;

public class TurnToAngle extends Command {
  private static final double kTurnP = 0.02;
  private static final double kTurnI = 0.0;
  private static final double kTurnD = 0.0;
  private static final double kMaxRot = 0.75;

  private final DriveSubsystem driveSubsystem;
  private final APPID turnPID;
  private final DoubleSupplier requestedAngleSupplier;
  private final boolean isRelative;
<<<<<<< HEAD
  private final DoubleSupplier forwardSupplier;
  private final DoubleSupplier strafeSupplier;
  private final double translationScale;
=======
>>>>>>> 5ad2726df04907c16c7e32c8bd0e8ba4619a61ca

  private double targetAngleDeg;

  public TurnToAngle(
      DriveSubsystem driveSubsystem,
      DoubleSupplier requestedAngleSupplier,
      double angleToleranceDeg,
<<<<<<< HEAD
      boolean isRelative,
      DoubleSupplier forwardSupplier,
      DoubleSupplier strafeSupplier,
      double translationScale) {
    this.driveSubsystem = driveSubsystem;
    this.requestedAngleSupplier = requestedAngleSupplier;
    this.isRelative = isRelative;
    this.forwardSupplier = forwardSupplier;
    this.strafeSupplier = strafeSupplier;
    this.translationScale = MathUtil.clamp(translationScale, 0.0, 1.0);
=======
      boolean isRelative) {
    this.driveSubsystem = driveSubsystem;
    this.requestedAngleSupplier = requestedAngleSupplier;
    this.isRelative = isRelative;
>>>>>>> 5ad2726df04907c16c7e32c8bd0e8ba4619a61ca

    turnPID = new APPID(kTurnP, kTurnI, kTurnD, angleToleranceDeg);
    turnPID.setMaxOutput(kMaxRot);

    addRequirements(driveSubsystem);
  }

  public TurnToAngle(DriveSubsystem driveSubsystem, double targetAngleDeg) {
<<<<<<< HEAD
    this(driveSubsystem, () -> targetAngleDeg, 1.0, false, () -> 0.0, () -> 0.0, 0.0);
  }

  public TurnToAngle(DriveSubsystem driveSubsystem, double targetAngleDeg, double angleToleranceDeg) {
    this(driveSubsystem, () -> targetAngleDeg, angleToleranceDeg, false, () -> 0.0, () -> 0.0, 0.0);
=======
    this(driveSubsystem, () -> targetAngleDeg, 2.0, false);
  }

  public TurnToAngle(DriveSubsystem driveSubsystem, double targetAngleDeg, double angleToleranceDeg) {
    this(driveSubsystem, () -> targetAngleDeg, angleToleranceDeg, false);
>>>>>>> 5ad2726df04907c16c7e32c8bd0e8ba4619a61ca
  }

  public TurnToAngle(
      DriveSubsystem driveSubsystem,
      DoubleSupplier targetAngleSupplier,
      double angleToleranceDeg) {
<<<<<<< HEAD
    this(driveSubsystem, targetAngleSupplier, angleToleranceDeg, false, () -> 0.0, () -> 0.0, 0.0);
  }

  public TurnToAngle(
      DriveSubsystem driveSubsystem,
      DoubleSupplier targetAngleSupplier,
      double angleToleranceDeg,
      DoubleSupplier forwardSupplier,
      DoubleSupplier strafeSupplier,
      double translationScale) {
    this(
        driveSubsystem,
        targetAngleSupplier,
        angleToleranceDeg,
        false,
        forwardSupplier,
        strafeSupplier,
        translationScale);
  }

  public static TurnToAngle relative(DriveSubsystem driveSubsystem, double deltaAngleDeg) {
    return new TurnToAngle(
        driveSubsystem, () -> deltaAngleDeg, 2.0, true, () -> 0.0, () -> 0.0, 0.0);
=======
    this(driveSubsystem, targetAngleSupplier, angleToleranceDeg, false);
  }

  public static TurnToAngle relative(DriveSubsystem driveSubsystem, double deltaAngleDeg) {
    return new TurnToAngle(driveSubsystem, () -> deltaAngleDeg, 2.0, true);
>>>>>>> 5ad2726df04907c16c7e32c8bd0e8ba4619a61ca
  }

  public static TurnToAngle relative(
      DriveSubsystem driveSubsystem,
      double deltaAngleDeg,
      double toleranceDeg) {
<<<<<<< HEAD
    return new TurnToAngle(
        driveSubsystem, () -> deltaAngleDeg, toleranceDeg, true, () -> 0.0, () -> 0.0, 0.0);
=======
    return new TurnToAngle(driveSubsystem, () -> deltaAngleDeg, toleranceDeg, true);
>>>>>>> 5ad2726df04907c16c7e32c8bd0e8ba4619a61ca
  }

  @Override
  public void initialize() {
    turnPID.reset();
  }

  @Override
  public void execute() {
    double currentAngle = Calculations.normalizeAngle360(driveSubsystem.getPose().getAngle());
    double requestedAngleDeg = requestedAngleSupplier.getAsDouble();

    if (isRelative) {
      targetAngleDeg = Calculations.normalizeAngle360(currentAngle + requestedAngleDeg);
    } else {
      targetAngleDeg = Calculations.normalizeAngle360(requestedAngleDeg);
    }

    double angleError = Calculations.shortestAngularDistance(targetAngleDeg, currentAngle);

    turnPID.setDesiredValue(0.0);
    double rotCmd = turnPID.calculate(-angleError);
<<<<<<< HEAD
    double xCmd =
        -MathUtil.applyDeadband(forwardSupplier.getAsDouble(), OIConstants.kDriveDeadband)
            * translationScale;
    double yCmd =
        -MathUtil.applyDeadband(strafeSupplier.getAsDouble(), OIConstants.kDriveDeadband)
            * translationScale;

    driveSubsystem.drive(xCmd, yCmd, rotCmd, true);
=======

    driveSubsystem.drive(0.0, 0.0, rotCmd, true);
>>>>>>> 5ad2726df04907c16c7e32c8bd0e8ba4619a61ca
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsystem.drive(0.0, 0.0, 0.0, true);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
