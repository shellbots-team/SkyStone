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
	CRServo leftServo = null;
	CRServo rightServo = null;

	void init(Telemetry telemetry, CRServo leftServo, CRServo rightServo) {
		logger = new Logger(telemetry);
		this.leftServo = leftServo;
		this.rightServo = rightServo;

	}

	public void leftGrab() {
		setServoPosition(leftServo, 0.435);
	}

	public void rightGrab() {
		setServoPosition(rightServo, 0.68);
	}

	public void raise() {
		setServoPosition(leftServo, 1);
		setServoPosition(rightServo, 0.2);
	}


	@Override
	public void stopAllMotors() {

	}

	@Override
	void logTeleOpData() {
		ServoController sc = leftServo.getController();

		logger.completeLog("Left Grabber", String.valueOf(sc.getServoPosition(leftServo.getPortNumber())));
		logger.completeLog("Right Grabber", String.valueOf(sc.getServoPosition(rightServo.getPortNumber())));
	}
}
