package org.firstinspires.ftc.teamcode.Autonomous.Blue.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.BasicSkyStoneGrabber;

/**
 * Created by shell on 10/26/2019.
 */

@Autonomous(group = "Blue:SkyStone", name = "Blue: SkyStone: Center")
public class BlueSkyStoneCenter extends BasicSkyStoneGrabber {
	double[] leftXValues = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5494368, -0.69962454, -0.69962454, -0.87484354, -0.87484354, -0.87484354, -0.87484354, -0.89987487, -0.89987487, -0.89987487, -0.89987487, -0.9332499, -1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.24071757, 0.2740926, 0.2824364, 0.2824364, 0.2824364, 0.29078016, 0.29078016, 0.35753027, 0.74968714, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.9165624, 0.38256153, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
	double[] leftYValues = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.4326241, 0.4326241, 0.4326241, 0.4326241, 0.4326241, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.9082186, 0.9082186, 0.9082186, 0.9082186, 0.9082186, 0.8414685, 0.8414685, 0.8414685, 0.8414685, 0.8414685, 0.8414685, 0.8414685, 0.8414685, 0.38256153, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.23237382, 0.42428035, 0.42428035, 0.42428035, 0.42428035, 0.42428035, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.09052984, -0.15727994, -0.190655, -0.20734254, -0.21568629, -0.21568629, -0.21568629, -0.21568629, -0.21568629, -0.2574051, -0.33249897, -0.33249897, -0.48268673, -0.48268673, -0.49103048, -0.49103048, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.49937424, -0.5828119, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -0.6245307, -0.4576554, -0.37421778, -0.37421778, -0.37421778, -0.37421778, -0.34084272, -0.34084272, -0.14893617, -0.05715478, -0.015435967, 0.0, 0.0, 0.0, -0.15727994, -0.17396747, -0.18231122, -0.18231122, -0.190655, -0.29078016, -0.33249897, -0.34084272, -0.38256153, -0.3909053, -0.3909053, -0.49937424, -0.8080935, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -0.26574886, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.24906135, -0.2740926, -0.2740926, -0.2740926, -0.2824364, -0.2824364, -0.21568629, -0.21568629, -0.20734254, -0.19899875, -0.19899875, -0.4576554, -0.87484354, -0.71631205, -0.59115565, -0.52440554, -0.47434297, -0.44931164, -0.40759283, -0.02377973};
	double[] rightXValues = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.015435967, 0.02377973, 0.02377973, 0.02377973, 0.02377973, 0.02377973, 0.040467255, 0.040467255, 0.040467255, 0.040467255, 0.040467255, 0.040467255, 0.05715478, 0.123904884, 0.2574051, 0.30746767, 0.3241552, 0.3241552, 0.3241552, 0.3241552, 0.23237382, 0.23237382, 0.14059241, 0.14059241, 0.123904884, 0.123904884, 0.123904884, 0.123904884, 0.0988736, 0.08218607, 0.19899875, 0.507718, 0.52440554, 0.52440554, 0.52440554, 0.4159366, 0.14893617, 0.0070921956, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
	double[] time = new double[]{171.03809, 33.729743, 42.479067, 30.305784, 30.453597, 33.646358, 31.182295, 32.109066, 39.449275, 29.246253, 32.58193, 85.39954, 64.459955, 25.309117, 35.394847, 28.809898, 31.151045, 30.979274, 29.861774, 34.051827, 26.165315000000003, 32.480159, 29.022659, 36.687868, 60.476725, 66.758288, 44.931515, 29.727191, 31.674743, 59.230005000000006, 45.32938, 35.758753999999996, 48.689537, 28.371147999999998, 28.544118, 32.88667, 62.271933000000004, 56.509746, 47.611671, 28.569795, 45.759796, 52.972349, 34.422243, 26.707867, 40.622348, 28.940627, 26.19568, 26.325367, 32.70542, 33.000941, 51.670006, 45.686619, 44.121879, 25.549221, 28.395522999999997, 25.946095999999997, 26.298909, 27.717658999999998, 34.335941, 50.662401, 25.437502000000002, 42.869327999999996, 26.770315, 34.251202, 34.116774, 52.047661000000005, 71.161934, 27.537606999999998, 26.720263000000003, 29.377451, 28.10969, 29.045993, 26.949324999999998, 31.660420000000002, 28.708285, 25.412972, 25.884742, 23.139846, 33.640367999999995, 32.082764, 24.009273, 40.755577, 97.311989, 39.681463, 27.549222, 24.304846, 26.960472, 25.791097, 41.581149999999994, 24.413753, 27.001669, 31.566045000000003, 23.448439, 28.306044999999997, 32.428909, 24.133128, 66.85672500000001, 62.785163000000004, 31.152451000000003, 29.419586000000002, 26.323388, 25.690055, 27.191409, 27.2612, 24.412085, 25.2087, 26.178127999999997, 26.589689999999997, 37.945056, 36.307296, 23.751357000000002, 62.769485, 75.979227, 27.437555, 30.891045, 25.828805, 25.810053999999997, 37.384586999999996, 25.939951, 23.898544, 38.713338, 27.561929, 33.410523999999995, 31.817295, 32.431825999999994, 41.444327, 27.523441, 28.311929, 37.866202, 27.227815, 24.503908000000003, 26.190784, 31.77792, 44.8799, 23.496252, 24.470003000000002, 24.578492, 24.017242, 25.112399, 30.890889, 32.921618, 31.264431000000002, 42.709067000000005, 26.721460999999998, 30.718753000000003, 40.764274, 24.224273, 23.85469, 24.564898000000003, 26.870003, 54.669484999999995, 27.631148, 24.491512, 24.398647999999998, 23.922553999999998, 28.312243000000002, 27.94719, 30.883544999999998, 37.359379, 68.56407, 67.14026700000001, 36.234483, 36.651253000000004, 24.701721, 27.560784, 24.452399, 24.868075, 55.624900999999994, 43.126411000000004, 23.785470999999998, 42.503285000000005, 26.866773, 66.63594400000001, 31.110837, 64.12604800000001, 28.743023, 26.171929, 23.939742, 23.421044, 20.94021, 26.959325, 24.197763000000002, 23.723805000000002, 25.276565, 33.169275, 32.547034000000004, 23.874846, 22.797503, 42.4374, 29.286097, 56.658964, 56.899225, 27.865627, 22.858388, 22.520992, 23.365940000000002, 25.444065, 26.055263, 24.667971, 23.606409, 27.473022999999998, 31.664482, 26.970264, 28.428648000000003, 28.258961999999997, 46.567505000000004, 29.019482, 24.118127, 68.584851, 30.946044999999998, 23.308804, 20.594794, 23.757659, 24.68818, 25.550107, 26.53443, 22.946773, 29.989169, 35.035419999999995, 29.961983, 23.06318, 41.607348, 24.852607, 29.569274, 25.518127, 65.915788, 34.237348, 28.123336, 22.55745, 22.743023, 23.705211000000002, 23.601096, 31.959951, 27.408753, 29.868336, 26.343805, 33.722086999999995, 22.768490999999997, 41.558806, 30.691982, 46.83688, 68.90058, 43.709431, 24.259637, 21.634898, 21.656773, 33.804222, 21.809012, 33.774899, 38.691410000000005, 31.544378, 28.510837000000002, 23.92245, 41.557921, 40.894847999999996, 30.710212000000002, 63.069173000000006, 38.4574, 39.331565999999995, 37.29542, 31.337138, 37.501618, 37.641096999999995, 44.948910000000005, 36.804848, 37.828285, 60.544746, 42.793337, 22.864013, 39.256254, 23.642659, 24.019794, 23.904377, 36.31167, 21.811773, 24.552971, 22.648908, 29.446149000000002, 28.532399, 22.08344, 48.604171, 35.149899999999995, 41.977661, 35.055368, 22.751981, 22.712971, 40.251774999999995, 24.60594, 21.485627, 22.051669, 24.477034000000003, 21.601304000000003, 27.467034, 23.981878000000002, 23.718388, 24.409845999999998, 41.769951999999996, 24.762347000000002, 23.522971000000002, 47.92714, 42.447556999999996, 31.501409000000002, 23.935523, 20.341773, 39.101774, 23.073492, 23.030992, 23.607606, 24.869273, 23.235626999999997, 28.428701, 24.372086, 21.98297, 29.545733000000002, 23.653232, 26.593023, 22.841409, 46.914953000000004, 27.683701000000003, 47.566307, 41.795473, 53.788183000000004, 37.135264, 35.708805000000005, 23.561201, 23.79219, 44.15766, 26.991825000000002, 38.963493, 38.249275, 37.5774, 23.386513, 30.848805, 31.458754, 32.886306, 24.376096, 22.958232, 36.96297199999999, 23.581668999999998, 20.982398, 21.810471, 22.521461, 24.92568, 20.552709999999998, 23.742971, 30.591721999999997, 27.480784, 23.122971, 24.819378, 46.784327, 21.689898, 31.681565000000003, 31.02193, 32.254690000000004, 27.363544, 27.218544, 39.358754000000005, 22.755627, 24.052711000000002, 21.046773, 23.867294, 21.851148000000002, 22.632346000000002, 27.570888, 33.279534999999996, 22.339116999999998, 25.655627, 46.670682, 24.087971, 31.802347, 28.268336, 24.776981, 31.493648999999998, 27.069742, 41.20141, 21.378283, 23.203909000000003, 22.424794000000002, 24.485471, 24.20068, 23.720106, 26.20844, 31.545315999999996, 35.817452, 37.087816000000004, 44.893234, 27.055626999999998, 44.615317000000005, 31.04943, 22.985263, 20.145522999999997, 39.102921, 24.744169, 22.968335999999997, 24.610472, 23.226513, 23.747346999999998, 22.043804, 28.278024, 31.148961, 20.433335, 34.509482, 34.975889, 39.153025, 30.737190000000002, 31.779639, 28.108805, 24.135315000000002, 23.057451, 46.432661, 20.647867, 21.644325, 19.58547, 21.502137, 22.087553999999997, 25.874378, 27.219742, 32.430159, 22.955003, 43.388963000000004, 35.842191, 51.624432, 20.1412, 27.484273, 29.299794000000002, 25.647554, 21.409898000000002, 49.229328, 21.330628, 22.777137, 21.121304000000002, 23.814898, 33.938597, 43.319692, 20.475054, 22.388908999999998, 33.510472, 36.959795, 52.912296999999995, 26.231878000000002, 49.94563, 35.774483000000004, 42.316723, 37.274170000000005, 43.492817, 35.020889, 32.982555, 43.262035999999995, 37.126098000000006, 35.486774, 34.830472};

	@Override
	protected double[] getLeftXValues() {
		return leftXValues;
	}

	@Override
	protected double[] getLeftYValues() {
		return leftYValues;
	}

	@Override
	protected double[] getRightXValues() {
		return rightXValues;
	}

	@Override
	protected double[] getTime() {
		return time;
	}
}
