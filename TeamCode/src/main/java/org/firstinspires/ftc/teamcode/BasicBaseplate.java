package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by shell on 09/24/2019.
 */

@Autonomous(group = "Basic", name = "Basic: Baseplate")
public class BasicBaseplate extends BaseBaseplate {

    @Override
    public void runOpMode() {

        super.runOpMode();

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
