package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.Objects;

/**
 * Created by shell on 09/24/2019.
 */
@Disabled
public abstract class BasicSkyStoneGrabber extends BaseAutonomous {

	final boolean DOUBLE_STONE = true;

	@Override
	void moveTowardsLoadingZone(long milliseconds, double speed) {
		super.moveTowardsBuildingZone(milliseconds, speed);
	}

	void moveTowardsLoadingZone(double distance, double speed, double maxSeconds) {
		if(getColor() == Color.RED) {
			robot.drivetrain.runDistance(distance, -distance, -distance, distance, speed, maxSeconds);
		} else {
			robot.drivetrain.runDistance(-distance, distance, distance, -distance, speed, maxSeconds);
		}
	}

	@Override
	void moveTowardsBuildingZone(long milliseconds, double speed) {
		super.moveTowardsLoadingZone(milliseconds, speed);
	}

	void moveTowardsBuildingZone(double distance, double speed, double maxSeconds) {
		if(getColor() == Color.RED) {
			robot.drivetrain.runDistance(-distance, distance, distance, -distance, speed, maxSeconds);
		} else {
			robot.drivetrain.runDistance(distance, -distance, -distance, distance, speed, maxSeconds);
		}
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
		robot.drivetrain.runDistance(-5.45, -5.45, 1.0, 2);

// Start detecting objects
		robot.objectDetection.initializeTFOD();

// Allow time for object detection to find the blocks
		sleep(2000);

// Get the value of the skystone
		Recognition stone = robot.objectDetection.getSkyStone();
		logger.completeLog("Right Side", String.valueOf(stone.getRight()));

// Set the stones position
		byte position = 0;
		final byte BASEPLATE_SIDE = 1;
		final byte MIDDLE_SIDE = 0;
		final byte SKYSTONE_SIDE = -1;

		if(getColor() == Color.RED) {
			if (stone.getRight() > 700) {
				position = 1;
			} else if (stone.getRight() < 420) {
				position = -1;
			}
		} else {
			if (stone.getRight() > 750) {
				position = 1;
			} else if (stone.getRight() < 500) {
				position = -1;
			}
			if(stone.getRight() == 500) { position = 1; }
		}

// Invert position if on blue side
		if (getColor() == Color.BLUE) { position *= -1; }

// Log the final position
		logger.completeLog("Postion", String.valueOf(position));

		double distanceToBaseplate;

// Move to be aligned with skystone
// Setup distace to baseplate accordingly
		if(getColor() == Color.RED) {
			if (position == SKYSTONE_SIDE) {
				moveTowardsLoadingZone(2.0, 1.0, 1);
				distanceToBaseplate = 27;
			} else if (position == MIDDLE_SIDE) {
				moveTowardsBuildingZone(2.2, 1.0, 1);
				distanceToBaseplate = 22;
			} else {
				moveTowardsBuildingZone(5.1, 1.0, 1);
				distanceToBaseplate = 20;
			}
		} else {
			if (position == SKYSTONE_SIDE) {
				moveTowardsLoadingZone(2.3, 1.0, 1);
				distanceToBaseplate = 26;
			} else if (position == MIDDLE_SIDE) {
				moveTowardsBuildingZone(1.8, 1.0, 2);
				distanceToBaseplate = 24;
			} else {
				moveTowardsBuildingZone(5.5, 1.0, 1);
				distanceToBaseplate = 19;
			}
		}

// Move to be right next to the stone
		if(getColor() == Color.RED) {
			robot.drivetrain.runDistance(-7.5, -7.5, 0.55, 2);
		} else {
			robot.drivetrain.runDistance(-7.6, -7.6, 0.55, 2);
		}

// Grab onto the skystone
		grabStone();

// Allow time for the servo to move/grab it
		if(getColor() == Color.RED) {
			sleep(500);
		} else {
			sleep(900);
		}

// Pull the block away from other blocks
			if(getColor() == Color.RED) {
				if(position == SKYSTONE_SIDE) {
					robot.drivetrain.runDistance(3.92, 3.92);
				}else if(position==MIDDLE_SIDE) {
					robot.drivetrain.runDistance(3.65, 3.65);
				} else {
					robot.drivetrain.runDistance(4.3, 4.3);
				}
			} else {
				if(position == BASEPLATE_SIDE) {
					robot.drivetrain.runDistance(4.1, 4.1, 5);
				} else if(position == MIDDLE_SIDE) {
					robot.drivetrain.runDistance(3.9, 3.9);
				} else {
					robot.drivetrain.runDistance(4, 4);
				}
			}


// Drive to the baseplate side
		moveTowardsBuildingZone(distanceToBaseplate, 1.0, 3);

// Release the block
		robot.grabber.raise();

// Allow time for the block to be released
		sleep(500);

		double distanceToMidline;

		if(getColor() == Color.RED) {
			if (position == BASEPLATE_SIDE) {
				distanceToMidline = 10.7;
			} else if (position == MIDDLE_SIDE) {
				distanceToMidline = 10.05;
			} else {
				distanceToMidline = 10.75;
			}
		} else {
			if (position == BASEPLATE_SIDE) {
				distanceToMidline = 9.6;
			} else if (position == MIDDLE_SIDE) {
				distanceToMidline = 9.6;
			} else {
				distanceToMidline = 8.7;
			}
		}

// Run to be aligned on the colored line
		moveTowardsLoadingZone(distanceToMidline, 1.0, 3);

// Drive into the bridge leg to re-orientate itself
		if(getColor() == Color.RED) {
			robot.drivetrain.runDistance(-4.4, -4.4, 0.5, 1);
		} else {
			robot.drivetrain.runDistance(-4.5, -4.5, 0.5, 1);
		}

// Wait to reduce momentum
		sleep(500);

		if(DOUBLE_STONE) {

// Drive slightly away from the bridge leg
			if(getColor() == Color.RED) {
				robot.drivetrain.runDistance(2, 2, 0.5, 1);
			} else {
				robot.drivetrain.runDistance(0.5, 0.5, 0.5, 1);
			}

			distanceToBaseplate -= 1;

			double change = 0.0;

			if (position == SKYSTONE_SIDE) {
				change = 0;
			} else if (position == MIDDLE_SIDE) {
				change = 3.25;
			} else {
				change = 6.6;
			}

// Drive to the second skystone
			moveTowardsLoadingZone(distanceToBaseplate + change, 1.0, 4);
			if(getColor() == Color.RED) {
				moveTowardsLoadingZone(2.25, 0.6, 1);
			} else {
				moveTowardsLoadingZone(3, 0.6, 1);
			}

			double distanceToSkyStone;

			if (position == SKYSTONE_SIDE) {
				distanceToSkyStone = 0;
			} else if (position == MIDDLE_SIDE) {
				distanceToSkyStone = 4.3;
			} else {
				distanceToSkyStone = 7.7;
			}

			moveTowardsLoadingZone(-distanceToSkyStone, 1.0, 2);

// Drive to be next to the block
			if(getColor() == Color.RED) {
				if (position == SKYSTONE_SIDE) {
				    robot.drivetrain.runDistance(-2.135, -2.135);
				} else if(position == MIDDLE_SIDE) {
					robot.drivetrain.runDistance(-2.35, -2.35);
				} else {
					robot.drivetrain.runDistance(-2.22, -2.22);
				}

			} else {
				robot.drivetrain.runDistance(-2.8, -2.8 );
			}

// Grab the skystone
			grabStone();

// Allow time for the servo to move
			sleep(500);

// Pull the block away from stones
			if(getColor() == Color.RED) {
				if (position == MIDDLE_SIDE) {
					robot.drivetrain.runDistance(2.55, 2.55);
				} else {
					robot.drivetrain.runDistance(2.455, 2.455);
				}
			} else {
				if (position == BASEPLATE_SIDE) {
					robot.drivetrain.runDistance(3.5, 3.5);
				} else if (position == MIDDLE_SIDE) {
					robot.drivetrain.runDistance(2.5, 2.5);
				} else {
					robot.drivetrain.runDistance(2.5, 2.5);
				}
			}

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

			robot.drivetrain.runDistance(-2, -2, 0.5, 1);
		}
	}
}
