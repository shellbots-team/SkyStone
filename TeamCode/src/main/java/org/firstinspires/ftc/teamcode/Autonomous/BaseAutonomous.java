package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Logger;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Created by shell on 10/05/2019.
 */

@Disabled
@Autonomous(group = "Base", name = "Base: Autonomous")
public abstract class BaseAutonomous extends LinearOpMode {

	protected abstract Color getColor();

	protected abstract Placement getFinalPlacement();

	Robot robot = new Robot();
	Logger logger = null;
	int step = 0;

	protected enum Placement {
		WALL,
		CENTER
	}

	protected enum Color {
		RED,
		BLUE
	}

	void moveTowardsLoadingZone(double distance, double maxSeconds) {
		moveTowardsLoadingZone(distance, maxSeconds, robot.drivetrain.defaultSpeed);
	}

	void moveTowardsLoadingZone(double distance, double maxSeconds, double speed) {
		distance *= 2.25;
		if(getColor() == Color.RED) {
			robot.drivetrain.runDistance(-distance, distance, distance, -distance, maxSeconds, speed);
		} else {
			robot.drivetrain.runDistance(distance, -distance, -distance, distance, maxSeconds, speed);
		}
	}

	void moveTowardsBuildingZone(double distance, double maxSeconds) {
		moveTowardsBuildingZone(distance, maxSeconds, robot.drivetrain.defaultSpeed);
	}

	void moveTowardsBuildingZone(double distance, double maxSeconds, double speed) {
		distance *= 2.25;
		if(getColor() == Color.RED) {
			robot.drivetrain.runDistance(distance, -distance, -distance, distance, maxSeconds, speed);
		} else {
			robot.drivetrain.runDistance(-distance, distance, distance, -distance, maxSeconds, speed);
		}
	}

	@Override
	public void runOpMode() {
		// Initialize motors/servos
		robot.init(hardwareMap, telemetry, this);
		logger = new Logger(telemetry);
	}
}