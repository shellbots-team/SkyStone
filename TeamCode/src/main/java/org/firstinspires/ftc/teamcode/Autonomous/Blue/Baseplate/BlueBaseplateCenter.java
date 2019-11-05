package org.firstinspires.ftc.teamcode.Autonomous.Blue.Baseplate;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.BasicBaseplate;

/**
 * Created by shell on 10/26/2019.
 */

@Autonomous(group = "Blue:Baseplate", name = "Blue: Baseplate: Center")
public class BlueBaseplateCenter extends BasicBaseplate {
	@Override
	protected Color getColor() {
		return Color.BLUE;
	}

	@Override
	protected Placement getFinalPlacement() {
		return Placement.CENTER;
	}
}