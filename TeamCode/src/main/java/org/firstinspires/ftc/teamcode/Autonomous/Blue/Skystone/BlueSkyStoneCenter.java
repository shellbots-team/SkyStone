package org.firstinspires.ftc.teamcode.Autonomous.Blue.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.BasicSkyStoneArm;
import org.firstinspires.ftc.teamcode.Autonomous.BasicSkyStoneGrabber;

/**
 * Created by shell on 10/26/2019.
 */

@Autonomous(group = "Blue:SkyStone", name = "Blue: SkyStone: Wall")
public class BlueSkyStoneCenter extends BasicSkyStoneGrabber {
	@Override
	protected Color getColor() {
		return Color.BLUE;
	}

	@Override
	protected Placement getFinalPlacement() {
		return Placement.CENTER;
	}
}
