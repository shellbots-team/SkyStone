package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.teamcode.Autonomous.BaseAutonomous.Color.RED;

/**
 * Created by shell on 09/24/2019.
 */

public abstract class BasicFoundationTurn extends BaseAutonomous {

	@Override
	protected Placement getFinalPlacement() {
		return Placement.WALL;
	}

	@Override
	public void runOpMode() {

		super.runOpMode();

		robot.drivetrain.defaultSpeed = 0.4;

		// Step 0 - Ready to run
		logger.statusLog(step++, "Ready To Run");

		// Waiting until user presses start
		waitForStart();

		// Step 1 - Making sure base plate servos are up
		logger.statusLog(step++, "Making sure Foundation servos are up");
		robot.releaseFoundation();

		moveTowardsBuildingZone(4, 999);

		robot.drivetrain.runDistance(34, 34); // Run into Foundation

		robot.drivetrain.runDistance(4, 4, 999, 0.2);

		robot.grabFoundation(); // Grab the Foundation

		sleep(1000);

		robot.drivetrain.runDistance(-30, -30);

		if (getColor() == RED) {
			robot.drivetrain.turnDegrees(410, true, 0.6);
		} else {
			robot.drivetrain.turnDegrees(460, false, 0.6);
		}

		robot.releaseFoundation();

		robot.drivetrain.runDistance(6, 6);

		robot.drivetrain.runDistance(-4, -4);

		if (getColor() == RED) {
			moveTowardsBuildingZone(5, 999);
		} else {
			moveTowardsBuildingZone(5, 999);
		}

		robot.drivetrain.runDistance(-43, -43);

		moveTowardsBuildingZone(3, 999);

		// Step 16 - Finished
		logger.statusLog(step++, "Finished");
		robot.stopAllMotors();
	}
}
