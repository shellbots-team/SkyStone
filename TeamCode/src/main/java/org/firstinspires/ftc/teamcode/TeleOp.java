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
    private DcMotor frontLeft;
    private DcMotor frontRight;
    // private DcMotor backLeft;
    // private DcMotor backRight;

    // Creating a speed variable
    private double speed = 1.0;

    private boolean wasSpeedingUp = false;
    private boolean wasSlowingDown = false;


    /**
     * Run once after INIT is pushed
     */
    @Override
    public void init() {

        // Initialize motors/servos
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        // backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        // backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Set status
        telemetry.addData("Status", "Initialized");
        telemetry.update();

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
        telemetry.addData("Status", "Playing");
        telemetry.update();
    }

    @Override
    public void loop() {

        powerMotors();

        if (this.gamepad1.x) {
        } else if (this.gamepad1.b) {
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
        telemetry.addData("Frontleft power is ", frontLeft.getPower());
        telemetry.addData("Frontright power is ", frontRight.getPower());
       // telemetry.addData("Backleft power is ", backLeft.getPower());
       // telemetry.addData("Backright power is ", backRight.getPower());
        telemetry.addData("Status", "Running");
        telemetry.update();

    }

    /**
     * Runs once after STOP is pushed
     */
    @Override
    public void stop() {

    }

    private void powerMotors() {
        double leftPower = this.gamepad1.left_stick_y;
        double rightPower = this.gamepad1.right_stick_y;

        if(leftPower < 0.05 && leftPower > -0.05) {
            leftPower = 0;
        }

        if(rightPower < 0.05 && rightPower > -0.05) {
            rightPower = 0;
        }

        leftPower *= speed;
        rightPower *= speed;

        if (leftPower > 1) { leftPower = 1; }
        if (leftPower < -1) { leftPower = -1; }
        if (rightPower > 1) { rightPower = 1; }
        if (rightPower < -1) { rightPower = -1; }

        rightPower *= -1.0;

        frontLeft.setPower(leftPower);
        //backLeft.setPower(leftPower);

        frontRight.setPower(rightPower);
        //backRight.setPower(rightPower);

//        Log.d("Power", "Left: " + leftPower);
//        Log.d("Power", "Right: " + rightPower);
    }

}
