package org.firstinspires.ftc.teamcode.Autonomous.Red.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.BasicSkyStoneArm;

/**
 * Created by shell on 11/13/2019.
 */
@Disabled
@Autonomous(group = "Red:SkyStone", name = "Red: SkyStone")
public class RedSkyStoneWall extends BasicSkyStoneArm {
	@Override
	protected Color getColor() {
		return Color.RED;
	}

	@Override
	protected Placement getFinalPlacement() {
		return Placement.WALL;
	}
}
