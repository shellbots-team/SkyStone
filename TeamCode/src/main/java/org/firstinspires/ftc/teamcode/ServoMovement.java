package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Robot.Robot;
@Disabled
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(group = "Manual", name = "ServoMovement")
public class ServoMovement extends OpMode {

	CRServo left;
	CRServo right;

	/**
	 * Run once after INIT is pushed
	 */
	@Override
	public void init() {
		left = this.hardwareMap.get(CRServo.class, "left");
		right = this.hardwareMap.get(CRServo.class, "right");
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
	}

	@Override
	public void loop() {
		if (gamepad1.a) {
			left.setPower(1.0);
			right.setPower(-1.0);
		} else if (gamepad1.y) {
			left.setPower(-1.0);
			right.setPower(1.0);
		} else {
			left.setPower(0);
			right.setPower(0);
		}
		telemetry.addData("Status", "Running");
	}

	/**
	 * Runxs once after STOP is pushed
	 */
	@Override
	public void stop() {
	}
}
