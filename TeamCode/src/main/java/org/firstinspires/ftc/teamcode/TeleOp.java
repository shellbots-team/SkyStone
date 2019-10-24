package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by shell on 09/10/2019.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Basic Iterative Opmode", group="Manual")
public class TeleOp extends OpMode {

    // Declare motors/servos/variables
    Robot robot = new Robot();

    // Creating a moveSpeed variable
    private double moveSpeed = 1.0;
    private double armSpeed = 0.3;


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

        /**
         * Controller 1 settings
         */

        powerMotors();

        if (this.gamepad1.right_trigger > 0.5) { // When right trigger is clicked
            moveSpeed = 1.0;
        } else if (this.gamepad1.left_trigger > 0.5) { // When left trigger is clicked
            moveSpeed = 0.5;
        }

        if (this.gamepad1.left_bumper) {
        } else if (this.gamepad1.right_bumper) {
        }

        if (this.gamepad1.dpad_left) {
        } else if (this.gamepad1.dpad_right) {
        } else if (this.gamepad1.dpad_up) {
            robot.releaseBaseplate();
        } else if (this.gamepad1.dpad_down) {
            robot.grabBaseplate();
        }

        if (this.gamepad1.x) {
        } else if (this.gamepad1.b) {
        } else if (this.gamepad1.y) {
        } else if (this.gamepad1.a) {
        }

        /**
         * Controller 2 settings
         */

        if (this.gamepad2.right_trigger > 0.5) { // When right trigger is clicked
            robot.extendArm.setPower(armSpeed);
        } else if (this.gamepad2.left_trigger > 0.5) { // When left trigger is clicked
            robot.extendArm.setPower(-armSpeed);
        } else {
            robot.extendArm.setPower(0);
        }

        if (this.gamepad2.left_bumper) {
            armSpeed = 0.15;
        } else if (this.gamepad2.right_bumper) {
            armSpeed = 0.3;
        }

        if (this.gamepad2.dpad_left) {
        } else if (this.gamepad2.dpad_right) {
        } else if (this.gamepad2.dpad_up) {
            robot.leftArm.setPower(-0.35);
            robot.rightArm.setPower(-0.35);
        } else if(this.gamepad2.dpad_down) {
            robot.leftArm.setPower(0.35);
            robot.rightArm.setPower(0.35);
        } else {
            robot.rightArm.setPower(0);
            robot.leftArm.setPower(0);
        }

        if (this.gamepad2.dpad_up == this.gamepad1.dpad_down) {
        } else if (this.gamepad2.dpad_up) {
        } else if (this.gamepad2.dpad_down) {
        }

        if (this.gamepad2.x) {
        } else if (this.gamepad2.b) {
        } else if (this.gamepad2.y) {
<<<<<<< HEAD
            robot.setServoPosition(robot.rightHand, 1.00);
            robot.setServoPosition(robot.leftHand, 0.00);
=======
            robot.grabHand();
>>>>>>> Fixing github error
        } else if (this.gamepad2.a) {
            robot.setServoPosition(robot.rightHand, 0.00);
            robot.setServoPosition(robot.leftHand, 1.00);
        }

        robot.fullLog("FrontLeft", robot.frontLeft.getPower());
        robot.fullLog("FrontRight", robot.frontRight.getPower());
        robot.fullLog("BackLeft", robot.backLeft.getPower());
        robot.fullLog("BackRight", robot.backRight.getPower());
        robot.fullLog("Speed", moveSpeed);
        robot.telemetry.update();
    }

    /**
     * Runs once after STOP is pushed
     */
    @Override
    public void stop() {
        robot.setMotorPowers(0);
        robot.fullLog("Status", "Stopped");
        robot.telemetry.update();
    }

    private void powerMotors() {
        double leftX = this.gamepad1.left_stick_x;
        double leftY = -this.gamepad1.left_stick_y;
        double rightX = this.gamepad1.right_stick_x;
        double rightY = -this.gamepad1.right_stick_y;

        if((leftX > 0.2 && rightX > 0.2) || (leftX < -0.2 && rightX < -0.2)) {
            robot.setMotorPowers(leftX, -leftX, -rightX, rightX);
        } else {
            robot.setMotorPowers(leftY, rightY, leftY, rightY);
        }
    }

    private void oldPowerMotors() {
        // New robot powering math...
        double[] powers = new double[4]; // [leftX, leftY, rightX, rightY]
        powers[0] = this.gamepad1.left_stick_x;
        powers[1] = -(this.gamepad1.left_stick_y);
        powers[2] = this.gamepad1.right_stick_x;
        powers[3] = this.gamepad1.right_stick_y;

        double[] fp = new double[4];
        fp[0] = (powers[1]+powers[0]-powers[2])* moveSpeed;
        fp[1] = (powers[1]-powers[0]-powers[2])* moveSpeed;
        fp[2] = (powers[1]-powers[0]+powers[2])* moveSpeed;
        fp[3] = (powers[1]+powers[0]+powers[2])* moveSpeed;

        //double ma = Math.max(Math.max(Math.max(fp[0], fp[1]), fp[2]), fp[3]);

        for(int i = 0; i < fp.length; i++) {
            //fp[i] /= ma; // f[i] = fp[i] / ma
            if(fp[i] < 0.05 && fp[i] > -0.05) { fp[i] = 0.0; }
            if(fp[i] > 1.0) { fp[i] = 1.0; }
            if(fp[i] < -1.0) { fp[i] = -1.0; }
        }

        robot.setMotorPowers(fp[0], fp[2], fp[1], fp[3]);
    }

}
