package fr.uga.im2ag.patia.syska.controller;

import fr.uga.im2ag.patia.syska.statemachine.StateMachine;
import fr.uga.im2ag.patia.syska.statemachine.action.DrivingForwardAction;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

/**
 * Class GlobalDriverTest définit un test unitaire de l'automate d'état jouant
 * l'action DrivingForwardAction jusqu'à toucher quelque chose qui a pour effet
 * de déclencher l'action SizingAction de fermeture des pinces puis de
 * s'arrêter.
 * 
 * @author Equipe Syska - Frédéric Derue
 * @version 1.0
 *
 */
public class GlobalDriverTest {

	public static void main(String[] args) throws InterruptedException {
		GlobalController globalController = new GlobalController(SensorPort.S2, MotorPort.B, MotorPort.D);
		LCD.clear();
		StateMachine sm = new StateMachine();
		sm.setCurrentAction(new DrivingForwardAction(sm, globalController));
		sm.executeAction();
		Thread.sleep(5000);
		LCD.clear();
		System.out.println("EXIT");
		System.exit(0);
	}
}
