package fr.uga.im2ag.patia.syska.statemachine;

import fr.uga.im2ag.patia.syska.statemachine.action.Action;
import lejos.hardware.Button;

/**
 * Class StateMachine définit un automate d'états StateMachine pour un robot
 * EV3.
 * 
 * <P>
 * Les états sont assimilables à des actions de très courte durée (class Action)
 * que l'automate exécute via la méthode run() au sein d'une boucle d'attente
 * active qui interroge les capteurs embarqués via la méthode
 * needsGlobalEnding()pour éventuellement prendre la décision de changer
 * d'action.
 * 
 * <P>
 * L'intérêt de cet automate est de réagir très rapidement à des indications
 * remontées par certains capteurs lors de l'exécution de certaines actions.
 * 
 * @author Equipe Syska - Frédéric Derue
 * @version 1.0
 *
 */
public class StateMachine {

	/** action courante en cours d'exécution par l'automate. */
	private Action currentAction;

	/*
	 * Constructeur de l'automate.
	 */
	public StateMachine() {
	}

	/*
	 * @return l'action courante de l'automate.
	 */
	public Action getCurrentAction() {
		return this.currentAction;
	}

	/*
	 * Changement d'action courante à faire par l'automate.
	 * 
	 * @param currentAction la nouvelle action courante.
	 */
	public void setCurrentAction(Action currentAction) {
		this.currentAction = currentAction;
	}

	/*
	 * Boucle d'attente active qui exécute des actions de très courte durée via
	 * la méthode run() tout en interrogeant les capteurs embarqués via la
	 * méthode needsGlobalEnding() pour éventuellement prendre la décision de
	 * changer d'action, cad d'état pour l'automate.
	 */
	public void executeAction() {
		do {
			Action nextAction = this.getCurrentAction().needsToBeReplaced();
			if (nextAction != null)
				this.setCurrentAction(nextAction);
			else
				this.getCurrentAction().run();
		} while (!this.currentAction.needsGlobalEnding());
	}

	/*
	 * Condition générale d'interruption de l'automate. Ici l'appui sur le
	 * bouton Escape du robot.
	 * 
	 * @return true si l'automate doit s'arrêter quelque soit l'action en cours,
	 * false sinon.
	 */
	public boolean isGloballyIterrupted() {
		return Button.ESCAPE.isDown();
	}
}
