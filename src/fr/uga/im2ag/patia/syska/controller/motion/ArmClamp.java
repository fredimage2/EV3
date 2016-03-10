package fr.uga.im2ag.patia.syska.controller.motion;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class ArmClamp extends EV3LargeRegulatedMotor {

	/** Equivalence d'angle en durée pour l'état des pinces ouvertes. */
	public static int OPEN_ARM_REF_DURATION = 0;

	/** Equivalence d'angle en durée pour l'état des pinces semi-ouvertes en 1/10 ms. */
	public static int HALF_OPEN_ARM_REF_DURATION = 20000;

	/** Equivalence d'angle en durée pour l'état des pinces fermées en 1/10 ms. */
	public static int CLOSE_ARM_REF_DURATION = 10000;

	/** port du moteur d'action de la pince sur le contrôleur EV3 */
	private Port port;

	/**
	 * angle d'ouverture de la pince en durée ; OPEN_ARM_REF_DURATION <= int <=
	 * CLOSE_ARM_REF_DURATION
	 */
	private long armActualAngleInDuration;

	/** indicateur d'activité : true en mouvement ; false : à l'arrêt */
	private boolean isRunning;

	///// Constructor/////////////////////////////////////////

	public ArmClamp(Port p) {
		super(p);
		this.port = p;
		this.isRunning = false;
		this.armActualAngleInDuration = 0;
	}

	public ArmClamp(Port p, int armAngleInDuration) {
		this(p);
		this.armActualAngleInDuration = armAngleInDuration;
	}

	public Port getPort() {
		return port;
	}

	public void closeArmMvt() {
		super.backward();
		this.isRunning = true;
	}

	public void openArmMvt() {
		super.forward();
		this.isRunning = true;
	}

	public void sizingArmMvt(boolean b_sizing_mouvement) {
		if (b_sizing_mouvement)
			this.closeArmMvt();
		else
			this.openArmMvt();
	}

	public void stop() {
		super.stop();
		this.isRunning = false;
		//System.out.print("duretot : " + this.armActualAngleInDuration + "\n");
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	/*
	 * Synchronisation de l'équivalence d'angle en durée de la pince par rapport
	 * à la durée de l'action écoulée depuis la dernière synchronisation.
	 * 
	 * @param actingDeltaDuration le temps écoulé de l'action en cours
	 * d'utilisation de la pince depuis la dernière synchronisation.
	 * 
	 * @param b_sizing_mouvement true si la pince se ferme ; false si elle
	 * s'ouvre.
	 * 
	 * @return true en cas de bonne exécution de l'action ; false si les limites
	 * sont dépasées. écoulée.
	 */
	public boolean synchronizeAngleInDuration(long actingDeltaDuration, boolean b_sizing_mouvement) {
		boolean ret = true;
		if (b_sizing_mouvement)
			this.armActualAngleInDuration += actingDeltaDuration;
		else
			this.armActualAngleInDuration -= actingDeltaDuration;
		//System.out.print("duretot : " + this.armActualAngleInDuration + "\n");
		//System.out.print(this.armActualAngleInDuration + " ");
		if ((this.armActualAngleInDuration < OPEN_ARM_REF_DURATION)
				|| (this.armActualAngleInDuration > CLOSE_ARM_REF_DURATION)) {
			this.stop();
			ret = false;
		}
		return ret;

	}
}
