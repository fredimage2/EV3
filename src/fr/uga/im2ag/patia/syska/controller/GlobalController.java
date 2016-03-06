package fr.uga.im2ag.patia.syska.controller;

import fr.uga.im2ag.patia.syska.controller.motion.ArmClamp;
import fr.uga.im2ag.patia.syska.controller.motion.Motion;
import fr.uga.im2ag.patia.syska.controller.sensor.TouchSensor;
import lejos.hardware.Button;
import lejos.hardware.port.Port;

public class GlobalController {

	private TouchSensor touchSensor;
	private Motion drive;
	private ArmClamp armClamp;

	public GlobalController(Port sensor_port, Port left_port, Port right_port, Port armClamp_port) {
		log("Initializing Controller");

		touchSensor = new TouchSensor(sensor_port);
		drive = new Motion(left_port, right_port);
		armClamp = new ArmClamp(armClamp_port);
	}

	public TouchSensor getTouchSensor() {
		return this.touchSensor;
	}

	public Motion getDrive() {
		return this.drive;
	}
	
	public ArmClamp getArmClamp() {
		return this.armClamp;
	}

	public void log(String msg) {
		System.out.println("log>\t" + msg);
	}

	public void log(String msg, boolean carriage_return) {
		if (carriage_return)
			log(msg);
		else
			System.out.print(msg);
	}

	/*
	 * Condition immédiate d'interruption du robot. Ici l'appui sur le
	 * bouton Escape du robot.
	 * 
	 * @return true si l'automate doit s'arrêter quelque soit l'action en cours,
	 * false sinon.
	 */
	public boolean isGloballyIterrupted() {
		return Button.ESCAPE.isDown();
	}
}
