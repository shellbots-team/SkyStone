package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.CRServo;

public class CRGrabber {

	private boolean isUp = true;
	private double upPosition = 0;
	private double downPosition = 0;
	private CRServo servo = null;

	public CRGrabber() {
	}

	public void set(CRServo servo) {
		this.servo = servo;
	}

	public void setPositions(double upPosition, double downPosition) {
		this.upPosition = upPosition;
		this.downPosition = downPosition;
	}

	public void flip() {
		if(isUp) {
			setServoPosition(downPosition);
		} else {
			setServoPosition(upPosition);
		}
		this.isUp = !this.isUp;
	}

	public void setServoPosition(double position) {
		this.servo.getController().setServoPosition(this.servo.getPortNumber(), position);
	}

	public double getPosition() {
		return this.servo.getController().getServoPosition(this.servo.getPortNumber());
	}
}
