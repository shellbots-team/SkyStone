package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by shell on 09/24/2019.
 */

public abstract class BasicBaseplate extends BaseAutonomous {

	@Override
	public void runOpMode() {

		final double DISTANCE_TO_BASEPLATE = 14.8;
		final double SAFE_GUARD = 2;

		super.runOpMode();

		// Step 0 - Ready to run
		logger.statusLog(step++, "Ready To Run");

		// Waiting until user presses start
		waitForStart();

		// Step 1 - Making sure base plate servos are up
		logger.statusLog(step++, "Making sure baseplate servos are up");
		robot.releaseBaseplate();

		// Step 2 - Moving to be on the corner
		logger.statusLog(step++, "Moving to be on the corner");
		moveTowardsBuildingZone(1150, 1);

		// Step 3 - Moving to be aligned with center of baseplate
		logger.statusLog(step++, "Moving to be aligned with center of baseplate");
		moveTowardsLoadingZone(450, 1);
		sleep(100);

		// Step 3 - Move near the baseplate
		logger.statusLog(step++, "Moving near the baseplate");
		robot.drivetrain.runDistance(DISTANCE_TO_BASEPLATE - SAFE_GUARD, DISTANCE_TO_BASEPLATE - SAFE_GUARD);

		// Step 4 - Moving next to the baseplate
		logger.statusLog(step++, "Moving next to the baseplate");
		robot.drivetrain.runDistance(SAFE_GUARD, SAFE_GUARD, 0.5, 3);
		sleep(250);

		// Step 5 - Grab onto the baseplate
		logger.statusLog(step++, "Grabbing the baseplate");
		robot.grabBaseplate();
		sleep(1000);

		// Step 6 - Drag baseplate to the corner
		logger.statusLog(step++, "Dragging the baseplate to the corner");
		robot.drivetrain.runDistance(-(DISTANCE_TO_BASEPLATE + 4), -(DISTANCE_TO_BASEPLATE + 4));

		// Step 7 - Release the baseplate
		logger.statusLog(step++, "Releasing the baseplate");
		robot.releaseBaseplate();
		sleep(100);

		// Step 8 - Move away from build zone
		logger.statusLog(step++, "Moving away from the build zone");
		moveTowardsLoadingZone(1100, 1.0);

		// Step 9 - Moving next to the baseplate
		logger.statusLog(step++, "Moving next to the baseplate");
		robot.drivetrain.runDistance(7, 7);

		// Step 10 - Pushing baseplate into wall
		logger.statusLog(step++, "Pushing baseplate into the wall");
		moveTowardsBuildingZone(350, 1.0);

		// Step 11 - Moving away from the baseplate
		logger.statusLog(step++, "Moving away from the baseplate");
		moveTowardsLoadingZone(450, 1.0);

		// Step 12 - Moving to wall, or to center
		if (getFinalPlacement() == Placement.CENTER) {
			logger.statusLog(step++, "Moving to the center");
			robot.drivetrain.runDistance(4.55, 4.55);
		} else {
			logger.statusLog(step++, "Moving to the wall");
			robot.drivetrain.runDistance(-7, -7);
		}


		// Step 13 - Driving until on the color line
		logger.statusLog(step++, "Driving until on the colored line");
		moveTowardsLoadingZone(550, 1.0);

		// Step 14 - Fixing positioning on the line
		logger.statusLog(step++, "Fixing positioning on the line");
		moveTowardsBuildingZone(250, 1.0);

		// Step 15 - Run into wall or into bridge
		if (getFinalPlacement() == Placement.CENTER) {
			logger.statusLog(step++, "Running into bridge");
			robot.drivetrain.runDistance(1, 1);
		} else {
			logger.statusLog(step++, "Running into wall");
			robot.drivetrain.runDistance(-2, -2);
		}

		// Step 16 - Finished
		logger.statusLog(step++, "Finished");
		robot.stopAllMotors();
	}
}
