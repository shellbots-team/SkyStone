package org.firstinspires.ftc.teamcode.Autonomous.Red.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.BasicSkyStone;

/**
 * Created by shell on 11/13/2019.
 */

@Autonomous(group = "Red:SkyStone", name = "Red: SkyStone: Center")
public class RedSkyStoneCenter extends BasicSkyStone {
	@Override
	protected Color getColor() {
		return Color.RED;
	}

	@Override
	protected Placement getFinalPlacement() {
		return Placement.CENTER;
	}
}
