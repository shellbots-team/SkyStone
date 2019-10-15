package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by shell on 09/24/2019.
 */

@Autonomous(group = "Basic", name = "Basic: SkyStone")
public class BasicSkyStone extends BaseSkystone {

    @Override
    public void runOpMode() {

        super.runOpMode();

        // Setting status to "Ready to run"
        telemetry.addData("Status", "Ready To Run");
        telemetry.update();

        // Waiting until user presses start
        waitForStart();

        // Step 1 - Lift arm to unblock camera
        robot.fullLog("Status", "Step 1");
        robot.raiseArm();

        // Step 2 - Detect correct block
        robot.fullLog("Status", "Step 2");

        // Step 3 - Move sideways
        robot.fullLog("Status", "Step 3");

        // Step 4 - Move to block
        robot.fullLog("Status", "Step 4");
        robot.runInchesWithEncoders(4, 4);

        // Step 5 - Lower arm
        robot.fullLog("Status", "Step 5");
        robot.lowerArm();

        // Step 6 - Grab block
        robot.fullLog("Status", "Step 6");

        // Step 7 - Raise arm
        robot.fullLog("Status", "Step 7");
        robot.raiseArm();

        // Step 8 - Moving back to wall
        robot.fullLog("Status", "Step 8");
        robot.runInchesWithEncoders(-4, -4);

        // Step 9 - Turning to baseplate
        robot.fullLog("Status", "Step 9");
        robot.turnDegreesWithEncoders(90, true);

        // Step 10 - Moving to baseplate
        robot.fullLog("Status", "Step 10");
        robot.runInchesWithEncoders(12, 12);

        // Step 11 - Dropping block
        robot.fullLog("Status", "Step 11");


        // Step 12 - Moving backwards to line
        robot.fullLog("Status", "Step 12");
        while (!robot.isOnLine()) {
            robot.setMotorPowers(-1);
        }
        sleep(25);
        robot.setMotorPowers(0);

        // Step X - Finished
        robot.fullLog("Status", "Finished");
        robot.setMotorPowers(0);
    }
}
