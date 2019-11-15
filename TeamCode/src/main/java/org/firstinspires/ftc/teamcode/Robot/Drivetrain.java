package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Logger;

import java.util.Locale;

public class Drivetrain extends RobotComponent {

	private DcMotor frontLeft;
	private DcMotor frontRight;
	private DcMotor backLeft;
	private DcMotor backRight;

	private Logger logger = null;

	Drivetrain(OpMode opmode) {
		super(opmode);
	}

	void init(Telemetry telemetry, DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight) {

		logger = new Logger(telemetry);

		this.frontLeft = frontLeft;
		this.frontRight = frontRight;
		this.backLeft = backLeft;
		this.backRight = backRight;

		frontLeft.setTargetPosition(0);
		frontRight.setTargetPosition(0);
		backLeft.setTargetPosition(0);
		backRight.setTargetPosition(0);

		frontLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
		backLeft.setDirection(DcMotor.Direction.REVERSE);
		frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
		backRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors

		setAllPowers(0);

		setRunMode(DcMotor.RunMode.RUN_USING_ENCODER, frontLeft, frontRight, backLeft, backRight);
	}

	@Override
	void logTeleOpData() {
		logger.numberLog("Frontleft", frontLeft.getPower());
		logger.numberLog("Frontright", frontRight.getPower());
		logger.numberLog("Backleft", backLeft.getPower());
		logger.numberLog("Backright", backRight.getPower());
	}

	/**
	 * Turns the robot a specified amount of degrees either clockwise or counterclockwise
	 *
	 * @param degrees     The degrees to move
	 * @param goClockwise If movement should be clockwise
	 */
	public void turnDegrees(double degrees, boolean goClockwise, double speed) {
		int inchTurn = (int) (degrees / 8.02);
		if (goClockwise) {
			runDistance(inchTurn, -inchTurn);
		} else {
			runDistance(-inchTurn, inchTurn);
		}

	}

	public void runDistance(double leftInches, double rightInches) {
		runDistance(leftInches, rightInches, DEFAULT_DRIVE_SPEED, 999);
	}

	public void runDistance(double leftInches, double rightInches, double speed) {
		runDistance(leftInches, rightInches, speed, 999);
	}

	/**
	 * Sets all the motors to run at a given speed to specified distances or until a maximum amount
	 * of time has been reached.
	 *
	 * @param leftInches  The distance for the left motors to reach
	 * @param rightInches The distance for the right motors to reach
	 * @param maxSeconds  The maximum amount of time for the function to take
	 * @param speed       The speed for all of the motors to be set at
	 */
	public void runDistance(double leftInches, double rightInches, double speed, double maxSeconds) {
		if (!opModeIsActive()) {
			return;
		}

		// Reset encoder values
		setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER, frontLeft, frontRight, backLeft, backRight);
		setRunMode(DcMotor.RunMode.RUN_USING_ENCODER, frontLeft, frontRight, backLeft, backRight);
		int newLeftTarget = frontLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
		int newRightTarget = frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
		frontLeft.setTargetPosition(newLeftTarget);
		backLeft.setTargetPosition(newLeftTarget);
		frontRight.setTargetPosition(newRightTarget);
		backRight.setTargetPosition(newRightTarget);

		// Turn On RUN_TO_POSITION
		setRunMode(DcMotor.RunMode.RUN_TO_POSITION, frontLeft, frontRight, backLeft, backRight);

		// reset the timeout time and start motion.
		ElapsedTime runtime = new ElapsedTime();
		setAllPowers(Math.abs(speed));

		// Log the path
		logger.completeLog("Path1", String.format(Locale.US,
				"Running to %7d :%7d", newLeftTarget, newRightTarget));

		// While game is still going, maxtime has not been reached, and none of the motors have reached their position
		while (opModeIsActive() && (runtime.seconds() < maxSeconds) &&
				(frontLeft.isBusy() && backLeft.isBusy()) &&
				(frontRight.isBusy() && backRight.isBusy())) {

			logger.addData("Path1", String.format(Locale.US,
					"Running to %7d :%7d", newLeftTarget, newRightTarget));
			logger.occasionalLog("Path2", String.format(Locale.US,
					"Running at %7d :%7d and %7d :%7d",
					frontLeft.getCurrentPosition(),
					frontRight.getCurrentPosition(),
					backLeft.getCurrentPosition(),
					backRight.getCurrentPosition()));
			logger.update();
		}

		// Stop all motion
		setAllPowers(0);

		// Turn off RUN_TO_POSITION
		setRunMode(DcMotor.RunMode.RUN_USING_ENCODER, frontLeft, frontRight, backLeft, backRight);

		// Stop all motion
		setAllPowers(0);

		sleep(200);
	}

	public void setPowerLeft(double power) {
		setIndividualPowers(-power, power, power, -power);
	}

	public void setPowerRight(double power) {
		setIndividualPowers(power,-power, -power, power);
	}


	/**
	 * Sets all 4 motors on the drivetrain to a given power
	 *
	 * @param power The power to set the motors to (-1.0 to 1.0)
	 */
	public void setAllPowers(double power) {
		setSpecificPowers(power, frontLeft, frontRight, backLeft, backRight);
	}

	public void setIndividualPowers(double[] motorPowers) {
		if (motorPowers.length != 4) {
			return;
		}
		frontLeft.setPower(motorPowers[0]);
		frontRight.setPower(motorPowers[1]);
		backLeft.setPower(motorPowers[2]);
		backRight.setPower(motorPowers[3]);
	}

	/**
	 * Sets the powers of all motors in the drive train to a different power
	 *
	 * @param frontLeftPower  The power for the front left motor
	 * @param frontRightPower The power for the front right motor
	 * @param backLeftPower   The power for the back left motor
	 * @param backRightPower  The power for the back right motor
	 */
	public void setIndividualPowers(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
		frontLeft.setPower(frontLeftPower);
		frontRight.setPower(frontRightPower);
		backLeft.setPower(backLeftPower);
		backRight.setPower(backRightPower);
	}

	@Override
	public void stopAllMotors() {
		setSpecificPowers(0, frontLeft, frontRight, backLeft, backRight);
	}

}
