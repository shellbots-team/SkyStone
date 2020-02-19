package org.firstinspires.ftc.teamcode.Autonomous;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Robot.Grabber;

/**
 * Created by shell on 09/24/2019.
 */
@Disabled
public abstract class BasicSkyStone extends BaseAutonomous {

	boolean[] xInputs;
	boolean[] aInputs;
	boolean[] yInputs;
	boolean[] bInputs;

	public abstract boolean[] getXInputs();
	public abstract boolean[] getAInputs();
	public abstract boolean[] getYInputs();
	public abstract boolean[] getBInputs();

	public abstract void noDevices(byte skystoneLocation);

	public abstract int getColor();

	@Override
	public void initializerz() {
		xInputs = getXInputs();
		aInputs = getAInputs();
		yInputs = getYInputs();
		bInputs = getBInputs();
	}

	@Override
	public void prerun() {

		double[] leftXValues  = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		double[] leftYValues  = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.123904884, -0.20734254, -0.20734254, -0.20734254, -0.20734254, -0.20734254, -0.21568629, -0.21568629, -0.21568629, -0.22403005, -0.24071757, -0.24906135, -0.2574051, -0.2574051, -0.36587402, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -0.84981227, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.22403005, 0.8915311, 0.6912808, 0.02377973, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		double[] rightXValues = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		boolean[] xInputs  = new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
		boolean[] aInputs  = new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
		boolean[] yInputs  = new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
		boolean[] bInputs  = new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
		double[] time         = new double[] {115.05855299999999, 28.310888, 24.71792, 24.31318, 24.0562, 25.704169999999998, 24.045679, 23.423961, 23.60995, 21.075367, 24.027918999999997, 24.198232, 24.401356999999997, 27.146409, 23.300627000000002, 24.10344, 23.922451000000002, 114.614959, 39.399795000000005, 35.62391, 22.876669, 22.687346, 22.429585, 24.217502, 35.676097, 24.294794000000003, 25.908179999999998, 41.683806, 35.071253, 35.535733, 42.490369, 59.991568, 68.16683, 39.70141, 23.144533, 23.861513000000002, 23.017971000000003, 23.548856, 21.349065, 21.809169, 25.642034000000002, 23.873388, 24.566252, 21.447137, 27.791773, 22.770992, 23.304429000000003, 37.66292, 49.407088, 75.676206, 43.948286, 21.594117, 22.656876999999998, 25.222659, 24.455054, 26.444377000000003, 35.353075999999994, 34.416045, 38.494327, 40.257504, 21.964326, 23.968023000000002, 32.345054999999995, 48.500474000000004, 23.951721, 44.539483, 22.553283, 22.248335, 25.557085999999998, 21.644221, 23.669326, 22.782658, 22.610314, 22.622867000000003, 23.146461, 21.376825, 24.329794, 31.933389, 45.379484, 24.481357, 32.080003000000005, 48.378807, 26.847816, 48.654901, 20.808857, 21.407971, 23.079013, 26.487451, 24.508232, 24.372294, 26.018648, 24.200836, 22.293179, 24.148544, 21.870627, 41.04417, 34.507035, 39.227921, 38.720525, 37.746201, 45.620578, 24.062711, 21.860471};

		ElapsedTime timer = new ElapsedTime();

		int i = 1;
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

			if (xInputs[i] && xInputs[i] != xInputs[i-1]) { // Left Big Arm
				robot.grabber.flip(Grabber.Level.BASE, Grabber.Side.LEFT);
			}
			if (aInputs[i] && aInputs[i] != aInputs[i-1]) { // Left Little Arm
				robot.grabber.flip(Grabber.Level.ALT, Grabber.Side.LEFT);
			}
			if (yInputs[i] && yInputs[i] != yInputs[i-1]) { // Right Big Arm
				robot.grabber.flip(Grabber.Level.BASE, Grabber.Side.RIGHT);
			}
			if (bInputs[i] && bInputs[i] != bInputs[i-1]) { // Right Little Arm
				robot.grabber.flip(Grabber.Level.ALT, Grabber.Side.RIGHT);
			}

			while(timer.milliseconds() < time[i]) {}
			i++;
		}

		//TODO: Add skystone locating and store to skystoneLocation (-1, 0, 1) make sure to adjust for Red/Blue side
		robot.objectDetection.initializeTFOD();
		robot.objectDetection.initializeObjectDetection();
		sleep(2000);
		double skystoneRight = robot.objectDetection.getSkyStone().getRight();

		byte position = 0;

		if (skystoneRight > 700) {
			position = 1;
		} else if (skystoneRight < 480) {
			position = -1;
		}
		if (skystoneRight > 750) {
			position = 1;
		} else if (skystoneRight < 500) {
			position = -1;
		}
		if(skystoneRight == 500) { position = 1; }

// Invert position if on blue side
		if(getColor() == Color.BLUE) { position *= -1; }

		noDevices(position);

	}

	@Override
	public void runOpMode() {
		super.runOpMode();
	}

	@Override
	public void fakeAutonomous(int i) {

		if (xInputs[i] && xInputs[i] != xInputs[i-1]) { // Left Big Arm
			robot.grabber.flip(Grabber.Level.BASE, Grabber.Side.LEFT);
		}
		if (aInputs[i] && aInputs[i] != aInputs[i-1]) { // Left Little Arm
			robot.grabber.flip(Grabber.Level.ALT, Grabber.Side.LEFT);
		}
		if (yInputs[i] && yInputs[i] != yInputs[i-1]) { // Right Big Arm
			robot.grabber.flip(Grabber.Level.BASE, Grabber.Side.RIGHT);
		}
		if (bInputs[i] && bInputs[i] != bInputs[i-1]) { // Right Little Arm
			robot.grabber.flip(Grabber.Level.ALT, Grabber.Side.RIGHT);
		}

	}

}
