package fr.uja.im2ag.patia.syska.statemachine.action;

import fr.uja.im2ag.patia.syska.controller.GlobalController;
import fr.uja.im2ag.patia.syska.statemachine.StateMachine;
import lejos.utility.Delay;

public abstract class Action {
	
	private final static int DELAY = 25;
	
	private StateMachine fsm;
	private GlobalController controller;
	
	public Action(StateMachine fsm,GlobalController controller) {
		this.fsm = fsm;
		this.controller = controller;
	}
	
	public Action(Action leapFrogCreatingAction) {
		this.fsm = leapFrogCreatingAction.getFsm();
		this.controller = leapFrogCreatingAction.getController();
	}
	
	/* 
	 *  Short run action repeated with sensor feedback and action re-orienting enabled
	 */
	public abstract void run();
	
	/* 
	 *  Action re-orienting ; will be lately replaced with state machine transition triggering 
	 */
	public abstract Action needsToBeReplaced();

	/* 
	 *  Short run action repeated with sensor feedback and action re-orienting enabled
	 */
	public abstract boolean needsGlobalEnding();

	private void delay() {
		Delay.msDelay(DELAY);
	}

	public StateMachine getFsm() {
		return fsm;
	}

	public GlobalController getController() {
		return controller;
	}
	
}
