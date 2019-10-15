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

        // Step 0 - Ready to run
        robot.fullLog("Status", "Ready To Run");

        // Waiting until user presses start
        waitForStart();

        // Step 1 - Move to the baseplate
        robot.fullLog("Status", "Step 1");
        robot.runInchesWithEncoders(4, 4);

        // Step 2 - grab onto the baseplate
        robot.fullLog("Status", "Step 2");
        robot.grabBaseplate();
        sleep(100);

        // Step 3 - Drag baseplate to corner
        robot.fullLog("Status", "Step 3");
        robot.runInchesWithEncoders(-4, -4);

        // Step 4 - Release the baseplate
        robot.fullLog("Status", "Step 4");
        robot.releaseBaseplate();
        sleep(100);

        // Step 5 - Turning to look at the midline
        robot.fullLog("Status", "Step 5");
        robot.turnDegreesWithEncoders(90, true);

        // Step 6 - Move until on the line
        robot.fullLog("Status", "Step 6");
        while (!robot.isOnLine()) {
            robot.setMotorPowers(1);
        }
        sleep(25);
        robot.setMotorPowers(0);

        // Step 7 - Turn to middle
        robot.fullLog("Status", "Step 7");
        robot.turnDegreesWithEncoders(90, true);

        // Step 8 - Drive to middle
        robot.fullLog("Status", "Step 8");
        robot.runInchesWithEncoders(2, 2);

        // Step X - Finished
        robot.fullLog("Status", "Finished");
        robot.setMotorPowers(0);

    }
}
