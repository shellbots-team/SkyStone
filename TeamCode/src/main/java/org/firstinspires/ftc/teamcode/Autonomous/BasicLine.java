package org.firstinspires.ftc.teamcode.Autonomous;

/**
 * Created by shell on 10/26/2019.
 */

public abstract class BasicLine extends BaseAutonomous {

	protected abstract Side getStartingSide();

	protected enum Side {
		SKYSTONE,
		BASEPLATE
	}

	@Override
	public void runOpMode() {
		super.runOpMode();

		waitForStart();

		// Step 1 - Moving to the center or doing nothing
		if (getFinalPlacement() == Placement.CENTER) {
			logger.statusLog(step++, "Moving to the center");
			robot.drivetrain.runDistance(13, 13);
		} else {
			logger.statusLog(step++, "N/A");
		}

		// Step 2 - Driving onto the midline
		logger.statusLog(step++, "Driving to midline");
//		if (getStartingSide() == Side.SKYSTONE) {
//			moveTowardsBuildingZone(0, 1.0);
//		} else {
//			moveTowardsLoadingZone(0, 1.0);
//		}
		moveTowardsBuildingZone(0, 1.0);

		// Step 3 - Stopping when on the line
		logger.statusLog(step++, "Stopping when on the midline");
		robot.stopWhenOnLine(4.5);

		// Step 4 - Correcting self on line
		logger.statusLog(step++, "Correcting self on line");
//		if (getStartingSide() == Side.SKYSTONE) {
//			moveTowardsLoadingZone(250, 1.0);
//		} else {
//			moveTowardsBuildingZone(250, 1.0);
//		}
		moveTowardsLoadingZone(250, 1);

		// Step 5 - Running into wall/bridge
//		if (getFinalPlacement() == Placement.CENTER) {
//			logger.statusLog(step++, "Running into bridge");
//			robot.drivetrain.runDistance(1, 1);
//		} else {
//			logger.statusLog(step++, "Running into wall");
//			robot.drivetrain.runDistance(-2, -2);
//		}

	}

}