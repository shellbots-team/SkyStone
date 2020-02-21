package org.firstinspires.ftc.teamcode.Autonomous.Blue.Foundation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.BasicFoundationPull;

/**
 * Created by shell on 10/26/2019.
 */

@Autonomous(group = "Blue:Foundation", name = "Blue: Foundation: Pull: Wall")
public class BlueFoundationPullWall extends BasicFoundationPull {
	@Override
	protected Color getColor() {
		return Color.BLUE;
	}

	@Override
	protected Placement getFinalPlacement() {
		return Placement.WALL;
	}
}