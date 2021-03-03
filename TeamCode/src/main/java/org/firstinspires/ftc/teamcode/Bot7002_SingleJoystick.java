/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
 * This file contains a configuration for Mr. Reynolds' TestBed motors/servos/sensor
 *
 * It is intended to test basic function of program parameters
 *
 * Note: this Class uses a TestHardwareTeleOp configuration file.
 *
 * You could make a copy and adjust Configuration to match your bot for use as a basic testing
 * platform.
 */


@TeleOp(name="Bot7002_SingleJoystick", group="Examples")  // @Autonomous(...) is the other common choice
//@Disabled
public class Bot7002_SingleJoystick extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    Bot7002HardwareSetup robot = new Bot7002HardwareSetup();  // Use MyBotHardware Setup

    double axisY;
    double axisX;
    double leftVal;
    double rightVal;

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);                //Initialize hardware from the MyBotHardware Setup

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        /*
         * TeleOp Code Below://
         */

        while (opModeIsActive())   // run until the end of the match (driver presses STOP)
        {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            // JoyStick control
            axisY = gamepad1.left_stick_y;
            axisX = gamepad1.right_stick_x;

            //may need to change + and - to make robot turn Left and Right correctly

            leftVal = axisY - axisX;
            rightVal = axisX + axisY;
          //Code for when one or both sticks slows down the driving speed of the bot
            if (gamepad1.right_stick_button && gamepad1.left_stick_button)
            {
                robot.motorLeft.setPower(leftVal/4);
                robot.motorRight.setPower(rightVal/4);
            }
            else if(gamepad1.right_stick_button)
            {
                robot.motorLeft.setPower(leftVal/2);
                robot.motorRight.setPower(rightVal/2);
            }
            else
            {
                robot.motorLeft.setPower(leftVal);
                robot.motorRight.setPower(rightVal);
            }

            // Sweep Control - Uses dual buttons to control motor direction
            if (gamepad1.right_bumper)
            {
                robot.motorSweep.setPower(gamepad1.right_trigger); // if both Bumper + Trigger, then negative power, runs arm down
            }
            else
            {
                robot.motorSweep.setPower(-gamepad1.right_trigger);  // else trigger positive value, runs arm up
            }

            //Launcher and 2nd sweeper
            if (gamepad1.left_bumper)
            {
                robot.motorLauncher.setPower(-gamepad1.left_trigger/2); // if both Bumper + Trigger, then negative power, runs sweeper
            }
            else
            {
                robot.motorLauncher.setPower(gamepad1.left_trigger); // else trigger positive value, runs sweeper other way

            }

            //Arm Control - Uses second gamepad
            if (gamepad2.right_bumper) {
                robot.motorArm.setPower(-gamepad2.right_trigger); // if both Bumper + Trigger, then negative power, runs arm down
            } else {
                robot.motorArm.setPower(gamepad2.right_trigger);  // else trigger positive value, runs arm up
            }

            //servo lift commands
            if (gamepad1.x) //button 'x' will open
            {
                robot.Lifter.setPosition(robot.UP);
            } else if (gamepad1.a) //button 'a' will mid
            {
                robot.Lifter.setPosition(robot.MID);
            }

            else if (gamepad1.b)
            {
                robot.Lifter.setPosition(robot.DOWN);
            }

            else if (gamepad1.y)
            {
                robot.Lifter.setPosition(robot.PEG);
            }

            //servo hook commands - uses second game pad
            if (gamepad2.a) //button 'a' will close
            {
                robot.Hook.setPosition(robot.OPEN);
            } else if (gamepad2.b) //button 'b' will open
            {
                robot.Hook.setPosition(robot.CLOSED);
            }

            //servo ring pusher commands - GMPD TWO x moves it
            if (gamepad2.x) //x will push pusher forward and backwards
            {
                robot.SPusher.setPosition(robot.FORWARD);
            }else
            {
                robot.SPusher.setPosition(robot.BACKWARD);
            }
            idle(); // Always call idle() at the bottom of your while(opModeIsActive() loop
        }
    }
}
