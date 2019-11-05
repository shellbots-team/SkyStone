package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by shell on 09/24/2019.
 */

@Autonomous(group = "Basic", name = "Basic: SkyStone")
public abstract class BasicSkyStone extends BaseAutonomous {

	@Override
	void moveTowardsLoadingZone(long milliseconds, double speed) {
		if (getColor() == Color.BLUE) {
			robot.drivetrain.setAllPowers(speed);
		}
		if (getColor() == Color.RED) {
			robot.drivetrain.setAllPowers(-speed);
		}
		if (milliseconds == 0) {
			return;
		}
		sleep(milliseconds);
		robot.drivetrain.setAllPowers(0);
	}

	private void moveTowardsLoadingZone(double leftInches, double rightInches) {
		robot.drivetrain.runDistance(leftInches, rightInches);
	}

	@Override
	void moveTowardsBuildingZone(long milliseconds, double speed) {
		if (getColor() == Color.BLUE) {
			robot.drivetrain.setAllPowers(-speed);
		}
		if (getColor() == Color.RED) {
			robot.drivetrain.setAllPowers(speed);
		}
		if (milliseconds == 0) {
			return;
		}
		sleep(milliseconds);
		robot.drivetrain.setAllPowers(0);
	}

	private void moveTowardsBuildingZone(double leftInches, double rightInches) {
		robot.drivetrain.runDistance(leftInches, rightInches);
	}

	@Override
	public void runOpMode() {

		super.runOpMode();

		// Step 0 - Ready to run
		logger.statusLog(step++, "Ready to run");

		// Waiting until user presses start
		waitForStart();

		// Step 1 - Initializing object detection
		logger.statusLog(step++, "Initializing object detection");
		robot.objectDetection.initializeObjectDetection();

		// Step 2 - Driving until on the center tape
		logger.statusLog(step++, "Driving until on the center tape");
		moveTowardsBuildingZone(0, 0.75);
		robot.stopWhenOnLine(2);

		// Step 3 - Driving to be aligned with the last block
		logger.statusLog(step++, "Driving to be aligned with final block");
		moveTowardsLoadingZone(5, 5);

		// Step 4 - Drive to be near the SkyStone
		logger.statusLog(step++, "Driving to be near the SkyStones");
		robot.drivetrain.setPowerLeft(1.0);
		sleep(1350);
		robot.drivetrain.stopAllMotors();

		// Step 5 - Raising arm
		logger.statusLog(step++, "Raising arm");
		robot.arm.raiseArm();

		// Step 6 - Slowly driving next to blocks
		logger.statusLog(step++, "Slowly driving next to blocks");
		moveTowardsLoadingZone(0, 0.5);

		// Step 7 - Stopping when block is detected
		logger.statusLog(step++, "Stopping when block is detected");
		ElapsedTime elapsedTime = new ElapsedTime();
		while (elapsedTime.seconds() < 3 && opModeIsActive() && !robot.objectDetection.isSkyStone()) {}
		robot.drivetrain.stopAllMotors();

		// Step 8 - Moving next to the correct block
		logger.statusLog(step++, "Moving next to the correct block");
		robot.drivetrain.setPowerLeft(0.75);
		sleep(600);
		robot.drivetrain.stopAllMotors();

		// Step 9 - Lowering arm
		logger.statusLog(step++, "Lowering arm");
		robot.arm.lowerArm();
		sleep(250);

		// Step 10 - Closing hand on block
		logger.statusLog(step++, "Closing hand on block");
		robot.arm.grabHand();
		sleep(500);

		// Step 11 - Re-raise the arm
		logger.statusLog(step++, "Re-raising the arm");
		robot.arm.raiseArm();

		// Step 12 - Turning to face the baseplate
		logger.statusLog(step++, "Turning to face the baseplate");
		robot.drivetrain.turnDegrees(90, true, 1.0);

		// Step 13 - Lowering the arm
		logger.statusLog(step++, "Lowering the arm");
		robot.arm.lowerArm();

		// Step 14 - Driving until on midline
		logger.statusLog(step++, "Driving until on the midline");
		moveTowardsBuildingZone(0, 1.0);
		robot.stopWhenOnLine(4.5);

		// Step 15 - Driving until at baseplate
		logger.statusLog(step++, "Driving until at baseplate");
		moveTowardsLoadingZone(12, 12);

		// Step 16 - Lowering arm onto baseplate
		logger.statusLog(step++, "Lowering arm onto baseplate");
		robot.arm.lowerArm();

		// Step 17 - Releasing hand/block
		logger.statusLog(step++, "Releasing hand/block");
		robot.arm.releaseHand();

		// Step 18 - Re-raising the arm
		logger.statusLog(step++, "Re-raising the arm");
		robot.arm.raiseArm();

		// Step 19 - Moving away from the baseplate
		logger.statusLog(step++, "Moving away from the baseplate");
		moveTowardsLoadingZone(-4.0, -4.0);

		// Step 20 - Moving to required parking position
		logger.statusLog(step++, "Moving to required parking position");
		if (getFinalPlacement() == Placement.WALL) {
			robot.drivetrain.runDistance(-5, -5);
		}

		// Step 21 - Moving to the midline
		logger.statusLog(step++, "Moving to the midline");
		moveTowardsLoadingZone(0, 1.0);
		robot.stopWhenOnLine(4);

		// Step 22 - Correcting self on midline
		logger.statusLog(step++, "Correcting self on the midline");
		if (getFinalPlacement() == Placement.CENTER) {
			robot.drivetrain.runDistance(1, 1);
		} else {
			robot.drivetrain.runDistance(-2, -2);
		}

		// Step 22 - Finished
		logger.statusLog(step++, "Finished");
		robot.stopAllMotors();
	}
}
