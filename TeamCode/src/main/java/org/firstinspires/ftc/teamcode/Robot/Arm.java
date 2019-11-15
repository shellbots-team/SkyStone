package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Logger;

import java.util.Locale;

public class Arm extends RobotComponent {

	public DcMotor leftArm = null;
	public DcMotor rightArm = null;
	private DcMotor extendArm = null;
	private CRServo leftHand = null;
	private CRServo rightHand = null;
	private boolean isGrabbing = false;

	private Logger logger = null;

	Arm(OpMode opmode) {
		super(opmode);
	}

	void init(Telemetry telemetry, DcMotor leftArm, DcMotor rightArm, DcMotor extendArm, CRServo leftHand, CRServo rightHand) {
		logger = new Logger(telemetry);

		this.leftHand = leftHand;
		this.rightHand = rightHand;

		this.leftArm = leftArm;
		this.rightArm = rightArm;
		this.extendArm = extendArm;

		leftArm.setDirection(DcMotor.Direction.REVERSE);
		rightArm.setDirection(DcMotor.Direction.REVERSE);
		extendArm.setDirection(DcMotor.Direction.FORWARD);

		leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		extendArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		leftArm.setTargetPosition(0);
		rightArm.setTargetPosition(0);

		// Set all motors to zero power
		setSpecificPowers(0, leftArm, rightArm, extendArm);

		// Set all motors to run with encoders.
		setRunMode(DcMotor.RunMode.RUN_USING_ENCODER, leftArm, rightArm, extendArm);
	}

	@Override
	public void logTeleOpData() {
//		logger.numberLog("Leftarm", leftArm.getPower());
//		logger.numberLog("Rightarm", rightArm.getPower());
//		logger.numberLog("Extendarm", extendArm.getPower());
		ServoController controller = leftHand.getController();
		logger.numberLog("LeftPos", controller.getServoPosition(leftHand.getPortNumber()));
		logger.numberLog("LeftPow", leftHand.getPower());
		logger.numberLog("RightPos", controller.getServoPosition(rightHand.getPortNumber()));
		logger.numberLog("RightPow", rightHand.getPower());
	}

	public 	void elevateDistance(double height, double speed) {
		elevateDistance(height, speed, 9999);
	}

	/**
	 * Moves the arm to a given height with a specified speed
	 *
	 * @param height The height (in inches) to move to
	 * @param speed  The power to give the arm motors
	 */
	public void elevateDistance(double height, double speed, long maxMilliseconds) {
		if (!opModeIsActive()) {
			return;
		}

		// Reset encoders
		setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER, leftArm, rightArm);

		int armTarget = (leftArm.getCurrentPosition() + rightArm.getCurrentPosition()) / 2
				+ (int) (height * COUNTS_PER_INCH);

		leftArm.setTargetPosition(-armTarget);
		rightArm.setTargetPosition(armTarget);


		ElapsedTime runtime = new ElapsedTime();

		setSpecificPowers(Math.abs(speed), leftArm, rightArm);

		// Log the path
		logger.completeLog("ArmPath", String.format(Locale.US,
				"Runnning to %7d", armTarget));

		while (opModeIsActive() && runtime.milliseconds() < maxMilliseconds&& (leftArm.isBusy() && rightArm.isBusy())) {
			logger.addData("ArmPath", String.format(Locale.US,
					"Runnning to %7d", armTarget));
			logger.completeLog("ArmPath2", String.format(Locale.US,
					"Running at %7d :%7d", leftArm.getCurrentPosition(), rightArm.getCurrentPosition()));
			logger.update();
		}

		setSpecificPowers(0, leftArm, rightArm);

		setRunMode(DcMotor.RunMode.RUN_USING_ENCODER, leftArm, rightArm);

		sleep(200);
	}

	public void extendWithPower(double power) {
		extendArm.setPower(power);
	}

	public void raiseWithPower(double power) {
		leftArm.setPower(power);
		rightArm.setPower(power);
	}

	public void lowerWithPower(double power) {
		leftArm.setPower(-power);
		rightArm.setPower(-power);
	}

	public void raiseArm() {
		setRunMode(DcMotor.RunMode.RUN_USING_ENCODER, leftArm);

		raiseWithPower(0.2);
		sleep(850);

		int leftPos = leftArm.getCurrentPosition();

		leftArm.setTargetPosition(leftPos);



		setSpecificPowers(0.2, leftArm);

		setSpecificPowers(0, rightArm);
	}

	public void lowerArm() {
		setRunMode(DcMotor.RunMode.RUN_USING_ENCODER, leftArm);
		lowerWithPower(0.01);
		sleep(250);
		stopAllMotors();
	}

	public void grabHand() {
		isGrabbing = true;
		setServoPosition(rightHand, 1);
		setServoPosition(leftHand, 0);
		sleep(1500);
	}

	public void releaseHand() {
		isGrabbing = false;
		setServoPosition(rightHand,0);
		setServoPosition(leftHand, 1);
		sleep(1000);
	}

	public boolean getIsGrabbing() {
		return isGrabbing;
	}

	public void maintainPosition(int pos) {
		setSpecificPowers(0.2);
		leftArm.setTargetPosition(pos);
		rightArm.setTargetPosition(pos);
	}

	@Override
	public void stopAllMotors() {
		setSpecificPowers(0, leftArm, rightArm, extendArm);
	}

}
