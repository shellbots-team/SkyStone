package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Created by shell on 09/10/2019.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(group = "Manual", name = "Manual Mode")
public class TeleOp extends OpMode {

	private Robot robot = new Robot();
	private Logger logger = null;

	private double speed = 1.0;

	/**
	 * Run once after INIT is pushed
	 */
	@Override
	public void init() {
		robot.init(hardwareMap, telemetry, this);
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

		/* Controller Layouts
		 *
		 * Controller 1 - "Body Controller"
		 *      Left Trigger     - Set full speed
		 *      Right Trigger    - Set half speed
		 *
		 *      Dpad Up          - Release baseplate / Move lower servos up
		 *      Dpad Down        - Grab baseplate / Move lower servos down
		 *
		 *      Left Joystick Y  - Move the robot forward/backward
		 *      Left Joystick X  - Move the robot left/right
		 *
		 *      Right Joystick X - Turn the robot
		 *
		 *      A + Y = Single joystick drive
		 *      X + B = Two joystick drive
		 *
		 * Controller 2 - "Arm Controller"
		 *      Right Trigger    - Extends arm
		 *      Left Trigger     - Detracts arm
		 *
		 *      Dpad Up          - Raise the arm
		 *      Dpad Down        - Lower the arm
		 *
		 *      Y Button         - Grab blocks with the hand
		 *      A Button         - Release blocks with the hand
		 */

		/*
		 * Controller 1 settings
		 */

		singleJoystickDrive();

		if (this.gamepad1.right_trigger > 0.5) {
			speed = 1.0;
		} else if (this.gamepad1.left_trigger > 0.5) {
			speed = 0.5;
		}

		if (this.gamepad1.right_bumper) {
		}
		if (this.gamepad1.left_bumper) {
		}

		if (this.gamepad1.dpad_up) {
			robot.releaseBaseplate();
		} else if (this.gamepad1.dpad_down) {
			robot.grabBaseplate();
		} else if (this.gamepad1.dpad_left) {
		} else if (this.gamepad1.dpad_right) {
		}

		if (this.gamepad1.y) {
		}
		if (this.gamepad1.a) {
		}
		if (this.gamepad1.x) {
		}
		if (this.gamepad1.b) {
		}

		/*
		 * Controller 2 settings
		 */

		if (this.gamepad2.right_trigger > 0.5) {
			robot.arm.extendWithPower(0.3);
		} else if (this.gamepad2.left_trigger > 0.5) {
			robot.arm.extendWithPower(-0.3);
		} else {
			robot.arm.extendWithPower(0);
		}

		if (this.gamepad2.right_bumper) {
			// down
		}
		if (this.gamepad2.left_bumper) {
			// up
		}

		if (this.gamepad2.dpad_up) {
			if(robot.arm.isGrabbing()) {
				robot.arm.raiseWithPower(0.35);
			} else {
				robot.arm.raiseWithPower(0.25);
			}
		} else if (this.gamepad2.dpad_down) {
			robot.arm.lowerWithPower(0.15);
		} else {
			robot.arm.raiseWithPower(0);
		}

		if (this.gamepad2.dpad_left) {
		}
		if (this.gamepad2.dpad_right) {
		}

		if (this.gamepad2.y) {
			robot.arm.grabHand();
		} else if (this.gamepad2.a) {
			robot.arm.releaseHand();
		}
		if (this.gamepad2.x) {
		}
		if (this.gamepad2.b) {
		}


		robot.arm.logTeleOpData();
		logger.numberLog("Speed", speed);
		logger.update();
	}

	/**
	 * Runxs once after STOP is pushed
	 */
	@Override
	public void stop() {
		robot.stopAllMotors();
		logger.completeLog("Status", "Stopped");
		logger.update();
	}

	private void singleJoystickDrive() {
		// New robot powering math...
		double leftX = this.gamepad1.left_stick_x;
		double leftY = this.gamepad1.left_stick_y;
		double rightX = this.gamepad1.right_stick_x;
		double rightY = this.gamepad1.right_stick_y;

		double[] motorPowers = new double[4];
		motorPowers[0] = (-leftY+leftX+rightX) * speed;
		motorPowers[1] = (-leftY-leftX-rightX) * speed;
		motorPowers[2] = (-leftY-leftX+rightX) * speed;
		motorPowers[3] = (-leftY+leftX-rightX) * speed;

		for(int i = 0; i < motorPowers.length; i++) {
			if(motorPowers[i] < 0.05 && motorPowers[i] > -0.05) { motorPowers[i] = 0.0; }
			if(motorPowers[i] > 1.0) { motorPowers[i] = 1.0; }
			if(motorPowers[i] < -1.0) { motorPowers[i] = -1.0; }
		}

		robot.drivetrain.setIndividualPowers(motorPowers);
	}

}
