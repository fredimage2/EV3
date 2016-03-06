package fr.uga.im2ag.patia.syska.statemachine.action;

import fr.uga.im2ag.patia.syska.controller.GlobalController;
import fr.uga.im2ag.patia.syska.statemachine.StateMachine;
import lejos.utility.Timer;
import lejos.utility.TimerListener;

public abstract class ActionWithDuration extends Action implements TimerListener {

	/** Durée de l'action en millisecondes */
	private int duration;

	/** Action suivante à exécuter à la fin de la duré écoulée */
	private Action nextAction;

	/** Timer interne permettant de simuler la durée */
	protected Timer timer;
	
	/** Indicateur de fin de durée */
	private boolean b_end_duration = false;

	/*
	 * Constructeur initial d'action.
	 * 
	 * @param controller le contrôleur 'mécanique' du robot EV3.
	 * 
	 * @param duration la durée de l'action.
	 * 
	 * @param nextAction l'action suivante à exécuter à la fin de la durée
	 * écoulée.
	 */
	public ActionWithDuration(GlobalController controller, int duration, Action nextAction) {
		super(controller);
		this.duration = duration;
		this.nextAction = nextAction;
		timer = new Timer(duration, this);
		this.b_end_duration = false;
	}

	protected boolean needsToBeReplacedWithDuration() {
		return this.b_end_duration;
	}

	protected Action getNextAction() {
		return this.nextAction;
	}

	/*
	 * Actions mécaniques à faire en premier lieu. Ici, démarrer le timer.
	 */
	public void preActions() {
		timer.start();
	}

	/*
	 * Actions mécaniques à faire avant de passer à l'action suivante. Ici,
	 * arrêter le timer.
	 */
	public void postActions() {
		timer.stop();
	}

	public void timedOut() {
		System.out.println("DUREE ACTION FIN.");
		this.b_end_duration = true;
	}
}
