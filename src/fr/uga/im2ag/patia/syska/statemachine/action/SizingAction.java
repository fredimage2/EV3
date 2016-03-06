package fr.uga.im2ag.patia.syska.statemachine.action;

import fr.uga.im2ag.patia.syska.controller.GlobalController;

/*
 * class SizingAction - classe primitive de saisie ; vouée à disparaître au profit de SizingActionWithDuration
 */
public class SizingAction extends Action {

	public SizingAction(GlobalController controller) {
		super(controller);
	}

	public void run() {
		this.getController().log("On touche ; on saisit.");
		this.getController().getDrive().stop();
	}

	public Action needsToBeReplaced() {
		Action ret = null;
		if (this.getController().isGloballyIterrupted())
			return new TerminatingAction(this.getController());
		if (!this.isFirstRun())
			return new TerminatingAction(this.getController()); // as a
																// beginning
		return ret;
	}

	/*
	 * Only true return in ending actions : terminate
	 */
	public boolean needsGlobalEnding() {
		return false;
	}
}
