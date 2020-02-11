package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by shell on 09/24/2019.
 */

public abstract class BasicBaseplate extends BaseAutonomous {

	@Override
	public void runOpMode() {

		final boolean PUSH_BASEPLATE = false;
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

		moveTowardsBuildingZone(50, 999, 0.7); // Drive into wall
		//robot.drivetrain.runDistance(10, 10);

		robot.logTeleOpData();

		// Step 16 - Finished
		logger.statusLog(step++, "Finished");
		robot.stopAllMotors();
	}
}
