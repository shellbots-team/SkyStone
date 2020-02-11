package org.firstinspires.ftc.teamcode.Outreach;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Logger;
import org.firstinspires.ftc.teamcode.Robot.BigRobot;


/**
 * Created by shell on 09/10/2019.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(group = "Outreach", name = "Big Robot Manual Mode")
public class bigRobotTeleOp extends OpMode {

	private BigRobot bigRobot = new BigRobot();
	private Logger logger = null;
	private String elevatorLimit = "";
	private int switchCount = 0;

	private double speed = 1.0;

	/**
	 * Run once after INIT is pushed
	 */
	@Override
	public void init() {
		bigRobot.init(hardwareMap, telemetry, this);
		logger = new Logger(telemetry);

		// Step 0 - Initialized
		logger.statusLog(0, "Initialized");

	}

	/**
	 * Runs constantly after INIT is pushed but before PLAY is pushed
	 */
	@Override
	public void init_loop() {

	}

	/**
	 * Runs once after PLAY is pushed
	 */
	@Override
	public void start() {
		// Step 1 - Playing
		logger.statusLog(0, "Playing");
	}

	@Override
	public void loop() {

		singleJoystickDrive();

		if (this.gamepad1.right_trigger > 0.5) {
			if(speed == 0.5) { switchCount += 1; }
			speed = 1.0;
		} else if (this.gamepad1.left_trigger > 0.5) {
			if(speed == 1.0) { switchCount += 1; }
			speed = 0.5;
		}

		logger.numberLog("Imu", bigRobot.getAngle());
		logger.numberLog("Speed", speed);
		logger.completeLog("Elevator Limit?", elevatorLimit);
		logger.numberLog("Switches full-half speed", switchCount);

		bigRobot.logTeleOpData();
		logger.update();

	}

	/**
	 * Runs once after STOP is pushed
	 */
	@Override
	public void stop() {
		//robot.stopAllMotors();
		logger.completeLog("Status", "Stopped");
		logger.update();
	}

	private void singleJoystickDrive() {
		// New robot powering math...
		double leftX = this.gamepad1.left_stick_x;
		double leftY = this.gamepad1.left_stick_y;
		double rightX = -this.gamepad1.right_stick_x;
		double rightY = this.gamepad1.right_stick_y;

		//double currAngle = robot.imu.getAngularOrientation().firstAngle;
		//double forwardBackwardChange = Math.abs(currAngle % 180) / 180;
		//double sideToSideChange = (currAngle % 90) / 90;

		//leftY -= forwardBackwardChange;
		//leftX *= sideToSideChange;

		//logger.numberLog("Angle", currAngle);
		//logger.numberLog("FBChange", forwardBackwardChange);
		//logger.numberLog("SSChange", sideToSideChange);
		//logger.numberLog("LeftX", leftX);
		//logger.numberLog("LeftY", leftY);

		//double imu_radians = robot.imu.getAngularOrientation().firstAngle * Math.PI/180;
		//double temp = leftY * Math.cos(imu_radians) - leftX * Math.sin(imu_radians);
		//leftX =       leftY * Math.sin(imu_radians) + leftX * Math.cos(imu_radians);
		//leftY = temp;

		//logger.numberLog("angle", imu_radians);

		//rightX *= 0.4;

		double[] motorPowers = new double[4];
		motorPowers[0] = 0;//(leftY-leftX+rightX);// -+
		motorPowers[1] = 0;//(leftY+leftX-rightX);// +-
		motorPowers[2] = (leftY+leftX+rightX);// ++
		motorPowers[3] = (leftY-leftX-rightX);// --
/*
		double max = Math.abs(getLargestAbsVal(motorPowers));
		if(max < 1) { max = 1; }

		for(int i = 0; i < motorPowers.length; i++) {
			motorPowers[i] /= max;
			motorPowers[i] *= speed;
		}
*/
		for(int i = 0; i < motorPowers.length; i++) {
			if(motorPowers[i] < 0.05 && motorPowers[i] > -0.05) { motorPowers[i] = 0.0; }
			if(motorPowers[i] > 1.0) { motorPowers[i] = 1.0; }
			if(motorPowers[i] < -1.0) { motorPowers[i] = -1.0; }
			//motorPowers[i] *= speed;
			logger.numberLog("Motor" + i, motorPowers[i]);
		}

		bigRobot.drivetrain.setIndividualPowers(motorPowers);
	}

	private double getLargestAbsVal(double[] values) {
		double max = 0;
		for(double val : values) {
			if(Math.abs(val) > Math.abs(max)) { max = val; }
		}
		return max;
	}

}
