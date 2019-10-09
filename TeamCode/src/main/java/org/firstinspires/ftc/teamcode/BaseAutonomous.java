package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by shell on 10/05/2019.
 */

@Autonomous(group = "Base", name = "Base: Autonomous")
public class BaseAutonomous extends LinearOpMode {

    Robot robot = new Robot();

    @Override
    public void runOpMode() {
        // Initialize motors/servos
        robot.init(hardwareMap, telemetry, this);
    }
}