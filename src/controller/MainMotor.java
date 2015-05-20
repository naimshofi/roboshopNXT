package controller;

import lejos.nxt.SensorPort;
import lejos.nxt.addon.tetrix.TetrixControllerFactory;
import lejos.nxt.addon.tetrix.TetrixMotor;
import lejos.nxt.addon.tetrix.TetrixMotorController;

public class MainMotor {
	
	TetrixMotorController motorMove = null;
	TetrixMotor motorRight = null;
	TetrixMotor motorLeft = null;
	private int speed = 15;
	
	public MainMotor(TetrixControllerFactory cf){
		motorMove = new TetrixMotorController(SensorPort.S4, cf.DAISY_CHAIN_POSITION_2);
		motorRight = motorMove.getBasicMotor(motorMove.MOTOR_1);
		motorLeft = motorMove.getBasicMotor(motorMove.MOTOR_2);
		motorLeft.setReverse(true);
		setMotorSpeed();
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
		setMotorSpeed();
	}
	
	public void setMotorSpeed(){
		motorRight.setPower(speed);
		motorLeft.setPower(speed);
	}	
	
	public void moveForward(){
		motorRight.forward();
		motorLeft.forward();
	}
	
	public void moveBackward(){
		motorRight.backward();
		motorLeft.backward();
	}
	
	public void stop(){
		motorRight.stop();
		motorLeft.stop();
	}
	
	public void rotateRight(){
		motorRight.backward();
		motorLeft.forward();
	}
	
	public void rotateLeft(){
		motorRight.forward();
		motorLeft.backward();
	}
	
	
	
	

}
