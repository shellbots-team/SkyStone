package org.firstinspires.ftc.teamcode.Red.Baseplate;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.BaseAutonomous;

/**
 * Created by shell on 09/24/2019.
 */

@Autonomous(group = "Basic", name = "Red: BaseplateWall")
public class RedBasicBaseplateWall extends BaseAutonomous {

    private final int SAFE_GUARD = 4;

    @Override
    public void runOpMode() {

        super.runOpMode();

        waitForStart();

        // Step 0 - Ready to run
        robot.fullLogAndUpdate("Status", "Step 0 - Ready To Run");

        // Waiting until user presses start
        waitForStart();

        robot.releaseBaseplate();

        robot.driveRight();
        sleep(600);

        // Step 1 - Move near the baseplate
        robot.fullLogAndUpdate("Status", "Step 1 - Moving near the baseplate");
        robot.runInchesWithEncoders(DISTANCE_TO_BASEPLATE-SAFE_GUARD, DISTANCE_TO_BASEPLATE-SAFE_GUARD);

        // Step 2 - Moving next to the baseplate
        robot.fullLogAndUpdate("Status", "Step 2 - Moving next to the baseplate");
        robot.runInchesWithEncoders(SAFE_GUARD, SAFE_GUARD, 999, 0.5);

        // Step 3 - grab onto the baseplate
        robot.fullLogAndUpdate("Status", "Step 3 - Grabbing onto the baseplate");
        robot.grabBaseplate();
        sleep(1000);

        // Step 4 - Drag baseplate to corner
        robot.fullLogAndUpdate("Status", "Step 4 - Dragging baseplate to the corner");
        robot.runInchesWithEncoders(-(DISTANCE_TO_BASEPLATE+2), -(DISTANCE_TO_BASEPLATE+2));

        // Step 5 - Release the baseplate
        robot.fullLogAndUpdate("Status", "Step 5 - Releasing the baseplate");
        robot.releaseBaseplate();
        sleep(100);

        //Step 6 - Move away from the build zone
        robot.fullLogAndUpdate("status", "Step 6 - Moving away from the build zone");
        robot.driveLeft();
        sleep(2000);
        robot.setMotorPowers(0);

        robot.runInchesWithEncoders(5, 5);

        robot.driveRight();
        sleep(800);
        robot.setMotorPowers(0);

        robot.runInchesWithEncoders(-5, -5);

        // Step 7 - Move until on the midline
        robot.fullLogAndUpdate("Status", "Step 7 - Moving until on the midline");
        robot.driveLeft();
        int i = 0;
        ElapsedTime rt = new ElapsedTime();
        rt.reset();
        while (!robot.isOnLine() && opModeIsActive() && rt.seconds() < 4.5) {
            i++;
            if( i % 10 == 0) {
                robot.fullLogAndUpdate("Color", robot.colorSensor.red() + " : " + robot.colorSensor.green() + " : " + robot.colorSensor.blue());
            }
        }
        robot.setMotorPowers(0);

        // Step 8 - Fixing alignment with line
        robot.fullLogAndUpdate("Status", "Step 8 - Fixing alignment with line");
        robot.driveRight();
        sleep(250);
        robot.setMotorPowers(0);

        // Step 9 - Run back to wall
        robot.fullLogAndUpdate("Status", "Step 9");
        robot.runInchesWithEncoders(-2, -2);

        // Step 10 - Finished
        robot.fullLogAndUpdate("Status", "Step 10 - Stopping motors/finished");
        robot.setMotorPowers(0);

    }
}
