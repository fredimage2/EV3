package fr.uga.im2ag.patia.syska.statemachine.action;

import fr.uga.im2ag.patia.syska.controller.GlobalController;
import lejos.utility.Delay;

/**
 * Class Action définit une action exécutable par un robot EV3 via
 * GlobalController qui peut également être interprétée comme un état
 * particulier par un automate d'états StateMachine.
 * 
 * <P>
 * La partie 'mécanique' d'une action exécutable par un robot EV3 est prise en
 * charge par le contrôleur GlobalController tout comme la remontée
 * d'information par les capteurs physiques.
 * 
 * @author Equipe Syska - Frédéric Derue
 * @version 1.0
 *
 */
public abstract class Action {

	/**
	 * le contrôleur du robot EV3 permettant d'exécuter des commandes mécaniques
	 * et d'interroger les capteurs physiques.
	 */
	private GlobalController controller;

	/**
	 * booléen permettant de caractériser la première exécution de l'action au
	 * niveau de l'automate.
	 */
	private boolean b_Action_First = true;

	/*
	 * Constructeur initial d'action.
	 * 
	 * @param controller le contrôleur 'mécanique' du robot EV3.
	 */

	public Action(GlobalController controller) {
		this.controller = controller;
	}

	/*
	 * Actions mécaniques à faire en premier lieu.
	 */
	public void preActions() {
	}

	/*
	 * Actions mécaniques à faire avant de passer à l'action suivante.
	 */
	public void postActions() {
	}
	
	/*
	 * Suite d'opérations 'mécaniques' attendues. Se doit d'être d'un temps
	 * d'exécution très court.
	 */
	public abstract void run();

	/*
	 * Méthode de décision de changement d'action à partir des remontées
	 * d'information de certains capteurs embarqués.
	 */
	public abstract Action needsToBeReplaced();

	/*
	 * Condition d'interruption de l'automate particulière à l'action.
	 * 
	 * @return true si l'automate doit s'arrêter ; false sinon.
	 */
	public abstract boolean needsGlobalEnding();

	/*
	 * @return true lors de la première exécution de l'action ; false sinon.
	 */
	public boolean isFirstRun() {
		return b_Action_First;
	}

	/*
	 * @parameter b_IsFirstRun indicateur de première exécution de l'action.
	 */
	public void setIsFirstRun(boolean b_IsFirstRun) {
		b_Action_First = b_IsFirstRun;
	}

	/*
	 * @return le contrôleur 'mécanique' du robot EV3.
	 */
	public GlobalController getController() {
		return controller;
	}
}
