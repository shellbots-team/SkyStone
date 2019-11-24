package org.firstinspires.ftc.teamcode.Autonomous.Blue.Baseplate;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.BasicBaseplate;

/**
 * Created by shell on 10/26/2019.
 */
@Disabled
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