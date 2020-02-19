package org.firstinspires.ftc.teamcode.Autonomous;

/**
 * Created by shell on 09/24/2019.
 */

public abstract class BasicFoundation extends BaseAutonomous {

	boolean[] upDPadInputs;
	boolean[] downDPadInputs;

	@Override
	public void initializerz() {
		upDPadInputs = getUpDPadInputs();
		downDPadInputs = getDownDPadInputs();
	}

	@Override
	public void prerun() {}

	@Override
	public void runOpMode() { super.runOpMode(); }

	@Override
	public void fakeAutonomous(int i) {

		if(upDPadInputs[i]) {
			robot.releaseBaseplate();
		} else if(downDPadInputs[i]) {
			robot.grabBaseplate();
		}

	}

	public abstract boolean[] getUpDPadInputs();
	public abstract boolean[] getDownDPadInputs();
}
