package org.firstinspires.ftc.teamcode.Blue.Baseplate;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.BaseAutonomous;

/**
 * Created by shell on 10/25/2019.
 */

@Autonomous(group="Basic", name = "Blue: Baseplate Line Wall")
public class BlueBaseplateLineWall extends BaseAutonomous {

	@Override
	public void runOpMode() {
		super.runOpMode();

		waitForStart();

		robot.fullLogAndUpdate("Status", "Step 1 - Driving until on corner tape");
		robot.driveRight();
		int i = 0;
		while (!robot.isOnLine() && opModeIsActive()) {
			i++;
			if (i % 10 == 0) {
				robot.fullLogAndUpdate("Color", robot.colorSensor.red() + " : " + robot.colorSensor.green() + " : " + robot.colorSensor.blue());
			}
		}
		robot.setMotorPowers(0);

		robot.driveLeft();
		sleep(250);

		robot.runInchesWithEncoders(-2, -2);
	}
}

