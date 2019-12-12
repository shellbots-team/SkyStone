package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.Objects;

/**
 * Created by shell on 09/24/2019.
 */
@Disabled
public abstract class BasicSkyStoneGrabber extends BaseAutonomous {

	@Override
	void moveTowardsLoadingZone(long milliseconds, double speed) {
		super.moveTowardsBuildingZone(milliseconds, speed);
	}

	void moveTowardsLoadingZone(double distance, double speed, long maxSeconds) {
		robot.drivetrain.runDistance(distance, -distance, -distance, distance, speed, maxSeconds);
	}

	@Override
	void moveTowardsBuildingZone(long milliseconds, double speed) {
		super.moveTowardsLoadingZone(milliseconds, speed);
	}

	void moveTowardsBuildingZone(double distance, double speed, long maxSeconds) {
		robot.drivetrain.runDistance(-distance, distance, distance, -distance, speed, maxSeconds);
	}

	void grabStone() {
		if(getColor() == Color.BLUE) {
			robot.grabber.rightGrab();
		} else {
			robot.grabber.leftGrab();
		}
	}

	@Override
	public void runOpMode() {

		super.runOpMode();

		robot.objectDetection.initializeObjectDetection();

		waitForStart();

		robot.releaseBaseplate();

		// Drive forward so objects are within distance
		robot.drivetrain.runDistance(-6, -6, 1.0, 2);

		// Start object detection
		robot.objectDetection.initializeTFOD();

		sleep(1500);

		// Get the value of the skystone
		Recognition stone = robot.objectDetection.getSkyStone();
		logger.completeLog("Right Side", String.valueOf(stone.getRight()));

		// Set the stones position
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

		long distanceToBaseplate;

		if(position == SKYSTONE_SIDE) {
			moveTowardsLoadingZone(4, 1.0, 999);
			distanceToBaseplate = 24;
		} else if(position == MIDDLE_SIDE) {
			moveTowardsBuildingZone(2, 1.0, 999);
			distanceToBaseplate = 22;
		} else {
			moveTowardsBuildingZone(5.3, 1.0, 999);
			distanceToBaseplate = 20;
		}

		robot.drivetrain.runDistance(-6.5, -6.5);

		grabStone();

		sleep(500);

		robot.drivetrain.runDistance(3.2, 3.2);

		moveTowardsBuildingZone(distanceToBaseplate, 1.0, 999);

		robot.grabber.raise();

		sleep(500);
		// TODO : turn

		distanceToBaseplate += 1000;
		/*
		moveTowardsLoadingZone(distanceToBaseplate, 1.0);

		robot.drivetrain.runDistance(-2, -2);

		if(position == SKYSTONE_SIDE) {
			grabSkystoneSide();
		} else {
			grabBaseplateSide();
		}

		sleep(500);

		robot.drivetrain.runDistance(2, 2);

		moveTowardsBuildingZone(distanceToBaseplate, 1.0);

		robot.grabber.raise();

		moveTowardsLoadingZone(1000, 1.0);
		 */

/*
		// Grab the skystone
		robot.arm.grabHand();

		sleep(800);

		robot.arm.raiseArm(true);

		// Lift the skystone up
		robot.arm.maintainPosition();

		// Move away from stones
		robot.drivetrain.runDistance(1.4, -1.4, -1.4, 1.4, 1, 0.5);

		boolean clockwise = true;
		if(getColor() == Color.BLUE) { clockwise = false; }

		// Turn to look at baseplate
		if(position == SKYSTONE_SIDE) {
			robot.drivetrain.turnDegrees(98.6,clockwise, 1.0);
		} else {
			robot.drivetrain.turnDegrees(98.6,clockwise, 1.0);
		}

		// Lower the arm to go under the bridge
		robot.arm.lowerArm(true);

		// Figure out the position to the baseplate side
		double distanceToBaseplateSide = 0;

		if(position == SKYSTONE_SIDE) {
			distanceToBaseplateSide = 24;
		} else if(position == MIDDLE_SIDE) {
			distanceToBaseplateSide = 21;
		} else if(position == BASEPLATE_SIDE) {
			distanceToBaseplateSide = 18;
		}

		// Run to the baseplate
		robot.drivetrain.runDistance(-distanceToBaseplateSide, distanceToBaseplateSide, distanceToBaseplateSide, -distanceToBaseplateSide, 1.0, 3.5);

		// Drop the stone
		robot.grabber.raise();

		sleep(250);

		if(position == BASEPLATE_SIDE) {
			distanceToBaseplateSide += 10.24;
		} else {
			distanceToBaseplateSide += 10;
		}

		double distanceToMidline;

		if(position == 0) {
			distanceToMidline = 6.78;
		} else {
			distanceToMidline = 7.4;
		}

		robot.drivetrain.runDistance(distanceToMidline, -distanceToMidline, -distanceToMidline, distanceToMidline, 1.0, 2);

		moveTowardsLoadingZone(550, 0.5);

		moveTowardsBuildingZone(50, 0.5);

		distanceToBaseplateSide -= distanceToMidline;

		// Moving back to the other stone
		robot.drivetrain.runDistance(distanceToBaseplateSide, -distanceToBaseplateSide, -distanceToBaseplateSide, distanceToBaseplateSide, 1.0, 4);

		distanceToBaseplateSide += distanceToMidline;


		// Drive away from the stones
		robot.drivetrain.runDistance(-3, -3, 1.0, 0.6);

		// Drive to the baseplate side
		robot.drivetrain.runDistance(-distanceToBaseplateSide, distanceToBaseplateSide, -distanceToBaseplateSide, -distanceToBaseplateSide, 1.0, 6);

		robot.grabber.raise();

		// Move back to the line
		robot.drivetrain.runDistance(-5.5, 5.5, 5.5, -5.5, 1, 2);
*/
	}
}
