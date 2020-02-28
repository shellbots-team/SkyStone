package org.firstinspires.ftc.teamcode.Autonomous;

/**
 * Created by shell on 09/24/2019.
 */

public abstract class BasicFoundationPull extends BaseAutonomous {

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

		moveTowardsBuildingZone(14, 999); // Run into wall

		moveTowardsLoadingZone(8, 999); // Move to be positioned at center of Foundation

		robot.drivetrain.runDistance(-2, -2, 999, 0.4); // align on wall

		robot.drivetrain.runDistance(34, 34); // Run into Foundation

		robot.drivetrain.runDistance(4, 4, 999, 0.2);

		robot.grabFoundation(); // Grab the Foundation

		sleep(1000);

		robot.drivetrain.runDistance(-40, -40);

		robot.releaseFoundation();

		moveTowardsLoadingZone(14, 999); // Move next to the Foundation

		robot.drivetrain.runDistance(20, 20);

		moveTowardsBuildingZone(6, 999);

		moveTowardsLoadingZone(4, 999);

		if(getFinalPlacement() == Placement.CENTER) {
			robot.drivetrain.runDistance(7, 7);
		} else {
			if(getColor() == Color.RED) {
				robot.drivetrain.runDistance(-16, -16);
			} else {
				robot.drivetrain.runDistance(-18, -18);
			}
		}

		moveTowardsLoadingZone(10, 999);

		if(getFinalPlacement() == Placement.WALL) { robot.drivetrain.runDistance(-2, -2); }

		// Step 16 - Finished
		logger.statusLog(step++, "Finished");
		robot.stopAllMotors();
	}
}
