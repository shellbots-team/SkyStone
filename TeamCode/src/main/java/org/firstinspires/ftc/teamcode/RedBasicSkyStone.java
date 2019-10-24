package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
<<<<<<< HEAD
=======
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;
import java.util.Locale;
>>>>>>> Fixing github error

/**
 * Created by shell on 09/24/2019.
 */

@Autonomous(group = "Basic", name = "Basic: SkyStone")
public class RedBasicSkyStone extends BaseSkystone {

    @Override
    public void runOpMode() {

        super.runOpMode();

        // Step 0 - Setting status to "Ready to run"
        telemetry.addData("Status", "Ready To Run");
        telemetry.update();

        // Waiting until user presses start
        waitForStart();

<<<<<<< HEAD
        // Step 1 - Lift arm to unblock camera
        robot.fullLog("Status", "Step 1");
        robot.raiseArm();
=======
        //Step 1 - Getting ready to find skystone
        robot.fullLog("Status", "Step 1");
        robot.setMotorPowers(-0.75);
        int i = 0;
        while (!robot.isOnLine() && opModeIsActive()) {
            i++;
            if (i % 10 == 0) {
                robot.fullLog("Color", robot.colorSensor.red() + " : " + robot.colorSensor.green() + " : " + robot.colorSensor.blue());
                robot.telemetry.update();
            }
        }
        robot.setMotorPowers(0);

        robot.runInchesWithEncoders(-4, -4);

        robot.raiseArm();

        robot.setMotorPowersSideways(0.75, false);

        sleep(1500);

        robot.setMotorPowers(0.25);

        ElapsedTime currTime = new ElapsedTime();
        boolean elementNotFound = true;

        while (currTime.seconds() < 4 && opModeIsActive() && elementNotFound) {
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
                        robot.fullLog(String.format(Locale.US, "Detection Number %d" +
                                        "\nConfidence: %.03f " +
                                        "\nLabel: %s" +
                                        "\nLeft: %.03f\n Right: %.03f\n Top: %.03f\n Bottom: %.03f\n",
                                i, recognition.getConfidence(), recognition.getLabel(), recognition.getLeft(), recognition.getRight(), recognition.getTop(), recognition.getBottom()));
                    }
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel() == LABEL_SECOND_ELEMENT) {
                            robot.fullLog("Found skystone");
                            robot.setMotorPowers(0);
                            elementNotFound = false;
                        }
                    }
                    telemetry.update();
                }
            }
        }

        robot.setMotorPowersSideways(0.75, false);

        sleep(600);

        robot.setMotorPowers(0);

        robot.lowerArm();
        sleep(250);

        robot.grabHand();
        sleep(500);

        robot.raiseArm();
        sleep(200);

        robot.turnDegreesWithEncoders(90, true);

        robot.lowerArm();
        sleep(300);

        robot.setMotorPowers(1.0);
        i = 0;
        while (!robot.isOnLine() && opModeIsActive()) {
            i++;
            if (i % 10 == 0) {
                robot.fullLog("Color", robot.colorSensor.red() + " : " + robot.colorSensor.green() + " : " + robot.colorSensor.blue());
                robot.telemetry.update();
            }
        }
        robot.setMotorPowers(0);

        robot.runInchesWithEncoders(16, 16);

        robot.lowerArm();
        sleep(200);

        robot.releaseHand();
        sleep(200);

        robot.raiseArm();
        sleep(200);

        robot.runInchesWithEncoders(-6, -6);

        robot.lowerArm();
        sleep(200);

        robot.setMotorPowers(-0.75);
        i = 0;
        while (!robot.isOnLine() && opModeIsActive()) {
            i++;
            if (i % 10 == 0) {
                robot.fullLog("Color", robot.colorSensor.red() + " : " + robot.colorSensor.green() + " : " + robot.colorSensor.blue());
                robot.telemetry.update();
            }
        }
        robot.setMotorPowers(0);

        sleep(10000);

        if (tfod != null) {
            tfod.shutdown();
        }
/*
        // Step 1 - Lift arm to unblock camera
        robot.fullLog("Status", "Step 1");
        robot.lowerArm();
>>>>>>> Fixing github error

        // Step 2 - Detect correct block
        robot.fullLog("Status", "Step 2");

        // Step 3 - Move sideways (align with the block)
        robot.fullLog("Status", "Step 3");

        // Step 4 - Move to block (drive forward)
        robot.fullLog("Status", "Step 4");
        robot.runInchesWithEncoders(4, 4);

        // Step 5 - Lower arm
        robot.fullLog("Status", "Step 5");
<<<<<<< HEAD
        robot.lowerArm();
=======
        robot.raiseArm();
>>>>>>> Fixing github error

        // Step 6 - Grab block
        robot.fullLog("Status", "Step 6");

        // Step 7 - Raise arm
        robot.fullLog("Status", "Step 7");
<<<<<<< HEAD
        robot.raiseArm();
=======
        robot.lowerArm();
>>>>>>> Fixing github error

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
<<<<<<< HEAD

=======
*/
>>>>>>> Fixing github error
        // Step X - Finished
        robot.fullLog("Status", "Finished");
        robot.setMotorPowers(0);
    }
}
