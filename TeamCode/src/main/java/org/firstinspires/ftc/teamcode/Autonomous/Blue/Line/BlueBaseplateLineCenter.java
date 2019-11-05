package org.firstinspires.ftc.teamcode.Autonomous.Blue.Line;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.BasicLine;

/**
 * Created by shell on 10/26/2019.
 */

@Autonomous(group = "Blue:ZLine:Baseplate", name = "Blue: Line: Baseplate Center")
public class BlueBaseplateLineCenter extends BasicLine {
	@Override
	protected Side getStartingSide() {
		return Side.BASEPLATE;
	}

	@Override
	protected Color getColor() {
		return Color.BLUE;
	}

	@Override
	protected Placement getFinalPlacement() {
		return Placement.CENTER;
	}
}
