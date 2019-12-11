package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Grabber extends RobotComponent {
	public Grabber(OpMode opmode) {
		super(opmode);
	}

	Telemetry telemetry = null;
	CRServo servo = null;

	void init(Telemetry telemetry, CRServo servo) {
		this.telemetry = telemetry;
		this.servo = servo;
	}

	public void grab() {
		setServoPosition(servo, 1);
	}

	public void release() {
		setServoPosition(servo, -1);
	}


	@Override
	public void stopAllMotors() {

	}

	@Override
	void logTeleOpData() {

	}
}
