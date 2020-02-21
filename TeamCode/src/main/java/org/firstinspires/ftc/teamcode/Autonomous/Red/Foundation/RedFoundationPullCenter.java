package org.firstinspires.ftc.teamcode.Autonomous.Red.Foundation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.BasicFoundationPull;

/**
 * Created by shell on 10/26/2019.
 */

@Autonomous(group = "Red:Foundation", name = "Red: Foundation: Pull: Center")
public class RedFoundationPullCenter extends BasicFoundationPull {
	@Override
	protected Color getColor() {
		return Color.RED;
	}

	@Override
	protected Placement getFinalPlacement() {
		return Placement.CENTER;
	}
}
