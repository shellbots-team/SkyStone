package org.firstinspires.ftc.teamcode.Autonomous;

import android.util.Log;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.Robot.Grabber;

/**
 * Created by shell on 09/24/2019.
 */

public abstract class BasicSkyStone extends BaseAutonomous {

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

		robot.drivetrain.defaultSpeed = 0.4;

		// Step 0 - Ready to run
		logger.statusLog(step++, "Ready To Run");

// Wait until play is pushed
		waitForStart();

		try {

// Make sure Foundation servos are up
			robot.releaseFoundation();

			robot.grabber.home();
			robot.grabber.prepareForStone();

// Drive forward so blocks are within distance
			if(getColor() == Color.RED) {
				robot.driveUntilDistance(0.4, 15);
			} else {
				robot.drivetrain.runDistance(20, 20, 999);
			}

			if(getColor() == Color.RED) { sleep(500); }

			moveTowardsBuildingZone(1, 999);

			int sumOfPositions = 0;
			int iterationCount = 1;

// Start detecting objects
			for (int i = 0; i < iterationCount; i++) {
				if (!opModeIsActive()) {
					break;
				}
				robot.objectDetection.initializeTFOD();
				sleep(1250);
				Recognition stone = robot.objectDetection.getSkyStone();
				boolean offscreen = stone.getTop() == 12345f;
				float stoneX = (stone.getLeft() + stone.getRight()) / 2;
				logger.completeLog("I:" + i + " Stone X", String.valueOf(stoneX));
				if (getColor() == Color.RED) {
					if (offscreen || stoneX < 100) {
						sumOfPositions--;
					} else if (stoneX > 400) {
						sumOfPositions++;
					}
				} else {
					if (offscreen || stoneX > 600) {
						sumOfPositions--;
					} else if (stoneX < 400) {
						sumOfPositions++;
					}
				}
			}

			robot.objectDetection.stopTFOD();

			logger.numberLog("Initial sum", sumOfPositions);
			sumOfPositions /= iterationCount;
			sumOfPositions = Math.round(sumOfPositions);
			logger.numberLog("Final sum", sumOfPositions);
			logger.update();

			// TODO: Wait time in between movement to avoid large changes based on momentum

			// Moving to be positioned in front of stone #1
			if (getColor() == Color.RED) {
				if (sumOfPositions == 1) {
					moveTowardsBuildingZone(6.75, 999);
				} else if (sumOfPositions == 0) {
					moveTowardsBuildingZone(3, 999);
				} else {
					moveTowardsLoadingZone(1.5, 999);
				}
			} else {
				if (sumOfPositions == 1) {
					moveTowardsBuildingZone(6.5, 999);
				} else if (sumOfPositions == 0) {
					moveTowardsBuildingZone(2, 999);
				} else {
					moveTowardsLoadingZone(2, 999);
				}

			}

			sleep(500);

			robot.drivetrain.runDistance(11.5, 11.5, 999, 0.2);

			robot.grabber.grabStone();
			sleep(1500);
			robot.grabber.raiseStone();
			sleep(1000);

			if (getColor() == Color.RED) {
				robot.drivetrain.runDistance(-6.9, -6.9);
			} else {
				robot.drivetrain.runDistance(-4.5, -4.5);
			}

			sleep(500);

			// Bring stone #1 to baseplate side
			if (sumOfPositions == 1) {
				moveTowardsBuildingZone(20, 999, 0.5);
			} else if (sumOfPositions == 0) {
				moveTowardsBuildingZone(25, 999, 0.5);
			} else {
				moveTowardsBuildingZone(30, 999, 0.5);
			}

			robot.drivetrain.runDistance(8, 8);

			robot.grabber.dropBase();

			sleep(500);

			robot.grabber.dropAlt();

			sleep(500);

			robot.grabber.prepareForStone();

			robot.drivetrain.runDistance(-7, -7);

			moveTowardsLoadingZone(20, 999, 0.5);

			robot.drivetrain.runDistance(-4, -4);

			// Moving to be in front of stone #2
			if(getColor() == Color.RED) {
				if (sumOfPositions == 1) {
					moveTowardsLoadingZone(12, 999, 0.5);
				} else if (sumOfPositions == 0) {
					moveTowardsLoadingZone(18, 999, 0.5);
				} else {
					moveTowardsLoadingZone(22, 999, 0.5);
				}
			} else {
				if (sumOfPositions == 1) {
					moveTowardsLoadingZone(13, 999, 0.5);
				} else if (sumOfPositions == 0) {
					moveTowardsLoadingZone(18, 999, 0.5);
				} else {
					moveTowardsLoadingZone(22, 999, 0.5);
				}
			}

			robot.driveUntilDistance(0.2, 4);
			robot.drivetrain.runDistance(2, 2);

			robot.grabber.grabStone();
			sleep(1500);
			robot.grabber.raiseStone();
			sleep(1000);

			if(getColor() == Color.RED) {
				robot.drivetrain.runDistance(-5, -5, 999);
			} else {
				robot.drivetrain.runDistance(-5.5, -5.5, 999);
			}

			// Bring stone #2 to baseplate side
			if (sumOfPositions == 1) {
				moveTowardsBuildingZone(34, 999, 0.5);
			} else if (sumOfPositions == 0) {
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

		}
		catch (RuntimeException e) {
			Log.e("14736:RuntimeException", e.getMessage());
			robot.drivetrain.runDistance(12, 12);
		}
		finally {
			// Step 16 - Finished
			logger.statusLog(step++, "Finished");
			robot.stopAllMotors();
		}
	}

}
