package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by shell on 08/27/2019.
 */

@Autonomous
public class AutonomousMode extends LinearOpMode {

    // Declare motors/servos
    private DcMotor myMotor;

    @Override
    public void runOpMode() {

        // Initialize motors/servos
        myMotor = hardwareMap.get(DcMotor.class, "myMotor");

        // Setting status to "Ready to run"
        telemetry.addData("Status", "Ready To Run");
        telemetry.update();

        // Waiting until user presses start
        waitForStart();

        // Step 1
        telemetry.addData("Status", "Step 1");
        telemetry.update();
        // Take Action
        sleep(1000); // Sleep for 1 second

        telemetry.addData("Status", "Finished");
        telemetry.update();

    }
}
