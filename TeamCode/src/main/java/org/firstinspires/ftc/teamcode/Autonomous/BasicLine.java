package org.firstinspires.ftc.teamcode.Autonomous;

/**
 * Created by shell on 10/26/2019.
 */

public abstract class BasicLine extends BaseAutonomous {

	protected abstract Side getStartingSide();

	protected enum Side {
		SKYSTONE,
		Foundation
	}

	@Override
	public void runOpMode() {
		super.runOpMode();

		waitForStart();

		robot.grabber.raise();
		robot.grabFoundation();

		// Step 1 - Moving to the center or doing nothing
		if (getFinalPlacement() == Placement.CENTER) {
			logger.statusLog(step++, "Moving to the center");
			robot.drivetrain.runDistance(29, 29);
		} else {
			logger.statusLog(step++, "N/A");
		}

		// Step 2 - Driving onto the midline
		logger.statusLog(step++, "Driving to midline");
		if (getStartingSide() == Side.SKYSTONE) {
			moveTowardsBuildingZone(18, 999);
		} else if (getStartingSide() == Side.Foundation) {
			moveTowardsLoadingZone(20, 999);
		}

		// Step 5 - Running into wall/bridge
		if (getFinalPlacement() == Placement.CENTER) {
			logger.statusLog(step++, "Running into bridge");
			robot.drivetrain.runDistance(1, 1);
		} else {
			logger.statusLog(step++, "Running into wall");
			robot.drivetrain.runDistance(-2, -2);
		}

	}

}