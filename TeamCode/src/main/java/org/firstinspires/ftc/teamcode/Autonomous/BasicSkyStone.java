package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Robot.ObjectDetection;

/**
 * Created by shell on 09/24/2019.
 */
@Disabled
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
		robot.drivetrain.runDistance(-leftInches, -rightInches);
	}

	@Override
	// TODO: Test this and movetowardsloading zone
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

		waitForStart();

		robot.arm.raiseArm(false);
		//TODO: extend arm at the start
		robot.arm.maintainPosition();

		robot.drivetrain.runDistance(-7, 7, 7, -7, 1.0, 999);

		robot.objectDetection.initializeObjectDetection();

		sleep(1000);

		Recognition stone = robot.objectDetection.getSkyStone();
		logger.completeLog("Right Side", String.valueOf(stone.getRight()));

		byte position = 0;
		final byte BASEPLATE_SIDE = 1;
		final byte MIDDLE_SIDE = 0;
		final byte SKYSTONE_SIDE = -1;

		if(stone.getRight() < 300) {
			position = -1;
		} else if (stone.getRight() > 600) {
			position = 1;
		}

		if(getColor() == Color.BLUE) { position *= -1; }

		logger.completeLog("Postion", String.valueOf(position));

		robot.drivetrain.runDistance(-4.4, 4.4, 4.4, -4.4, 1.0, 999);

		if(position == SKYSTONE_SIDE) {
			moveTowardsLoadingZone(3.0, 3.0);
		} else if(position == MIDDLE_SIDE) {
		} else if(position == BASEPLATE_SIDE) {
			moveTowardsBuildingZone(3.0, 3.0);
		}

		robot.arm.lowerArm(false);
		sleep(750);

		robot.arm.grabHand();
		sleep(1250);

		robot.arm.raiseArm(true);
		robot.arm.maintainPosition();

		robot.drivetrain.runDistance(1, -1, -1, 1, 1, 999);

		robot.drivetrain.turnDegrees(94, true, 1.0);

		robot.arm.lowerArm(true);

		int distanceToBaseplate = 0;

		if(position == SKYSTONE_SIDE) {
			distanceToBaseplate = 26;
		} else if(position == MIDDLE_SIDE) {
			distanceToBaseplate = 21;
		} else if(position == BASEPLATE_SIDE) {
			distanceToBaseplate = 16;
		}

		if(getColor() == Color.BLUE) { distanceToBaseplate *= -1; }

		robot.drivetrain.runDistance(-distanceToBaseplate, distanceToBaseplate, distanceToBaseplate, -distanceToBaseplate, 1.0, 999);

		robot.arm.releaseHand();
		robot.drivetrain.turnDegrees(10.5, true, 1.0);

		distanceToBaseplate += 9.7;

		robot.drivetrain.runDistance(distanceToBaseplate, -distanceToBaseplate, -distanceToBaseplate, distanceToBaseplate, 1.0, 999);

		robot.drivetrain.runDistance(-0.75, -0.75);

		robot.drivetrain.turnDegrees(100, false, 1.0);

		robot.drivetrain.runDistance(-1.3, 1.3, 1.3, -1.3, 1, 999);

		robot.arm.grabHand();
		sleep(500);

		robot.arm.raiseArm(true);
		robot.arm.maintainPosition();

		robot.drivetrain.runDistance(1.0,-1.0,-1.0,1.0,1.0,999);

		robot.drivetrain.turnDegrees(100, true, 1.0);

		robot.arm.lowerArm(true);

		robot.drivetrain.runDistance(-distanceToBaseplate, distanceToBaseplate, distanceToBaseplate, -distanceToBaseplate, 1.0, 999);

		robot.arm.releaseHand();

		robot.drivetrain.runDistance(5, -5, -5, 5, 1, 999);


		// move forward, grab block, ect...

//		robot.objectDetection.initializeObjectDetection();
//		sleep(20000);

//		robot.arm.extendWithPower(0.5);
//		sleep(500);
//		robot.arm.stopAllMotors();
//		sleep(4000);

//		robot.arm.grabHand();
//
//		sleep(1200);
//
//		robot.arm.raiseArm();
//
//		robot.arm.maintainPosition();
//
//		sleep(4000);
//
//		robot.arm.lowerArm();
//
//		sleep(1000);
//
//		robot.arm.releaseHand();
//
//		sleep(3000);

//		robot.arm.lowerArm();
//
//		robot.arm.releaseHand();
//
//		telemetry.addData("Finished", "!");
//		telemetry.update();

//		robot.arm.stopAllMotors();
//
//		lg(robot.arm.leftArm, robot.arm.rightArm);
//
//		sleep(2000);
//
//		lg(robot.arm.leftArm, robot.arm.rightArm);

/*
		// Step 0 - Ready to run
		logger.statusLog(step++, "Ready to run");

		// Waiting until user presses start
		waitForStart();

		// Step 2 - Drive to be near the SkyStone
		logger.statusLog(step++, "Driving to be near the SkyStones");
		robot.drivetrain.setPowerLeft(1.0);
		sleep(700);
		robot.drivetrain.stopAllMotors();

		// Step 5 - Raising arm
		logger.statusLog(step++, "Raising arm");
		robot.arm.raiseArm();

		// Step 1 - Initializing object detection
		logger.statusLog(step++, "Initializing object detection");
		robot.objectDetection.initializeObjectDetection();

		// Checking position of skystone
		logger.statusLog(step++, "Finding position of skystone");
		Recognition skystone = robot.objectDetection.getSkyStone();
		byte position;
		if(skystone.getRight() > 100) {
			position = 1;
		} else if(skystone.getRight() < -100) {
			position = -1;
		} else {
			position = 0;
		}

		logger.numberLog("Position", position);

		if(getColor() == Color.BLUE) { position *= -1; }

		logger.statusLog(step++, "Moving to position");
		if(position == 1) {
			moveTowardsBuildingZone(5.0, 5.0);
		} else if(position == -1) {
			moveTowardsLoadingZone(5.0, 5.0);
		}

		// Step - Extending arm
		logger.statusLog(step++, "Raising arm");
		robot.arm.extendWithPower(1);
		sleep(500);
		robot.arm.extendWithPower(0);

		// Step 6 - Slowly driving next to blocks
		logger.statusLog(step++, "Slowly driving next to blocks");
		robot.drivetrain.setPowerRight(0.25);
		sleep(250);
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
		sleep(500);

		// Step 12 - Turning to face the baseplate
		logger.statusLog(step++, "Turning to face the baseplate");
		if(getColor() == Color.RED) {
			robot.drivetrain.turnDegrees(90, true, 1.0);
		} else {
			robot.drivetrain.turnDegrees(90, false, 1.0);
		}

		// Step 13 - Lowering the arm
		logger.statusLog(step++, "Lowering the arm");
		robot.arm.lowerArm();
		sleep(500);

		// Step 14 - Driving until on midline
		logger.statusLog(step++, "Driving until on the midline");
		robot.drivetrain.setPowerRight(1.0);
		robot.stopWhenOnLine(4.5);

		// Step 15 - Driving until at baseplate
		logger.statusLog(step++, "Driving until at baseplate");
		moveTowardsLoadingZone(12.0, 12.0);

		// Step 16 - Lowering arm onto baseplate
		logger.statusLog(step++, "Lowering arm onto baseplate");
		robot.arm.lowerArm();

		// Step 17 - Releasing hand/block
		logger.statusLog(step++, "Releasing hand/block");
		robot.arm.releaseHand();

		// Step 18 - Re-raising the arm
		logger.statusLog(step++, "Re-raising the arm");
		robot.arm.raiseArm();
		sleep(250);

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
		*/
	}
}
