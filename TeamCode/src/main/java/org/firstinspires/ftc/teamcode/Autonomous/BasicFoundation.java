package org.firstinspires.ftc.teamcode.Autonomous;

/**
 * Created by shell on 09/24/2019.
 */

public abstract class BasicFoundation extends BaseAutonomous {

	@Override
	public void runOpMode() { super.runOpMode(); }

	@Override
	public void fakeAutonomous(int i) {

		boolean[] upDPadInputs = getUpDPadInputs();
		boolean[] downDPadInputs = getDownDPadInputs();

		if(upDPadInputs[i]) {
			robot.releaseBaseplate();
		} else if(downDPadInputs[i]) {
			robot.grabBaseplate();
		}

	}

	public abstract boolean[] getUpDPadInputs();
	public abstract boolean[] getDownDPadInputs();
}
