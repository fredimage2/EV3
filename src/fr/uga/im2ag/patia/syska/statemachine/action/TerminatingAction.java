package fr.uga.im2ag.patia.syska.statemachine.action;

import fr.uga.im2ag.patia.syska.controller.GlobalController;

/**
 * Class TerminatingAction définit un état final sans successeur à l'exécution
 * duquel les actions mécaniques en cours doivent être définitivement arrêtées.
 * 
 * @author Equipe Syska - Frédéric Derue
 * @version 1.0
 *
 */
public class TerminatingAction extends Action {

	/*
	 * Constructeur initial d'action.
	 * 
	 * @param controller le contrôleur 'mécanique' du robot EV3.
	 */
	public TerminatingAction(GlobalController controller) {
		super(controller);
	}
	
	/*
	 * Actions mécaniques à faire en premier lieu.
	 * Ici, arrêter tous les moteurs.
	 */
	public void preActions() {
		if (this.getController().getArmClamp().isRunning())
			this.getController().getArmClamp().stop();
		this.getController().getDrive().stop();
	}
	/*
	 * Arrêt des actions mécaniques en cours.
	 */
	public void run() {
		this.getController().log("STOP !!");
	}

	/*
	 * Il s'agit d'un état final sans successeur.
	 */
	public Action needsToBeReplaced() {
		return null;
	}

	/*
	 * Arrêt global de l'automate après avoir exécuter une fois la méthode run()
	 * pour arrêter les actions mécaniques en cours
	 */
	public boolean needsGlobalEnding() {
		return (!this.isFirstRun()) && true;
	}
}
