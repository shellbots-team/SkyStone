package org.firstinspires.ftc.teamcode.Autonomous.Blue.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.BasicSkyStone;

/**
 * Created by shell on 10/26/2019.
 */

@Autonomous(group = "Blue:SkyStone", name = "Blue: SkyStone: Center")
public class BlueSkyStoneCenter extends BasicSkyStone {
	@Override
	protected Color getColor() {
		return Color.BLUE;
	}

	@Override
	protected Placement getFinalPlacement() {
		return Placement.CENTER;
	}
}
