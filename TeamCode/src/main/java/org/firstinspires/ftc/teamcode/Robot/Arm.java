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
	private DcMotor.RunMode armRunMode = DcMotor.RunMode.RUN_USING_ENCODER;
	private int currentPosition = 0;

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
		logger.numberLog("Leftarm", leftArm.getPower());
		logger.numberLog("Rightarm", rightArm.getPower());
		logger.numberLog("Extendarm", extendArm.getPower());
	}

	public void extendWithPower(double power) {
		extendArm.setPower(power);
	}

	public void raiseWithPower(double power) {
		if(armRunMode != DcMotor.RunMode.RUN_USING_ENCODER) {
			setRunMode(DcMotor.RunMode.RUN_USING_ENCODER, leftArm, rightArm);
			armRunMode = DcMotor.RunMode.RUN_USING_ENCODER;
		}
		leftArm.setPower(power);
		rightArm.setPower(power);
	}

	public void lowerWithPower(double power) {
		if(armRunMode != DcMotor.RunMode.RUN_USING_ENCODER) {
			setRunMode(DcMotor.RunMode.RUN_USING_ENCODER, leftArm, rightArm);
			armRunMode = DcMotor.RunMode.RUN_USING_ENCODER;
		}
		leftArm.setPower(-power);
		rightArm.setPower(-power);
	}

	public void raiseArm(boolean isHoldingBlock) {
		if(isHoldingBlock) {
			raiseWithPower(0.2);
			sleep(800);
		} else {
			raiseWithPower(0.2);
			sleep(700);
		}

		currentPosition = leftArm.getCurrentPosition();
	}

	public void lowerArm(boolean isHoldingBlock) {
		if(isHoldingBlock) {
			lowerWithPower(0.04);
			sleep(1000);
		} else {
			lowerWithPower(0.1);
			sleep(700);
		}
		stopAllMotors();
		currentPosition = leftArm.getCurrentPosition();
	}

	public void maintainPosition() {
		if(armRunMode == DcMotor.RunMode.RUN_TO_POSITION) {
			return;
		} else if(armRunMode != DcMotor.RunMode.RUN_USING_ENCODER) {
			setRunMode(DcMotor.RunMode.RUN_USING_ENCODER, rightArm);
		}
		leftArm.setTargetPosition(currentPosition);

		setRunMode(DcMotor.RunMode.RUN_TO_POSITION, leftArm);
		armRunMode = DcMotor.RunMode.RUN_TO_POSITION;

		leftArm.setPower(0.1);
		rightArm.setPower(0);
	}

	public void grabHand() {
		isGrabbing = true;
		setServoPosition(leftHand, 0);
	}

	public void partialGrabHand() {
		setServoPosition(leftHand, 0.5);
	}

	public void releaseHand() {
		isGrabbing = false;
		setServoPosition(leftHand, 0.8);
	}

	public boolean isGrabbing() {
		return isGrabbing;
	}

	@Override
	public void stopAllMotors() {
		setSpecificPowers(0, leftArm, rightArm, extendArm);
	}

}
