package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by shell on 09/24/2019.
 */

@Autonomous(group = "Basic", name = "Basic: Baseplate")
public class BasicBaseplate extends BaseAutonomous {

    @Override
    public void runOpMode() {

        super.runOpMode();

        waitForStart();

        // Step 0 - Ready to run
        robot.fullLog("Status", "Ready To Run");

        // Waiting until user presses start
        waitForStart();

        robot.releaseBaseplate();

        // Step 1 - Move to the baseplate
        robot.fullLog("Status", "Step 1");
        robot.runInchesWithEncoders(17, 17);

        // Step 2 - grab onto the baseplate
        robot.fullLog("Status", "Step 2");
        robot.grabBaseplate();
        sleep(1000);

        // Step 3 - Drag baseplate to corner
        robot.fullLog("Status", "Step 3");
        robot.runInchesWithEncoders(-17, -17);

        // Step 4 - Release the baseplate
        robot.fullLog("Status", "Step 4");
        robot.releaseBaseplate();
        sleep(100);

        // Step 6 - Move until on the line
        robot.fullLog("Status", "Step 6");
        robot.setMotorPowersSideways(1.0, false);
        int i = 0;
        while (!robot.isOnLine() && opModeIsActive()) {
            i++;
            if( i % 10 == 0) {
                robot.fullLog("Color", robot.colorSensor.red() + " : " + robot.colorSensor.green() + " : " + robot.colorSensor.blue());
                robot.telemetry.update();
            }
        }
        robot.setMotorPowers(0);

        robot.setMotorPowersSideways(1.0, true);
        sleep(250);
        robot.setMotorPowers(0);

        // Step X - Finished
        robot.fullLog("Status", "Finished");
        robot.setMotorPowers(0);

    }
}
