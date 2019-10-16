package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by shell on 09/10/2019.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Basic Iterative Opmode", group="Manual")
public class TeleOp extends OpMode {

    // Declare motors/servos/variables
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
        } else if (this.gamepad1.dpad_up) {
            robot.leftArm.setPower(-0.33);
            robot.rightArm.setPower(-0.33);
        } else if(this.gamepad1.dpad_down) {
            robot.leftArm.setPower(0.33);
            robot.rightArm.setPower(0.33);
        } else {
            robot.rightArm.setPower(0);
            robot.leftArm.setPower(0);
        }


        if (this.gamepad1.right_trigger > 0.5) { // When right trigger is clicked
            robot.extendArm.setPower(0.3);
        } else if (this.gamepad1.left_trigger > 0.5) { // When left trigger is clicked
            robot.extendArm.setPower(-0.3);
        } else {
            robot.extendArm.setPower(0);
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
        double[] powers = new double[4]; // [leftX, leftY, rightX, rightY]
        powers[0] = this.gamepad1.left_stick_x;
        powers[1] = this.gamepad1.left_stick_y;
        powers[2] = this.gamepad1.right_stick_x;
        powers[3] = this.gamepad1.right_stick_y;

        double[] fp = new double[4];
        fp[0] = powers[1]+powers[0]-powers[2];
        fp[1] = powers[1]-powers[0]-powers[2];
        fp[2] = powers[1]-powers[0]+powers[2];
        fp[3] = powers[1]+powers[0]+powers[2];

        for(int i = 0; i < fp.length; i++) {
            if(fp[i] < 0.05 && fp[i] > -0.05) { fp[i] = 0.0; }
            if(fp[i] > 1) { fp[i] = 1; }
            if(fp[i] < -1) { fp[i] = -1; }
        }

        robot.setMotorPowers(fp[0], fp[2], fp[1], fp[3]);
    }

}
