package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.Logger;

import java.util.List;

public class ObjectDetection {

	private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
	private static final String LABEL_FIRST_ELEMENT = "Stone";
	private static final String LABEL_SECOND_ELEMENT = "Skystone";

	private static final String VUFORIA_KEY = "AZUaS/D/////AAABmd9bAfIzFEvNp68QYPiUGWod1bqxZ/G6UuphfSOO67letJ25Ep2V5E/VfwlFektkz7sNxqkGiOXlTjCcLqVgj/eUwRxum4kkhFHDXZyjrKRb2U7xZaiv+tXxRLS52MnwFzzsUJZOZ0m9d5z3h0wBxL+yeA0bZHMKkIDdHlol+oxI+oTIlj/HtIJ0lqJMSBx40vrLg5Tx91849XDXFWtY9/CAsJbTUkYmLUniWHyolCF4UJ/mXSuyh0OMfaicPRPT4Ue0b0UKM9Z/PFOrqHeE57zO2e9zMBIG9ihPXbjF68ZZcAGfWIzA6uC3QdLwInO0DxR4iDCKqO6fCV+9EWQx8Xcde3yxdMX/E39+Sr+PpAw5";

	private static final double MINIMUM_CONFIDENCE = 0.6;

	private VuforiaLocalizer vuforia;
	private TFObjectDetector tfod;

	private HardwareMap hardwareMap;
	private Logger logger;

	ObjectDetection(HardwareMap hardwareMap, Telemetry telemetry) {
		this.hardwareMap = hardwareMap;
		logger = new Logger(telemetry);

	}

	public void initializeObjectDetection() {

		initVuforia();

		if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
			initTfod();
		} else {
			logger.completeLog("Sorry!", "This device is not compatible with TFOD");
			logger.update();
		}

		/*
		 * Activate TensorFlow Object Detection before we wait for the start command.
		 * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
		 **/
		if (tfod != null) {
			tfod.activate();
		}
	}

	public boolean isSkyStone() {
		if (tfod != null) {
			// getUpdatedRecognitions() will return null if no new information is available since
			// the last time that call was made.
			List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
			if (updatedRecognitions != null) {
				logger.numberLog("# Object Detected", updatedRecognitions.size());
				int i = 0;
				for (Recognition recognition : updatedRecognitions) {
					logger.completeLog("ObjectDetection",
							"\nDetection Number " + i +
									"\nLabel " + recognition.getLabel() +
									"\nConfidence: " + recognition.getConfidence() +
									"\nLeft: " + recognition.getLeft() +
									"\nRight: " + recognition.getRight() +
									"\nTop: " + recognition.getTop() +
									"\nBottom: " + recognition.getBottom());
					i++;
				}
				logger.update();
				for (Recognition recognition : updatedRecognitions) {
					if (recognition.getLabel().equals(LABEL_SECOND_ELEMENT)) {
						logger.completeLog("ObjectDetection", "Found skystone");
						return true;
					}
				}
			}
		}
		return false;
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
		tfodParameters.minimumConfidence = MINIMUM_CONFIDENCE;
		tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
		tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
	}
}
