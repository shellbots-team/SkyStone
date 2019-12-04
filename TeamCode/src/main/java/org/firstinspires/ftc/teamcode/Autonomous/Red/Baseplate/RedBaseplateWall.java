package org.firstinspires.ftc.teamcode.Autonomous.Red.Baseplate;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.BasicBaseplate;

/**
 * Created by shell on 10/26/2019.
 */
@Disabled
@Autonomous(group = "Red:Baseplate", name = "Red: Baseplate: Wall")
public class RedBaseplateWall extends BasicBaseplate {
	@Override
	protected Color getColor() {
		return Color.RED;
	}

	@Override
	protected Placement getFinalPlacement() {
		return Placement.WALL;
	}
}

