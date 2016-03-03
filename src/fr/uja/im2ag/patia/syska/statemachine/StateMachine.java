package fr.uja.im2ag.patia.syska.statemachine;

import fr.uja.im2ag.patia.syska.statemachine.action.Action;
import lejos.hardware.Button;

public class StateMachine {
	
	private Action currentAction;
	
	public StateMachine() {
	}
	
	public Action getCurrentAction() {
		return this.currentAction;
	}
	
	public void setCurrentAction(Action currentAction) {
		this.currentAction = currentAction;
	}

	public void executeAction() {
		do {
			Action nextAction = this.getCurrentAction().needsToBeReplaced();
			if (nextAction != null)
				this.setCurrentAction(nextAction);
			else
				this.getCurrentAction().run();
		} while (this.currentAction.needsGlobalEnding());
	}
	
	public boolean isGloballyIterrupted(){
		return Button.ESCAPE.isDown();
	}
}
