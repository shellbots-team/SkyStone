package org.firstinspires.ftc.teamcode.Autonomous.Blue.Foundation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.BasicFoundationTurn;

/**
 * Created by shell on 02/21/2020.
 */

@Autonomous(group = "Blue:Foundation", name = "Blue: Foundation: Turn: Wall")
public class BlueFoundationTurnWall extends BasicFoundationTurn {
	@Override
	protected Color getColor() {
		return Color.BLUE;
	}
}
