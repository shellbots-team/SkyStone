package org.firstinspires.ftc.teamcode.Red.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.BaseSkystone;

import java.util.List;
import java.util.Locale;

/**
 * Created by shell on 09/24/2019.
 */

@Disabled
@Autonomous(group = "Basic", name = "Red: SkyStone")
public class RedBasicSkyStone extends BaseSkystone {

    @Override
    public void runOpMode() {

        super.runOpMode();

        // Step 0 - Setting status to "Ready to run"
        robot.fullLogAndUpdate("Status", "Step 0 - Ready To Run");

        // Waiting until user presses start
        waitForStart();

        //Step 1 - Driving until on corner tape
        robot.fullLogAndUpdate("Status", "Step 1 - Driving until on corner tape");
        robot.setMotorPowers(-0.75);
        int i = 0;
        while (!robot.isOnLine() && opModeIsActive()) {
            i++;
            if (i % 10 == 0) {
                robot.fullLogAndUpdate("Color", robot.colorSensor.red() + " : " + robot.colorSensor.green() + " : " + robot.colorSensor.blue());
            }
        }
        robot.setMotorPowers(0);

        // Step 2 - Running until on the wall
        robot.fullLogAndUpdate("Status", "Step 2 - Driving until on the wall");
        robot.runInchesWithEncoders(-4, -4);

        // Step 4 - Driving until next to the blocks
        robot.fullLogAndUpdate("Status", "Step 4 - Driving until next to blocks");
        robot.setMotorPowersSideways(0.75, false);
        sleep(1350);
        robot.setMotorPowers(0);
        sleep(250);

        // Step 3 - Raising the arm
        robot.fullLogAndUpdate("Status", "Step 3 - Raising the arm");
        robot.raiseArm();
        sleep(250);

        // Step 5 - Driving next to blocks slowly
        robot.fullLogAndUpdate("Status", "Step 5 - Driving next to blocks slowly");
        robot.setMotorPowers(0.5);

        // Step 6 - If skystone seen, stop
        robot.fullLogAndUpdate("Status", "Step 6 - Scanning for skystone");
        ElapsedTime currTime = new ElapsedTime();
        boolean elementNotFound = true;
        while (currTime.seconds() < 3 && opModeIsActive() && elementNotFound) {
            if (tfod != null) {
                sleep(250);
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    robot.fullLog("# Object Detected", updatedRecognitions.size());
                    robot.fullLog("Time", currTime.seconds());

                    // step through the list of recognitions and display boundary info.
                    i = 0;
                    for (Recognition recognition : updatedRecognitions) {
                        robot.fullLog("ObjectDetection",
                                  "\nDetection Number " + i +
                                        "\nLabel " + recognition.getLabel() +
                                        "\nConfidence: " + recognition.getConfidence() +
                                        "\nLeft: " + recognition.getLeft() +
                                        "\nRight: " + recognition.getRight() +
                                        "\nTop: " + recognition.getTop() +
                                        "\nBottom: " + recognition.getBottom());
                        i++;
                    }
                    telemetry.update();
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel() == LABEL_SECOND_ELEMENT) {
                            robot.fullLogAndUpdate("ObjectDetection", "Found skystone");
                            elementNotFound = false;
                        }
                    }
                }
            }
        }

        sleep(250);

        // Step 7 - Stopping motors if block not found
        robot.fullLogAndUpdate("Status", "Step 7 - Stopping motors if skystone not found");
        robot.setMotorPowers(0);

        // Step 8 - Getting closer to grab a block
        robot.fullLogAndUpdate("Status", "Step 8 - Getting closer to block in order to grab it");
        robot.setMotorPowersSideways(0.75, false);
        sleep(600);
        robot.setMotorPowers(0);

        // Step 9 - Dropping arm onto the block
        robot.fullLogAndUpdate("Status", "Step 9 - Dropping arm onto the block");
        robot.lowerArm();
        sleep(250);

        // Step 10 - Grabbing onto the block with hand
        robot.fullLogAndUpdate("Status", "Step 10 - Grabbing the block with hand");
        robot.grabHand();
        sleep(2000);

        // Step 11 - Raising the arm
        robot.fullLogAndUpdate("Status", "Step 11 - Raising the arm");
        robot.raiseArm();
        sleep(200);

        // Step 12 - Turning to face the baseplate
        robot.fullLogAndUpdate("Status", "Step 12 - Turning to look at the baseplate");
        robot.turnDegreesWithEncoders(90, true);

        // Step 13 - Lowering the arm to fit under bridge
        robot.fullLogAndUpdate("Status", "Step 13 - Lowering the arm to fit under the bridge");
        robot.lowerArm();
        sleep(300);

        // Step 14 - Driving until on the midline
        robot.fullLogAndUpdate("Status", "Step 14 - Driving until on the midline");
        robot.setMotorPowers(1.0);
        i = 0;
        while (!robot.isOnLine() && opModeIsActive()) {
            i++;
            if (i % 10 == 0) {
                robot.fullLogAndUpdate("Color", robot.colorSensor.red() + " : " + robot.colorSensor.green() + " : " + robot.colorSensor.blue());
            }
        }
        robot.setMotorPowers(0);

        // Step 15 - Driving to baseplate
        robot.fullLogAndUpdate("Status", "Step 15 - Driving to the baseplate");
        robot.runInchesWithEncoders(16, 16);

        // Step 16 - Lowering the arm
        robot.fullLogAndUpdate("Status", "Step 16 - Lowering the arm");
        robot.lowerArm();
        sleep(200);

        // Step 17 - Releasing the block
        robot.fullLogAndUpdate("Status", "Step 17 - Releasing the block");
        robot.releaseHand();
        sleep(200);

        // Step 18 - Raising the arm
        robot.fullLogAndUpdate("Status", "Step 18 - Raising the arm");
        robot.raiseArm();
        sleep(200);

        // Step 19 - Driving away from baseplate
        robot.fullLogAndUpdate("Status", "Step 19 - Driving away from the baseplate");
        robot.runInchesWithEncoders(-6, -6);

        // Step 20 - Lowering the arm to fit under bridge
        robot.fullLogAndUpdate("Status", "Step 20 - Lowering the arm in order to fit under the bridge");
        robot.lowerArm();
        sleep(200);

        // Step 21 - Driving until on midline
        robot.fullLogAndUpdate("Status", "Step 21 - Driving until on the midline");
        robot.setMotorPowers(-0.75);
        i = 0;
        while (!robot.isOnLine() && opModeIsActive()) {
            i++;
            if (i % 10 == 0) {
                robot.fullLogAndUpdate("Color", robot.colorSensor.red() + " : " + robot.colorSensor.green() + " : " + robot.colorSensor.blue());
            }
        }

        // Step 22 - Stopping motors/finished
        robot.fullLogAndUpdate("Status", "Step 22 - Stopping/Finished");
        robot.setMotorPowers(0);
        if (tfod != null) {
            tfod.shutdown();
        }
    }
}
