
/**
 * This file illustrates the concept of driving up to a line and then stopping.
 * The code is structured as a LinearOpMode
 *
 * The code shows using two possible different light sensors:
 *   The sensor shown in this code is a MR Optical Distance Sensor (called "sensor_ods")
 *
 *   Setting the correct WHITE_THRESHOLD value is key to stopping correctly.
 *   This should be set half way between the light and dark values.
 *   These values can be read on the screen once the OpMode has been INIT, but before it is STARTED.
 *   Move the sensor on and off the white line and note the min and max readings.
 *   Edit this code to make WHITE_THRESHOLD half way between the min and max.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Bot7002_Autonomous_Test", group="Examples")
//@Disabled
public class Bot7002_Autonomous_Test extends LinearOpMode {

    //reset runtime counter
    private ElapsedTime runtime = new ElapsedTime();
    /* //////////
    //Declare OpMode members. && (robot.lightSensor.getLightDetected() < WHITE_THRESHOLD)
    *////////////

    //motors
    Bot7002HardwareSetup robot = new Bot7002HardwareSetup();  // Use MyBotHardware Setup

    //sensors
    OpticalDistanceSensor lightSensor;   //  Modern Robotics ODS sensor

    //variables
    static final double     WHITE_THRESHOLD = 0.5;  // spans between 0.1 - 0.5 from dark to light
    static final double     APPROACH_SPEED  = 0.7;  // adjust to desired motor speed ( 0.0 - 1.0 )

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);                //Initialize hardware from the MyBotHardware Setup

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        // Will display Light Level while waiting
        // Abort this loop is started or stopped.
        while (!(isStarted() || isStopRequested())) {

            // Display the light level while we are waiting to start
            telemetry.addData("Light Level", lightSensor.getLightDetected());
            telemetry.update();
            idle();
        }

        // Once Start is pressed the robot begins moving forward, and then enters while loop looking for a white line threshold.
        robot.motorLeft.setPower(APPROACH_SPEED);
        robot.motorRight.setPower(APPROACH_SPEED);

        // run until the white line is seen OR the driver presses STOP;
        // will continue to display Light Level values
        while (opModeIsActive() && (lightSensor.getLightDetected() < WHITE_THRESHOLD)) {

            // Continue displaying the light level while we are looking for the line threshold value
            telemetry.addData("Light Level",  lightSensor.getLightDetected());
            telemetry.update();
        }

        // Stop all motors
        robot.motorLeft.setPower(0);
        robot.motorRight.setPower(0);
        Thread.sleep(1000);     // pause for 1 sec to allow motors to fully stop

        //Back up behind line
        robot.motorLeft.setPower(-0.5);
        robot.motorRight.setPower(-0.5);
        Thread.sleep (1000 );
        // Stop all motors
        robot.motorLeft.setPower(0);
        robot.motorRight.setPower(0);
        Thread.sleep(1000);     // pause for 1 sec to allow motors to fully stop

        //Shot rings
        robot.Lifter.setPosition(robot.UP);
        robot.motorLauncher.setPower(1);
        Thread.sleep (1000 );
        robot.SPusher.setPosition(robot.FORWARD);
        Thread.sleep (500 );
        robot.SPusher.setPosition(robot.BACKWARD);
        robot.motorLauncher.setPower(0);
        Thread.sleep (500 );

        //Drive forward
        robot.motorLeft.setPower(0.5);
        robot.motorRight.setPower(0.5);
        Thread.sleep (500 );
        // Stop all motors
        robot.motorLeft.setPower(0);
        robot.motorRight.setPower(0);
        Thread.sleep(1000);     // pause for 1 sec to allow motors to fully stop

        // Might need to turn to pick up the goal
        //Drop wobble goal to zone b


        //Back up until line

    }
}
