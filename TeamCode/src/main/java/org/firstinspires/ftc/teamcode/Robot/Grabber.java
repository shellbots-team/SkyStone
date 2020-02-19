package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Logger;

public class Grabber extends RobotComponent {
	public Grabber(OpMode opmode) {
		super(opmode);
	}

	Logger logger = null;
	CRGrabber baseRightGrabber = new CRGrabber();
	CRGrabber altRightGrabber = new CRGrabber();
	CRGrabber baseLeftGrabber = new CRGrabber();
	CRGrabber altLeftGrabber = new CRGrabber();

	void init(Telemetry telemetry, CRServo baseRightServo, CRServo altRightServo, CRServo baseLeftServo, CRServo altLeftServo) {
		logger = new Logger(telemetry);
		this.baseRightGrabber.set(baseRightServo);
		baseRightGrabber.setPositions(0, 0.45);
		this.altRightGrabber.set(altRightServo);
		altRightGrabber.setPositions(0.25, 0.9);
		this.baseLeftGrabber.set(baseLeftServo);
		baseLeftGrabber.setPositions(1, 0.4);
		this.altLeftGrabber.set(altLeftServo);
		altLeftGrabber.setPositions(1, 0);
	}

	public static enum Side {
		LEFT, RIGHT
	}

	public static enum Level {
		BASE, ALT
	}

	public void flip(Level level, Side side) {
		if(level == Level.BASE) {
			if(side == Side.RIGHT) {
				baseRightGrabber.flip();
			} else {
				baseLeftGrabber.flip();
			}
		} else {
			if(side == Side.RIGHT) {
				altRightGrabber.flip();
			} else {
				altLeftGrabber.flip();
			}
		}
	}

	@Override
	public void stopAllMotors() {

	}

	@Override
	void logTeleOpData() {
		logger.completeLog("Base Right Grabber", String.valueOf(baseRightGrabber.getPosition()));
		logger.completeLog("Alt Right Grabber", String.valueOf(altRightGrabber.getPosition()));
		logger.completeLog("Base Left Grabber", String.valueOf(baseLeftGrabber.getPosition()));
		logger.completeLog("Alt Left Grabber", String.valueOf(altLeftGrabber.getPosition()));
	}
}
