package org.firstinspires.ftc.teamcode.Autonomous.Blue.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.BasicSkyStone;

/**
 * Created by shell on 10/26/2019.
 */
@Disabled
@Autonomous(group = "Blue:SkyStone", name = "Blue: SkyStone: Wall")
public class BlueSkyStoneWall extends BasicSkyStone {
	@Override
	protected Color getColor() {
		return Color.BLUE;
	}

	@Override
	protected Placement getFinalPlacement() {
		return Placement.WALL;
	}
}
