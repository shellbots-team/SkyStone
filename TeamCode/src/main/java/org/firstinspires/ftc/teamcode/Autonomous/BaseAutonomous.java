package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Logger;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Created by shell on 02/11/2020.
 */
@Autonomous(group = "Auto", name = "Manual Based Autonomous")
public abstract class BaseAutonomous extends LinearOpMode {

	protected abstract double[]  getLeftXValues();
	protected abstract double[]  getLeftYValues();
	protected abstract double[]  getRightXValues();
	protected abstract double[]  getTime();

	protected abstract void fakeAutonomous(int i);

	protected Robot robot = new Robot();
	protected Logger logger = null;

	public abstract void initializerz();
	public abstract void prerun();

	@Override
	public void runOpMode() {
		logger = new Logger(telemetry);
		robot.init(hardwareMap, telemetry, this);

		initializerz();

		ElapsedTime timer = new ElapsedTime();

		waitForStart();

		prerun();

		double[] leftXValues = getLeftXValues();
		double[] leftYValues = getLeftYValues();
		double[] rightXValues = getRightXValues();
		double[] time = getTime();

		int i = 0;
		while (opModeIsActive() && i < leftXValues.length) {
			timer.reset();
			logger.numberLog("I", i);

			double leftX = leftXValues[i] * -1;
			double leftY = leftYValues[i] * -1;
			double rightX = rightXValues[i];

			double[] motorPowers = new double[4];
			double speed = 0.5;
			motorPowers[0] = (leftY - leftX + rightX);
			motorPowers[1] = (leftY + leftX - rightX);
			motorPowers[2] = (leftY + leftX + rightX);
			motorPowers[3] = (leftY - leftX - rightX);

			for (int j = 0; j < motorPowers.length; j++) {
				if (motorPowers[j] < 0.05 && motorPowers[j] > -0.05) {
					motorPowers[j] = 0.0;
				}
				if (motorPowers[j] > 1.0) {
					motorPowers[j] = 1.0;
				}
				if (motorPowers[j] < -1.0) {
					motorPowers[j] = -1.0;
				}

				motorPowers[j] *= speed;
				logger.numberLog("motorPower["+j+"]", motorPowers[j]);
			}

			robot.drivetrain.setIndividualPowers(motorPowers);

			fakeAutonomous(i);

			while(timer.milliseconds() < time[i]) {}
			i++;
		}
	}
}
