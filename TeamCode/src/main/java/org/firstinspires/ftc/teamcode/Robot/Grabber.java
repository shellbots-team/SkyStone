package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Logger;

public class Grabber extends RobotComponent {
	public Grabber(OpMode opmode) {
		super(opmode);
	}

	Logger logger = null;
	CRServo rightServo = null;
	CRServo leftServo = null;

	void init(Telemetry telemetry, CRServo leftServo, CRServo rightServo) {
		logger = new Logger(telemetry);
		this.rightServo = leftServo;
		this.leftServo = rightServo;

	}

	public void leftGrab() {
		setServoPosition(leftServo, 0.185);
	}

	public void rightGrab() {
		setServoPosition(rightServo, 0.975);
	}

	public void raise() {
		setServoPosition(rightServo, 0.45);
		setServoPosition(leftServo, 0.65);
	}


	@Override
	public void stopAllMotors() {

	}

	@Override
	void logTeleOpData() {
		ServoController sc = rightServo.getController();

		logger.completeLog("Left Grabber", String.valueOf(sc.getServoPosition(rightServo.getPortNumber())));
		logger.completeLog("Right Grabber", String.valueOf(sc.getServoPosition(leftServo.getPortNumber())));
	}
}
