package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by shell on 09/24/2019.
 */

@Autonomous(group = "Complex", name = "Complex: SkyStone")
public abstract class ComplexSkyStone extends BaseAutonomous {

	@Override
	public void runOpMode() {

		super.runOpMode();

		// Setting status to "Ready to run"
		logger.statusLog(0, "Ready to run");

		// Waiting until user presses start
		waitForStart();

		// Step 1
		logger.statusLog(1, "...");

		// Step X - Finished
		logger.statusLog(0, "Finished");

	}
}
