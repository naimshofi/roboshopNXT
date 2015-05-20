package controller;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.tetrix.TetrixControllerFactory;
import lejos.nxt.addon.tetrix.TetrixMotor;
import lejos.nxt.addon.tetrix.TetrixMotorController;
import lejos.nxt.addon.tetrix.TetrixServo;
import lejos.nxt.addon.tetrix.TetrixServoController;

public class ArmGripper {


	TetrixMotorController motorArm = null;
	TetrixServoController servoArm = null;
	TetrixMotor motorRotate = null;	
	TetrixServo servoGrip = null;
	private int speedRotate = 10;
	
	public ArmGripper(TetrixControllerFactory cf){
		motorArm = new TetrixMotorController(SensorPort.S4, cf.DAISY_CHAIN_POSITION_1);
		servoArm = new TetrixServoController(SensorPort.S4, cf.DAISY_CHAIN_POSITION_3);
		motorRotate = motorArm.getBasicMotor(motorArm.MOTOR_1);
		servoGrip = servoArm.getServo(servoArm.SERVO_1);
		setRotateSpeed();
	}
	
	public void setSpeed(int speed){
		this.speedRotate = speed;
		setRotateSpeed();
	}
	
	public void setRotateSpeed(){
		motorRotate.setPower(speedRotate);		
	}	
	
	public void rotateRight(){
		motorRotate.forward();
	}
	
	public void rotateLeft(){
		motorRotate.backward();
	}
	
	public void stopRotate(){
		motorRotate.stop();
	}
	
	public void armUp(){
		Motor.A.backward();
		Motor.B.backward();
	}
	
	public void armDown(){
		Motor.A.forward();
		Motor.B.forward();
	}
	
	public void armStop(){
		Motor.A.stop(true);
		Motor.B.stop();
	}
}

