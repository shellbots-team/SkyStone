package org.firstinspires.ftc.teamcode.Autonomous.Blue.Line;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.BasicLine;

/**
 * Created by shell on 10/26/2019.
 */
@Autonomous(group = "Blue:ZLine", name = "Blue: Line: Foundation: Wall")
public class BlueFoundationLineWall extends BasicLine {
	@Override
	protected Side getStartingSide() {
		return Side.Foundation;
	}

	@Override
	protected Color getColor() {
		return Color.BLUE;
	}

	@Override
	protected Placement getFinalPlacement() {
		return Placement.WALL;
	}
}
