package org.firstinspires.ftc.teamcode.Autonomous.Red.Foundation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.BasicFoundationTurn;

/**
 * Created by shell on 02/21/2020.
 */

@Autonomous(group = "Red:Foundation", name = "Red: Foundation: Turn: Wall")
public class RedFoundationTurnWall extends BasicFoundationTurn {
	@Override
	protected Color getColor() {
		return Color.RED;
	}
}
