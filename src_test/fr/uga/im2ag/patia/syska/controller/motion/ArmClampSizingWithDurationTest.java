package fr.uga.im2ag.patia.syska.controller.motion;

import fr.uga.im2ag.patia.syska.controller.GlobalController;
import fr.uga.im2ag.patia.syska.statemachine.StateMachine;
import fr.uga.im2ag.patia.syska.statemachine.action.Action;
import fr.uga.im2ag.patia.syska.statemachine.action.SizingActionWithDuration;
import fr.uga.im2ag.patia.syska.statemachine.action.TerminatingAction;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

/**
 * Class ArmClampSizingWithDurationTest définit un test unitaire de l'automate
 * d'état jouant l'action SizingActionWithDuration pendant une durée
 * MOVEMENT_TEST_DURATION à l'issue de laquelle s'arrêter.
 * 
 * @author Equipe Syska - Frédéric Derue
 * @version 1.0
 *
 */
public class ArmClampSizingWithDurationTest {

	static int MOVEMENT_TEST_DURATION = 2000;

	public static void main(String[] args) throws InterruptedException {
		GlobalController globalController = new GlobalController(SensorPort.S2, MotorPort.B, MotorPort.D, MotorPort.C);
		LCD.clear();
		StateMachine sm = new StateMachine();
		Action lastAction = new TerminatingAction(globalController);
		Action secondAction = new SizingActionWithDuration(globalController, MOVEMENT_TEST_DURATION, lastAction,false); 
		Action firstAction = new SizingActionWithDuration(globalController, MOVEMENT_TEST_DURATION, secondAction,true); 
		sm.setCurrentAction(firstAction);
		sm.executeAction();
		Thread.sleep(15000);
		LCD.clear();
		System.out.println("EXIT");
		System.exit(0);
	}
}
