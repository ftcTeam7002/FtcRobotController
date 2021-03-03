
package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by TeameurekaRobotics on 12/30/2016
 *
 * This file contains an example Hardware Setup Class.
 *
 * It can be customized to match the configuration of your Bot by adding/removing hardware, and then used to instantiate
 * your bot hardware configuration in all your OpModes. This will clean up OpMode code by putting all
 * the configuration here, needing only a single instantiation inside your OpModes and avoid having to change configuration
 * in all OpModes when hardware is changed on robot.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 *
 */

public class Bot7002HardwareSetup {

   /* Declare Public OpMode members.
    *these are the null statements to make sure nothing is stored in the variables.
    */

    //motors
    public DcMotor motorLeft = null;
    public DcMotor motorRight = null;
    public DcMotor motorSweep = null;
    public DcMotor motorLauncher = null;
    public DcMotor motorArm = null;

    //servos
   // public Servo servoGrabber = null;
   // public Servo servoHandR = null;
    public Servo Lifter = null;
    public Servo Hook = null;
    public Servo SPusher = null;

    //sensors

    OpticalDistanceSensor lightSensor;   //  Modern Robotics ODS sensor

    //variables
    static final double     WHITE_THRESHOLD = 0.2;  // spans between 0.1 - 0.5 from dark to light
    static final double     APPROACH_SPEED  = 0.5;  // adjust to desired motor speed ( 0.0 - 1.0 )

    /* local OpMode members. */
    HardwareMap hwMap        = null;

    //Create and set default servo positions & MOTOR STOP variables.
    //Possible servo values: 0.0 - 1.0  For CRServo 0.5=stop greater or less than will spin in that direction
    //MOTOR VARIABLES
    final static double MOTOR_STOP = 0.0; // sets motor power to zero

    //SERVO VARIABLES
    //Launch platform
    final static double UP = 0.84;
    final static double MID = 0.75;
    final static double DOWN = 0.15;
    final static double PEG = 0.80;
    //Wobble grabber
    final static double CLOSED = 1;
    final static double OPEN = 0.2;
    //Pusher
    final static double BACKWARD = 0.88;
    final static double FORWARD = 0.78;

   /* Constructor   // this is not required as JAVA does it for you, but useful if you want to add
    * function to this method when called in OpModes.
    */
    public Bot7002HardwareSetup() {
    }

    //Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        /************************************************************
         * MOTOR SECTION
         ************************************************************/
        // Define Motors to match Robot Configuration File
        motorLeft = hwMap.dcMotor.get("motorL");
        motorRight = hwMap.dcMotor.get("motorR");
        motorSweep = hwMap.dcMotor.get("motorArm");
        motorLauncher = hwMap.dcMotor.get("motorLaunch");
        motorArm = hwMap.dcMotor.get("arm");

        // Set the drive motor directions:
        motorLeft.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        motorRight.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        motorSweep.setDirection(DcMotor.Direction.REVERSE); // Can change based on motor configuration
        motorLauncher.setDirection(DcMotor.Direction.FORWARD); // Can change based on motor configuration
        motorArm.setDirection(DcMotor.Direction.FORWARD);

        //Keep the motors from moving during initialize.
        motorLeft.setPower(MOTOR_STOP);
        motorRight.setPower(MOTOR_STOP);
        motorSweep.setPower(MOTOR_STOP);
        motorLauncher.setPower(MOTOR_STOP);
        motorArm.setPower(MOTOR_STOP);

        // Set motors to run USING or WITHOUT encoders
        // Depending upon your configuration and use
        motorSweep.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLauncher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        /************************************************************
         * SERVO SECTION
         ************************************************************/
        // Define Motors to match Robot Configuration File
        Lifter = hwMap.servo.get("Lifter");
        Lifter.setPosition(DOWN);

        Hook = hwMap.servo.get("Hook");
        Hook.setPosition(FORWARD);

        SPusher = hwMap.servo.get("SPusher");
        SPusher.setPosition(BACKWARD);
        /************************************************************
         * SENSOR SECTION
         ************************************************************/
        //Define sensors
       // gyro = hwMap.gyroSensor.get("gyro");

        // get a reference to our Light Sensor object.
        lightSensor = hwMap.opticalDistanceSensor.get("sensor_ods");  // Primary MR ODS sensor.

        // turn on LED of light sensor.
        lightSensor.enableLed(true);
   }


}

