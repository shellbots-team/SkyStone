package org.firstinspires.ftc.teamcode.Autonomous;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Robot.Grabber;

import java.util.Objects;

/**
 * Created by shell on 09/24/2019.
 */
@Disabled
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

// Wait until play is pushed
		waitForStart();

// Make sure Foundation servos are up
		robot.releaseFoundation();

		robot.grabber.raise();
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
				moveTowardsBuildingZone(6.5, 999);
			} else if(sumOfPositions == 0) {
				moveTowardsBuildingZone(2, 999);
			} else {
				moveTowardsLoadingZone(2.5, 999);
			}

		}

		robot.drivetrain.runDistance(11, 11);

		robot.grabber.grabStone();
		sleep(1500);
		robot.grabber.raiseStone();
		sleep(1000);

		if(getColor() == Color.RED) {
			robot.drivetrain.runDistance(-7, -7);
		} else {
			robot.drivetrain.runDistance(-4.5, -4.5);
		}

		if(sumOfPositions == 1) {
			moveTowardsBuildingZone(21, 999);
		} else if (sumOfPositions == 0) {
			moveTowardsBuildingZone(26, 999);
		} else {
			moveTowardsBuildingZone(31, 999);
		}

		robot.grabber.dropBase();

		sleep(500);

		robot.grabber.dropAlt();

		sleep(500);

		moveTowardsLoadingZone(12, 999);

		robot.drivetrain.runDistance(2, 2);
	}

}
