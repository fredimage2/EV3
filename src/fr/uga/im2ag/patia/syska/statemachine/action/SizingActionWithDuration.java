package fr.uga.im2ag.patia.syska.statemachine.action;

import fr.uga.im2ag.patia.syska.controller.GlobalController;

public class SizingActionWithDuration extends ActionWithDuration {

	private boolean b_sizing_mouvement;
	/*
	 * Constructeur
	 * 
	 * @param controller le contrôleur 'mécanique' du robot EV3.
	 * 
	 * @param duration la durée de l'action.
	 * 
	 * @param nextAction l'action suivante à exécuter à la fin de la durée
	 * écoulée.
	 */
	public SizingActionWithDuration(GlobalController controller, int duration, Action nextAction, boolean b_sizing) {
		super(controller, duration, nextAction);
		this.b_sizing_mouvement = b_sizing;
	}

	public void run() {
		if (this.b_sizing_mouvement)
			this.getController().getArmClamp().closeArmMvt();
		else
			this.getController().getArmClamp().openArmMvt();
	}

	public Action needsToBeReplaced() {
		Action ret = null;
		if (this.getController().isGloballyIterrupted())
			return new TerminatingAction(this.getController());
		if (this.needsToBeReplacedWithDuration())
			return this.getNextAction();
		return ret;
	}
	
	/*
	 * Actions mécaniques à faire en premier lieu.
	 * Ici, démarrer le starter et afficher un message sur le LCD.
	 */
	public void preActions() {
		super.preActions();
		this.getController().log("Debut de "+(this.b_sizing_mouvement?"la fermeture":"l'ouverture")+" des pinces.");
	}
	
	/*
	 * Actions mécaniques à faire avant de passer à l'action suivante.
	 * Ici, arrêter le timer et le moteur de la pince s'il tourne.
	 */
	public void postActions() {
		super.postActions();
		if (this.getController().getArmClamp().isRunning())
			this.getController().getArmClamp().stop();
	}

	/*
	 * Only true return in ending actions : terminate
	 */
	public boolean needsGlobalEnding() {
		return false;
	}
}
