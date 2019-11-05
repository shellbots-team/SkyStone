package org.firstinspires.ftc.teamcode.Autonomous.Blue.Line;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.BasicLine;

/**
 * Created by shell on 10/26/2019.
 */

@Autonomous(group = "Blue:ZLine:SkyStone", name = "Blue: Line: SkyStone Wall")
public class BlueSkyStoneLineWall extends BasicLine {
	@Override
	protected Side getStartingSide() {
		return Side.SKYSTONE;
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
