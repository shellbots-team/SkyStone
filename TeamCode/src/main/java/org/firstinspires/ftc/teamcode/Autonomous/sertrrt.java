package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(group = "Base", name = "Servo : Autonomous")
public class sertrrt extends LinearOpMode {

	CRServo serv = null;

	@Override
	public void runOpMode() {

			final boolean PUSH_BASEPLATE = false;
			final double DISTANCE_TO_BASEPLATE = 14.8;
			final double SAFE_GUARD = 2;

			serv = hardwareMap.get(CRServo.class, "exServo");

			waitForStart();

			serv.setPower(1.0);

			sleep(30000);
			// Step 16 - Finished
		}
}
