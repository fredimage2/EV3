package fr.uga.im2ag.patia.syska.statemachine.action;

import fr.uga.im2ag.patia.syska.controller.GlobalController;

public class SizingActionWithDuration extends ActionWithDuration {

	/** Sens du mouvement des pinces : true ouverture ; false fermeture */
	private boolean b_sizing_mouvement;

	/**
	 * Durée transmise au contrôleur lors de la synchronisation précédente avec
	 * la durée de l'action en 1/10 milliseconde.
	 */
	private long lastSynchroDuration;

	/*
	 * Constructeur
	 * 
	 * @param controller le contrôleur 'mécanique' du robot EV3.
	 * 
	 * @param duration la durée de l'action.
	 * 
	 * @param nextAction l'action suivante à exécuter à la fin de la durée
	 * écoulée.
	 * 
	 * @param b_sizing true si la pince se ferme ; false si elle s'ouvre.
	 */
	public SizingActionWithDuration(GlobalController controller, int duration, Action nextAction, boolean b_sizing) {
		super(controller, duration, nextAction);
		this.b_sizing_mouvement = b_sizing;
	}

	public void run() {
		this.getController().getArmClamp().sizingArmMvt(this.b_sizing_mouvement);
	}

	public Action needsToBeReplaced() {
		Action ret = null;
		if (this.getController().isGloballyIterrupted())
			return new TerminatingAction(this.getController());
		if (this.getController().getTouchSensor().isTouching())
			return new TerminatingAction(this.getController());
		// Test de dépassement des limites de la pince et de fin de durée de
		// l'action.
		if (this.needsToBeReplacedWithDuration())
			return this.getNextAction();
		if (!this.durationSynchronizing())
			return this.getNextAction();
		return ret;
	}

	/*
	 * Actions mécaniques à faire en premier lieu. Ici, démarrer le starter et
	 * afficher un message sur le LCD.
	 */
	public void preActions() {
		super.preActions();
		this.getController()
				.log("Debut de " + (this.b_sizing_mouvement ? "la fermeture" : "l'ouverture") + " des pinces.");
	}

	/*
	 * Actions mécaniques à faire avant de passer à l'action suivante. Ici,
	 * arrêter le timer et le moteur de la pince s'il tourne.
	 */
	public void postActions() {
		super.postActions();
		if (this.getController().getArmClamp().isRunning()) {
			// this.durationSynchronizing();
			this.getController().getArmClamp().stop();
		}
	}

	/*
	 * Only true return in ending actions : terminate
	 */
	public boolean needsGlobalEnding() {
		return false;
	}

	/*
	 * Synchronisation de l'équivalence d'angle en durée de la pince par rapport
	 * à la durée de l'action écoulée depuis la dernière synchronisation.
	 * 
	 * @return true en cas de bonne exécution de l'action ; false si les limites
	 * sont dépasées.
	 */
	public boolean durationSynchronizing() {
		long lastDuration = (this.isFirstRun()) ? 0 : this.lastSynchroDuration;
		this.lastSynchroDuration = this.getActingDuration();
		long deltaSynchroDuration = this.lastSynchroDuration - lastDuration;
		//System.out.print("dure : " + deltaSynchroDuration + "\n");
		return this.getController().getArmClamp().synchronizeAngleInDuration(deltaSynchroDuration,
				this.b_sizing_mouvement);
	}
}
