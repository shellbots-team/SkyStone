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

	void moveTowardsLoadingZone(double distance, double speed, double maxSeconds) {
		robot.drivetrain.runDistance(distance, -distance, -distance, distance, speed, maxSeconds);
	}

	@Override
	void moveTowardsBuildingZone(long milliseconds, double speed) {
		super.moveTowardsLoadingZone(milliseconds, speed);
	}

	void moveTowardsBuildingZone(double distance, double speed, double maxSeconds) {
		robot.drivetrain.runDistance(-distance, distance, distance, -distance, speed, maxSeconds);
	}

	void grabStone() {
		if (getColor() == Color.BLUE) {
			robot.grabber.rightGrab();
		} else {
			robot.grabber.leftGrab();
		}
	}

	@Override
	public void runOpMode() {

		super.runOpMode();

// Setup object detection
		robot.objectDetection.initializeObjectDetection();

// Wait until play is pushed
		waitForStart();

// Make sure baseplate servos are up
		robot.releaseBaseplate();

		robot.grabber.raise();

// Drive forward so blocks are within distance
		robot.drivetrain.runDistance(-6, -6, 1.0, 2);

// Start detecting objects
		robot.objectDetection.initializeTFOD();

// Allow time for object detection to find the blocks
		sleep(1500);

// Get the value of the skystone
		Recognition stone = robot.objectDetection.getSkyStone();
		logger.completeLog("Right Side", String.valueOf(stone.getRight()));

// Set the stones position
		byte position = 0;
		final byte BASEPLATE_SIDE = 1;
		final byte MIDDLE_SIDE = 0;
		final byte SKYSTONE_SIDE = -1;

		if (stone.getRight() < 300) {
			position = -1;
		} else if (stone.getRight() > 600) {
			position = 1;
		}

// Invert position if on blue side
		if (getColor() == Color.BLUE) { position *= -1; }

// Log the final position
		logger.completeLog("Postion", String.valueOf(position));

		long distanceToBaseplate;

// Move to be aligned with skystone
// Setup distace to baseplate accordingly
		if (position == SKYSTONE_SIDE) {
			moveTowardsLoadingZone(4, 1.0, 999);
			distanceToBaseplate = 25;
		} else if (position == MIDDLE_SIDE) {
			moveTowardsBuildingZone(2, 1.0, 999);
			distanceToBaseplate = 23;
		} else {
			moveTowardsBuildingZone(6.08, 1.0, 999);
			distanceToBaseplate = 21;
		}

// Move to be right next to the stone
		robot.drivetrain.runDistance(-7.5, -7.5, 0.55, 2);

// Grab onto the skystone
		grabStone();

// Allow time for the servo to move/grab it
		sleep(500);

// Pull the block away from other blocks
		robot.drivetrain.runDistance(2.44, 2.22);

// Drive to the baseplate side
		moveTowardsBuildingZone(distanceToBaseplate, 1.0, 999);

// Release the block
		robot.grabber.raise();

// Allow time for the block to be released
		sleep(500);

		double distanceToMidline = 12;

// Run to be aligned on the colored line
		moveTowardsLoadingZone(distanceToMidline, 1.0, 3);

// Drive into the bridge leg to re-orientate itself
		robot.drivetrain.runDistance(-4, -4, 0.5, 1);

// Wait to reduce momentum
		sleep(500);

// Drive slightly away from the bridge leg
		robot.drivetrain.runDistance(2, 2, 0.5, 1);

		distanceToBaseplate -= 1.25;

// Drive to the second skystone
		moveTowardsLoadingZone(distanceToBaseplate, 1.0, 3);

// Drive to be next to the block
		robot.drivetrain.runDistance(-2, -2);

// Grab the skystone
		grabStone();

// Allow time for the servo to move
		sleep(500);

// Pull the block away from stones
		robot.drivetrain.runDistance(3.0, 2.5);

// Account for the distance driven to midline
		distanceToBaseplate += 9;

// Drive back to the baseplate side
		moveTowardsBuildingZone(distanceToBaseplate, 1.0, 4);

// Release the skystone
		robot.grabber.raise();

// Allow time for the block to be relased
		sleep(500);

// Drive back onto the midline
		moveTowardsLoadingZone(9, 1.0, 2);
	}
}
