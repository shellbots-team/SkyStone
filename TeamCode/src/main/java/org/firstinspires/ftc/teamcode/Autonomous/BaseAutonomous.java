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

	void moveTowardsLoadingZone(long milliseconds, double speed) {
		if (getColor() == Color.BLUE) {
			robot.drivetrain.setPowerRight(speed);
		} else if (getColor() == Color.RED) {
			robot.drivetrain.setPowerLeft(speed);
		} else {
			logger.completeLog("Color", "Color not found");
		}
		if (milliseconds == 0) {
			return;
		}
		sleep(milliseconds);
		robot.drivetrain.setAllPowers(0);
	}

	void moveTowardsBuildingZone(long milliseconds, double speed) {
		if (getColor() == Color.BLUE) {
			robot.drivetrain.setPowerLeft(speed);
		}
		if (getColor() == Color.RED) {
			robot.drivetrain.setPowerRight(speed);
		}
		if (milliseconds == 0) {
			return;
		}
		sleep(milliseconds);
		robot.drivetrain.setAllPowers(0);
	}

	@Override
	public void runOpMode() {
		// Initialize motors/servos
		robot.init(hardwareMap, telemetry, this);
		logger = new Logger(telemetry);
	}
}