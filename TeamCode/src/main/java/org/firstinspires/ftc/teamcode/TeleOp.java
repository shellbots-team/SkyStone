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
	private String elevatorLimit = "";
	private int switchCount = 0;

	private double speed = 1.0;
	private double armSpeed = 1.0;

	private boolean manualOverride = false;
	private boolean grabbersAreFront = true;

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
			if(speed == 0.5) { switchCount += 1; }
			speed = 1.0;
		} else if (this.gamepad1.left_trigger > 0.5) {
			if(speed == 1.0) { switchCount += 1; }
			speed = 0.5;
		}

		if (this.gamepad1.start && this.gamepad1.right_bumper) {
			grabbersAreFront = true;
		}
		if (this.gamepad1.start && this.gamepad1.left_bumper) {
			grabbersAreFront = false;
		}

		if (this.gamepad1.dpad_up) {
			robot.releaseBaseplate();
		} else if (this.gamepad1.dpad_down) {
			robot.grabBaseplate();
		} else if (this.gamepad1.dpad_left) {
		} else if (this.gamepad1.dpad_right) {
		}

		if (this.gamepad1.y) {
			robot.grabber.raise();
		}
		if (this.gamepad1.a) {
		}
		if (this.gamepad1.x) {
			robot.grabber.leftGrab();
		}
		if (this.gamepad1.b) {
			robot.grabber.rightGrab();
		}

		/*
		 * Controller 2 settings
		 */

		if(this.gamepad2.right_stick_button && this.gamepad2.x) {
			manualOverride = true;
		}

		if (this.gamepad2.right_trigger > 0.5) {
			robot.arm.extendWithPower(-0.55);
		} else if (this.gamepad2.left_trigger > 0.5) {
			robot.arm.extendWithPower(0.55);
		} else {
			robot.arm.extendWithPower(0);
		}

		if (this.gamepad2.right_bumper) {
			armSpeed = 1.0;
		}
		if (this.gamepad2.left_bumper) {
			armSpeed = 0.27;
		}

		if (this.gamepad2.dpad_up) {
			robot.arm.raiseWithPower(0.4 * armSpeed);
		} else if (this.gamepad2.dpad_down) {
			robot.arm.lowerWithPower(0.15);
		} else {
			robot.arm.raiseWithPower(0);
		}

		if (this.gamepad2.dpad_left) {
		}
		if (this.gamepad2.dpad_right) {
		}

		if(this.gamepad2.y) {
			robot.arm.grabHand();
		} else if (this.gamepad2.a) {
			robot.arm.releaseHand();
		}

		if (this.gamepad2.x) {
		}
		if (this.gamepad2.b) {
		}

		if (this.gamepad2.right_stick_button && (manualOverride || !robot.maxTouch.isPressed())) { // Up
			robot.arm.elevateWithPower(-1.0);
		} else if (this.gamepad2.left_stick_button && (manualOverride || !robot.minTouch.isPressed())) { // Down
			robot.arm.elevateWithPower(1.0);
		} else {
			robot.arm.elevateWithPower(0);
		}

		elevatorLimit = "None";
		if(robot.minTouch.isPressed() && robot.maxTouch.isPressed()) {
			elevatorLimit = "Error";
		} else if(robot.minTouch.isPressed()) {
			elevatorLimit = "Minimum";
		} else if(robot.maxTouch.isPressed()) {
			elevatorLimit = "Maximum";
		}

		logger.numberLog("Speed", speed);
		logger.completeLog("Elevator Limit?", elevatorLimit);
		logger.completeLog("Elevator Manually Overridden?", manualOverride ? "True" : "False");
		logger.numberLog("Switches full-half speed", switchCount);
		logger.update();

	}

	/**
	 * Runs once after STOP is pushed
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
		if(grabbersAreFront) {
			leftX *= -1;
			leftY *= -1;
		}
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
