package controller;

import java.io.IOException;
import java.io.InputStream;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.addon.tetrix.TetrixControllerFactory;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.USBConnection;

/*
 Muhammad Naim bin Mohmad Shofi
 Universiti Putra Malaysia
 naim_shofi@yahoo.com

 This program is for Final Year Project for Bachelor of Computer Science and Information Technology.
 The title of this project is RoboShop : Shopping Robot Assistant Using Lego Mindstorm and Tetrix.
 It used J4K Library and LeJOS SDK.

 In this class, the program get the Movement ID from Kinect and execute it to the motor movement.
 */

public class MotorTest {

	public static TetrixControllerFactory cf;
	public static MainMotor mm;
	public static ArmGripper ag;
	public static BTConnection BTLink;
	public static USBConnection USBLink;
	public static InputStream dataIn;
	public static int commandValue, transmitReceived;
	public static boolean[] command = new boolean[10];
	public static int forceStop = 0;

	public static boolean[] checkCommand() {
		try {
			transmitReceived = dataIn.read();
			System.out.println(transmitReceived);

			if (transmitReceived == 1) {
				Sound.beep();
				command[0] = true;
				LCD.clear();
				LCD.drawString("Depan", 0, 0);
			}// forward

			if (transmitReceived == 2) {
				command[1] = true;
				LCD.clear();
				LCD.drawString("Belakang", 0, 0);
			}// backward

			if (transmitReceived == 3) {
				command[2] = true;
				LCD.clear();
				LCD.drawString("Kanan", 0, 0);
			}// rotate right

			if (transmitReceived == 4) {
				command[3] = true;
				LCD.clear();
				LCD.drawString("Kiri", 0, 0);
			}// rotate left

			if (transmitReceived == 5) {
				command[4] = true;
				LCD.clear();
				LCD.drawString("Arm Angkat", 0, 0);
			}// arm up

			if (transmitReceived == 6) {
				command[5] = true;
				LCD.clear();
				LCD.drawString("Arm Turun", 0, 0);
			}// arm down

			if (transmitReceived == 7) {
				command[6] = true;
				LCD.clear();
				LCD.drawString("Pusing Arm Arah Jam", 0, 0);
			}// rotate arm right

			if (transmitReceived == 8) {
				command[7] = true;
				LCD.clear();
				LCD.drawString("Pusing Arm Lawan Jam", 0, 0);
			}// rotate arm left

			if (transmitReceived == 9) {
				command[8] = true;
				LCD.clear();
				LCD.drawString("Buka Arm", 0, 0);
			}// open arm

			if (transmitReceived == 10) {
				command[9] = true;
				LCD.clear();
				LCD.drawString("Tutup Arm", 0, 0);
			}// close arm

			if (transmitReceived == 0) {
				LCD.clear();
				LCD.drawString("Berhenti", 0, 0);
				for (int i = 0; i < command.length; i++) {
					command[i] = false;
				}
			}
		}

		catch (IOException ioe) {
			System.out.println("IO Exception readInt");
		}
		return command;
	}

	public static void robotAction(boolean[] commandList) {
		// Movement part
		if (commandList[0] == true) {
			mm.moveForward();
		} else if (commandList[1] == true) {
			mm.moveBackward();
		} else if (commandList[2] == true) {
			mm.rotateRight();
		} else if (commandList[3] == true) {
			mm.rotateLeft();
		} else if (commandList[0] == false && commandList[1] == false
				&& commandList[2] == false && commandList[3] == false) {
			mm.stop();
		}

		// Arm part
		if (commandList[4] == true) {
			ag.armUp();
		} else if (commandList[5] == true) {
			ag.armDown();
		} else if (commandList[6] == true) {
			ag.rotateRight();
		} else if (commandList[7] == true) {
			ag.rotateLeft();
		} else if (commandList[8] == true) {
			ag.armOpen();
		} else if (commandList[9] == true) {
			ag.armClose();
		} else if (commandList[4] == false && commandList[5] == false
				&& commandList[6] == false && commandList[6] == false
				&& commandList[8] == false && commandList[9] == false) {
			ag.armStop();
			ag.stopRotate();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		connect();
		while (!Button.ESCAPE.isDown()) {
			robotAction(checkCommand());
		}
	}

	public static void connect() {
		System.out.println("Listening");
		cf = new TetrixControllerFactory(SensorPort.S4);
		mm = new MainMotor(cf);
		ag = new ArmGripper(cf);
		BTLink = Bluetooth.waitForConnection();
		dataIn = BTLink.openDataInputStream();
		// USBLink = USB.waitForConnection();
		// dataOut = USBLink.openDataOutputStream();
		// dataIn = USBLink.openDataInputStream();

	}// End connect
}
