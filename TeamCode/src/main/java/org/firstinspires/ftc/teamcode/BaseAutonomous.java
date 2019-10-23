package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by shell on 10/05/2019.
 */

@Autonomous(group = "Base", name = "Base: Autonomous")
@Disabled
public class BaseAutonomous extends LinearOpMode {

    Robot robot = new Robot();
    static final double DISTANCE_TO_BASEPLATE = 16.3;

    @Override
    public void runOpMode() {
        // Initialize motors/servos
        robot.init(hardwareMap, telemetry, this);
    }
}