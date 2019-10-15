package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by shell on 09/10/2019.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Basic Iterative Opmode", group = "Manual")
public class TeleOp extends OpMode {

    Robot robot = new Robot();

    // Creating a speed variable
    private double speed = 1.0;

    private boolean wasSpeedingUp = false;
    private boolean wasSlowingDown = false;


    /**
     * Run once after INIT is pushed
     */
    @Override
    public void init() {
        robot.init(hardwareMap, telemetry, this);
        robot.fullLog("Status", "Initialized");

    }

    /**
     * Runs constantly after INIT is pushed but before PLAY is pushed
     */
    @Override
    public void init_loop() {

    }

    /**
     * Runs once after PLAY is pushed
     */
    @Override
    public void start() {
        robot.fullLog("Status", "Playing");
    }

    @Override
    public void loop() {

        powerMotors();

        if (this.gamepad1.x) {
            robot.grabBaseplate();
        } else if (this.gamepad1.b) {
            robot.releaseBaseplate();
        } else if (this.gamepad1.y) {
        } else if (this.gamepad1.a) {
        }

        if (this.gamepad1.dpad_left) {
        } else if (this.gamepad1.dpad_right) {
        }

        if (this.gamepad1.right_trigger > 0.5) { // When right trigger is clicked
        } else if (this.gamepad1.left_trigger > 0.5) { // When left trigger is clicked
        }

        if (this.gamepad1.left_bumper && !wasSpeedingUp) {
            // Speed up
            speed += 0.1;
            wasSpeedingUp = true;
        } else if (this.gamepad1.right_bumper && !wasSlowingDown) {
            // Slow down
            speed -= 0.1;
            wasSlowingDown = true;
        }

        if (wasSpeedingUp && !this.gamepad1.left_bumper) {
            wasSpeedingUp = false;
        }

        if (wasSlowingDown && !this.gamepad1.right_bumper) {
            wasSlowingDown = false;
        }

        if (this.gamepad1.dpad_up == this.gamepad1.dpad_down) {
        } else if (this.gamepad1.dpad_up) {
        } else if (this.gamepad1.dpad_down) {
        }

        // Display information about the motors
        boolean isFullLog = getRuntime() % 250 == 0;

        robot.fullLog(isFullLog, "FrontLeft", robot.frontLeft.getPower());
        robot.fullLog(isFullLog, "FrontRight", robot.frontRight.getPower());
        robot.fullLog(isFullLog, "BackLeft", robot.backLeft.getPower());
        robot.fullLog(isFullLog, "BackRight", robot.backRight.getPower());

        robot.fullLog(isFullLog, "Status", "Running");

    }

    /**
     * Runs once after STOP is pushed
     */
    @Override
    public void stop() {
        robot.fullLog("Status", "Stopped");
    }

    private void powerMotors() {
        // New robot powering math...

        robot.setMotorPowers(0, 0, 0, 0); // fL, fR, bL, bR
    }

}
