/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Locale;

/**
 * This is NOT an opmode.
 * <p>
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 * <p>
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 * <p>
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class Robot {
    /* Public OpMode members. */
    public DcMotor frontLeft = null;
    public DcMotor frontRight = null;
    public DcMotor backLeft = null;
    public DcMotor backRight = null;
    public DcMotor leftArm = null;
    public DcMotor rightArm = null;
    public DcMotor extendArm = null;

    public CRServo leftGrip = null;
    public CRServo rightGrip = null;
    public CRServo leftHand = null;
    public CRServo rightHand = null;

    public ColorSensor colorSensor = null;

    public static final double MID_SERVO = 0.5;

    /* local OpMode members. */
    HardwareMap hardwareMap = null;
    Telemetry telemetry = null;
    OpMode opmode = null;
/*

port 0 - 4
port 1 - 5
port 2 - 6

 */
    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DEFAULT_DRIVE_SPEED = 1.0;
    static final double DEFAULT_TURN_SPEED = 0.5;

    /* Constructor */
    public Robot() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap hardwareMap, Telemetry telemetry, OpMode opmode) {
        // Save reference to Hardware map
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.opmode = opmode;

        // Define and Initialize Motors
        frontLeft = this.hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = this.hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = this.hardwareMap.get(DcMotor.class, "backLeft");
        backRight = this.hardwareMap.get(DcMotor.class, "backRight");
        leftArm = this.hardwareMap.get(DcMotor.class, "leftArm");
        rightArm = this.hardwareMap.get(DcMotor.class, "rightArm");
        extendArm = this.hardwareMap.get(DcMotor.class, "extendArm");

        frontLeft.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        leftArm.setDirection(DcMotor.Direction.FORWARD);
        rightArm.setDirection(DcMotor.Direction.FORWARD);
        extendArm.setDirection(DcMotor.Direction.FORWARD);

        leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set all motors to zero power
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        leftArm.setPower(0);
        rightArm.setPower(0);
        extendArm.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Define and initialize ALL installed servos.
        leftGrip = this.hardwareMap.get(CRServo.class, "leftGrip");
        rightGrip = this.hardwareMap.get(CRServo.class, "rightGrip");
        leftHand = this.hardwareMap.get(CRServo.class, "leftHand");
        rightHand = this.hardwareMap.get(CRServo.class, "rightHand");

        // Define and initialize ALL sensors
        colorSensor = this.hardwareMap.get(ColorSensor.class, "colorSensor");
    }

    public void fullLog(String caption, double value) {
        fullLog(caption, String.valueOf(value));
    }

    public void fullLog(String value) {
        fullLog("Main", value);
    }

    public void fullLog(boolean isFullLog, String caption, String value) {
        if (isFullLog) {
            fullLog(caption, value);
        } else {
            telemetry.addData(caption, value);
            telemetry.update();
        }
    }

    public void fullLog(String caption, String value) {
        Log.d("14736:" + caption, value);
        telemetry.addData(caption, value);
    }

    public void fullLogAndUpdate(String caption, String value) {
        fullLog(caption, value);
        telemetry.update();
    }

    public void powerArm(double height, double speed) {
        if (opmode instanceof LinearOpMode && !((LinearOpMode) opmode).opModeIsActive()) {
            return;
        }

        // Reset encoders
        leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int armTarget = (leftArm.getCurrentPosition() + rightArm.getCurrentPosition()) / 2
                + (int) (height * COUNTS_PER_INCH);

        leftArm.setTargetPosition(armTarget);
        rightArm.setTargetPosition(armTarget);

        leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        leftArm.setPower(Math.abs(speed));
        rightArm.setPower(Math.abs(speed));

        while ((!(opmode instanceof LinearOpMode) || ((LinearOpMode) opmode).opModeIsActive()) &&
            (leftArm.isBusy() && rightArm.isBusy())) {

            fullLog("ArmPath", String.format(Locale.US, "Runnning to %7d", armTarget));
            fullLog("ArmPath2", String.format(Locale.US, "Running at %7d :%7d", leftArm.getCurrentPosition(), rightArm.getCurrentPosition()));
            telemetry.update();
        }

        leftArm.setPower(0);
        rightArm.setPower(0);

        leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        if (opmode instanceof LinearOpMode) {
            ((LinearOpMode) opmode).sleep(250);
        }
    }

    public void lowerArm() {
            powerArm(1.15, 0.15);
        }

    public void raiseArm() {
            powerArm(-1.175, 0.2);
    }

    public void setServoPosition(CRServo crservo, double position) {
        crservo.getController().setServoPosition(crservo.getPortNumber(), position);
    }

    public void grabBaseplate() {
        setServoPosition(leftGrip, 1);
        setServoPosition(rightGrip, 0.5);
    }

    public void releaseBaseplate() {
        setServoPosition(leftGrip, 0);
        setServoPosition(rightGrip, 1);
    }

    public void turnDegreesWithEncoders(double degrees, boolean goClockwise) {
        int inchTurn = (int) (degrees/8.02);
        if (goClockwise) {
            runInchesWithEncoders(inchTurn, -inchTurn);
        } else {
            runInchesWithEncoders(-inchTurn, inchTurn);
        }

    }

    public void runInchesWithEncoders(double leftInches, double rightInches) {
        runInchesWithEncoders(leftInches, rightInches, 999, 1);
    }

    public void runInchesWithEncoders(double leftInches, double rightInches, double maxSeconds) {
        runInchesWithEncoders(leftInches, rightInches, maxSeconds, 1);
    }

    public void runInchesWithEncoders(double leftInches, double rightInches, double maxSeconds, double speed) {
        if (opmode instanceof LinearOpMode && !((LinearOpMode) opmode).opModeIsActive()) {
            return;
        }

        // Reset encoder values
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int newLeftTarget = frontLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
        int newRightTarget = frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
        frontLeft.setTargetPosition(newLeftTarget);
        backLeft.setTargetPosition(newLeftTarget);
        frontRight.setTargetPosition(newRightTarget);
        backRight.setTargetPosition(newRightTarget);

        // Turn On RUN_TO_POSITION
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        runtime.reset();
        setMotorPowers(Math.abs(speed));

        // keep looping while we are still active, and there is time left, and all motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the robot will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the robot continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.
        while ((!(opmode instanceof LinearOpMode) || ((LinearOpMode) opmode).opModeIsActive()) &&
                (runtime.seconds() < maxSeconds) &&
                (frontLeft.isBusy() && backLeft.isBusy() &&
                        frontRight.isBusy() && backRight.isBusy())) {

            fullLog( "Path1", String.format(Locale.US, "Running to %7d :%7d", newLeftTarget, newRightTarget));
            fullLog( "Path2", String.format(Locale.US, "Running at %7d :%7d and %7d :%7d",
                    frontLeft.getCurrentPosition(),
                    frontRight.getCurrentPosition(),
                    backLeft.getCurrentPosition(),
                    backRight.getCurrentPosition()));
        }

        // Stop all motion
        setMotorPowers(0);

        // Turn off RUN_TO_POSITION
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Stop all motion
        setMotorPowers(0);

        if (opmode instanceof LinearOpMode) {
            ((LinearOpMode) opmode).sleep(200);   // optional pause after each move
        }
    }

    public void setMotorPowers(double pwr) {
        setMotorPowers(pwr, pwr, pwr, pwr);
    }

    public void setMotorPowers(double fL, double fR, double bL, double bR) {
        frontLeft.setPower(fL);
        frontRight.setPower(fR);
        backLeft.setPower(bL);
        backRight.setPower(bR);
    }

    public void grabHand() {
        setServoPosition(rightHand, 1.00);
        setServoPosition(leftHand, 0.00);
    }

    public void releaseHand() {
        setServoPosition(rightHand, 0.00);
        setServoPosition(leftHand, 1.00);
    }

    public void setMotorPowersSideways(double pwr, boolean isRight) {
        if(isRight) {
            setMotorPowers(pwr, -pwr, -pwr, pwr);
        } else {
            setMotorPowers(-pwr, pwr, pwr, -pwr);
        }
    }

    public boolean isOnLine() {
        return (colorSensor.red() > 20 || colorSensor.blue() > 20);
    }

    public void driveLeft() {
        setMotorPowersSideways(1.0,false);
    }

    public void driveRight() {
        setMotorPowersSideways(1.0,true);
    }
}


