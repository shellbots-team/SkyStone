package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

/**
 * Created by shell on 09/28/2019.
 */

@Autonomous(group = "Base", name = "Base: Skystone")
@Disabled
public class BaseSkystone extends BaseAutonomous {

    public static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    public static final String LABEL_FIRST_ELEMENT = "Stone";
    public static final String LABEL_SECOND_ELEMENT = "Skystone";

    private static final String VUFORIA_KEY = "AZUaS/D/////AAABmd9bAfIzFEvNp68QYPiUGWod1bqxZ/G6UuphfSOO67letJ25Ep2V5E/VfwlFektkz7sNxqkGiOXlTjCcLqVgj/eUwRxum4kkhFHDXZyjrKRb2U7xZaiv+tXxRLS52MnwFzzsUJZOZ0m9d5z3h0wBxL+yeA0bZHMKkIDdHlol+oxI+oTIlj/HtIJ0lqJMSBx40vrLg5Tx91849XDXFWtY9/CAsJbTUkYmLUniWHyolCF4UJ/mXSuyh0OMfaicPRPT4Ue0b0UKM9Z/PFOrqHeE57zO2e9zMBIG9ihPXbjF68ZZcAGfWIzA6uC3QdLwInO0DxR4iDCKqO6fCV+9EWQx8Xcde3yxdMX/E39+Sr+PpAw5";

    public VuforiaLocalizer vuforia;
    public TFObjectDetector tfod;

    @Override
    public void runOpMode() {

        super.runOpMode();

        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
            telemetry.update();
        }

        /*
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "TensorFlowCamera");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
}
