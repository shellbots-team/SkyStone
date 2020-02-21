package org.firstinspires.ftc.teamcode.Outreach;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Logger;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Created by shell on 09/10/2019.
 */
@Disabled
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(group = "Outreach", name = "Christmas on main")
public class Christmas_On_Main extends OpMode {

	private Robot robot = new Robot();
	private Logger logger = null;
	private ColorSensor colorSensor = null;
	private STATUS current_status = STATUS.STOPPED;

	private final double RED_THRESHOLD = 50;
	private final double GREEN_THRESHOLD = 50;
	private final double SPEED = 0.25;

	private double speed = 1.0;

	private enum STATUS {
		NORMAL,
		AUTO,
		STOPPED
	}

	/**
	 * Run once after INIT is pushed
	 */
	@Override
	public void init() {
		robot.init(hardwareMap, telemetry, this);
		logger = new Logger(telemetry);
		colorSensor = this.hardwareMap.get(ColorSensor.class, "colorSensor");

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

		if (this.gamepad1.y || this.gamepad1.a) {
			current_status = STATUS.STOPPED;
			colorSensor.enableLed(false);
		}
		if (this.gamepad1.b) {
			current_status = STATUS.NORMAL;
			colorSensor.enableLed(false);
		}
		if (this.gamepad1.x) {
			current_status = STATUS.AUTO;
			colorSensor.enableLed(true);
		}

		logger.addData("Status", String.valueOf(current_status));
		logger.addData("Red", String.valueOf(colorSensor.red()));
		logger.addData("Green", String.valueOf(colorSensor.green()));
		logger.addData("Blue", String.valueOf(colorSensor.blue()));
		logger.update();

		if(current_status == STATUS.STOPPED) {
			colorSensor.enableLed(false);
			robot.stopAllMotors();
		} else if(current_status == STATUS.AUTO) {
			if(colorSensor.green() > GREEN_THRESHOLD || colorSensor.red() > RED_THRESHOLD) {
				if(colorSensor.green() > colorSensor.red()) {
					robot.drivetrain.setAllPowers(SPEED);
				} else { robot.drivetrain.setAllPowers(-SPEED); }
			} else { robot.stopAllMotors();}
		} else if(current_status == STATUS.NORMAL) {


			/* Controller Layouts
			 *
			 * Controller 1 - "Body Controller"
			 *      Left Trigger     - Set full speed
			 *      Right Trigger    - Set half speed
			 *
			 *      Dpad Up          - Release Foundation / Move lower servos up
			 *      Dpad Down        - Grab Foundation / Move lower servos down
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
				robot.releaseFoundation();
			} else if (this.gamepad1.dpad_down) {
				robot.grabFoundation();
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
			}
			if (this.gamepad2.left_bumper) {
			}

			if (this.gamepad2.dpad_up) {
				if (robot.arm.isGrabbing()) {
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
		}
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
