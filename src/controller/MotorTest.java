import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.tetrix.TetrixControllerFactory;
import lejos.nxt.addon.tetrix.TetrixMotor;
import lejos.nxt.addon.tetrix.TetrixMotorController;
import lejos.nxt.addon.tetrix.TetrixServoController;

public class MotorTest {
	public static void main(String[] args) {
		// MotorController mc = new MotorController();

		// Instantiate the factory and get a Motor and servo controller. We
		// assume that there is one of
		// each daisy-chained.
		TetrixControllerFactory cf = new TetrixControllerFactory(SensorPort.S1);
		TetrixMotorController mc = cf.newMotorController();
		TetrixServoController sc = cf.newServoController();
		TetrixMotorController mc2 = cf.newMotorController();
		TetrixMotor tm1 = mc.getBasicMotor(mc.MOTOR_1);
		TetrixMotor tm2 = mc2.getBasicMotor(mc2.MOTOR_1);
		TetrixMotor tm3 = mc2.getBasicMotor(mc2.MOTOR_2);
		
		tm1.setPower(13);
		tm2.setPower(15);
		tm3.setPower(100);
				
		tm2.forward();
		tm3.forward();
		Button.waitForAnyPress();
		
		tm2.stop();
		tm3.stop();
		
		Button.waitForAnyPress();
	}
}
