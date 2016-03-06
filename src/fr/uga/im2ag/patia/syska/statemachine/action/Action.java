package fr.uga.im2ag.patia.syska.statemachine.action;

import fr.uga.im2ag.patia.syska.controller.GlobalController;
import fr.uga.im2ag.patia.syska.statemachine.StateMachine;
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

	/** Temps d'attente tolérable. Le plus bref possible. */
	private final static int DELAY = 25;

	/** l'automate d'état en charge d'exécuter l'action. */
	private StateMachine fsm;

	/**
	 * le contrôleur du robot EV3 permettant d'exécuter des commandes mécaniques
	 * et d'interroger les capteurs physiques.
	 */
	private GlobalController controller;

	/**
	 * booléen permettant de caractériser la première exécution de l'action au
	 * niveau de l'automate.
	 */
	protected boolean b_Action_First = true;

	/*
	 * Constructeur
	 * 
	 * @param fsm l'automate d'états en charge d'exéctuter l'action.
	 * 
	 * @param controller le contrôleur 'mécanique' du robot EV3.
	 */

	public Action(StateMachine fsm, GlobalController controller) {
		this.fsm = fsm;
		this.controller = controller;
	}

	/*
	 * Constructeur d'une action à partir d'une autre action la précédant.
	 * 
	 * @param leapFrogCreatingAction l'action précédente.
	 */
	public Action(Action leapFrogCreatingAction) {
		this.fsm = leapFrogCreatingAction.getFsm();
		this.controller = leapFrogCreatingAction.getController();
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
	 * Possibilité de simuler un temps d'attente. Le plus bref possible.
	 */
	private void delay() {
		Delay.msDelay(DELAY);
	}

	/*
	 * @return l'automate d'états en charge d'exéctuter l'action.
	 */
	public StateMachine getFsm() {
		return fsm;
	}

	/*
	 * @return le contrôleur 'mécanique' du robot EV3.
	 */
	public GlobalController getController() {
		return controller;
	}
}
