package fr.uga.im2ag.patia.syska.controller;

import fr.uga.im2ag.patia.syska.controller.motion.ArmClamp;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;

/**
 * Class ArmClampTest définit un test unitaire de fermeture et d'ouverture des pinces du
 * robot pendant un temps définie via la constante MOVEMENT_TEST_DURATION.
 * 
 * @author Equipe Syska - Frédéric Derue
 * @version 1.0
 *
 */
public class ArmClampTest {

	/** durée de chaque action d'ouverture et de fermeture des pinces. */
	static int MOVEMENT_TEST_DURATION = 5000;

	public static void main(String[] args) throws InterruptedException {
		ArmClamp arm = new ArmClamp(MotorPort.C);
		LCD.clear();
		System.out.println("BEGIN CLOSING ARM CLAMP");
		arm.closeArmMvt();
		Thread.sleep(MOVEMENT_TEST_DURATION);
		System.out.println("END CLOSING ARM CLAMP");

		System.out.println("BEGIN OPENING ARM CLAMP");
		arm.openArmMvt();
		Thread.sleep(MOVEMENT_TEST_DURATION);
		System.out.println("END OPENING ARM CLAMP");

		System.out.println("STOP ARM CLAMP");
		arm.stop();
		LCD.clear();
		System.out.println("EXIT");
		System.exit(0);
	}
}
