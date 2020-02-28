package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Robot.Grabber;

/**
 * Created by shell on 02/23/2020.
 */
@Autonomous(group = "A", name = "SkyStone Turning")
public class BasicSkyStonePlace extends BaseAutonomous {


	@Override
	protected Color getColor() {
		return Color.BLUE;
	}

	@Override
	protected Placement getFinalPlacement() {
		return Placement.CENTER;
	}

	@Override
	public void runOpMode() {

		super.runOpMode();

		if (getColor() == Color.BLUE) {
			robot.grabber.setSide(Grabber.Side.RIGHT);
		} else {
			robot.grabber.setSide(Grabber.Side.LEFT);
		}

// Setup object detection
		robot.objectDetection.initializeObjectDetection();

		robot.drivetrain.defaultSpeed = 0.4;//0.5;

// Wait until play is pushed
		waitForStart();

// Make sure Foundation servos are up
		robot.releaseFoundation();

		robot.grabber.home();
		robot.grabber.prepareForStone();

		moveTowardsBuildingZone(1, 999);

// Drive forward so blocks are within distance
		robot.drivetrain.runDistance(20, 20, 999);

		int sumOfPositions = 0;
		int iterationCount = 1;

// Start detecting objects
		for(int i = 0; i < iterationCount; i++) {
			robot.objectDetection.initializeTFOD();
			sleep(1250);
			Recognition stone = robot.objectDetection.getSkyStone();
			boolean offscreen = stone.getTop() == 12345f;
			float stoneX = (stone.getLeft() + stone.getRight()) / 2;
			logger.completeLog("I:"+i+" Stone X", String.valueOf(stoneX));
			if(getColor() == Color.RED) {
				if(offscreen || stoneX < 100) {
					sumOfPositions--;
				} else if(stoneX > 400) {
					sumOfPositions++;
				}
			} else {
				if(offscreen || stoneX > 600) {
					sumOfPositions--;
				} else if(stoneX < 400) {
					sumOfPositions++;
				}
			}
		}

		logger.numberLog("Initial sum", sumOfPositions);
		sumOfPositions /= iterationCount;
		sumOfPositions = Math.round(sumOfPositions);
		logger.numberLog("Final sum", sumOfPositions);
		logger.update();

		// TODO: Wait time in between movement to avoid large changes based on momentum

		if(getColor() == Color.RED) {
			if(sumOfPositions == 1) {
				moveTowardsBuildingZone(7, 999);
			} else if(sumOfPositions == 0) {
				moveTowardsBuildingZone(3, 999);
			} else {
				moveTowardsLoadingZone(1.5, 999);
			}
		} else {
			if(sumOfPositions == 1) {
				moveTowardsBuildingZone(6.75, 999);
			} else if(sumOfPositions == 0) {
				moveTowardsBuildingZone(2, 999);
			} else {
				moveTowardsLoadingZone(2, 999);
			}

		}

		sleep(500);

		robot.drivetrain.runDistance(11, 11, 999, 0.2);

		robot.grabber.grabStone();
		sleep(1500);
		robot.grabber.raiseStone();
		sleep(1000);

		if(getColor() == Color.RED) {
			robot.drivetrain.runDistance(-7, -7);
		} else {
			robot.drivetrain.runDistance(-5, -5);
		}

		sleep(500);

		robot.drivetrain.turnDegrees(270, false, 0.4);

		if(sumOfPositions == 1) {
			robot.drivetrain.runDistance(20, 999, 0.5);
		} else if (sumOfPositions == 0) {
			robot.drivetrain.runDistance(25, 999, 0.5);
		} else {
			robot.drivetrain.runDistance(30, 999, 0.5);
		}

		robot.grabber.dropBase();

		sleep(500);

		robot.grabber.dropAlt();

		sleep(500);

		robot.grabber.prepareForStone();
/*
		if(sumOfPositions == 1) {
			robot.drivetrain.runDistance(34, 999, 0.5);
		} else if(sumOfPositions == 0) {
			robot.drivetrain.runDistance(38, 999, 0.5);
		} else {
			robot.drivetrain.runDistance(42, 999, 0.5);
		}

		robot.drivetrain.turnDegrees(270, true, 0.4);

		robot.driveUntilDistance(0.2, 6);
		robot.drivetrain.runDistance(2, 2);

		robot.grabber.grabStone();
		sleep(1500);
		robot.grabber.raiseStone();
		sleep(1000);

		robot.drivetrain.runDistance(-5.5, -5.5, 999);

		if(sumOfPositions == 1) {
			moveTowardsBuildingZone(34, 999, 0.5);
		} else if(sumOfPositions == 0) {
			moveTowardsBuildingZone(39, 999, 0.5);
		} else {
			moveTowardsBuildingZone(43, 999, 0.5);
		}

		robot.grabber.dropBase();

		sleep(500);

		robot.grabber.dropAlt();

		sleep(500);

		robot.grabber.home();

		moveTowardsLoadingZone(12, 999, 0.5);
 */

	}

}
