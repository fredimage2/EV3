package fr.uga.im2ag.patia.syska.statemachine.action;

import fr.uga.im2ag.patia.syska.controller.GlobalController;
import fr.uga.im2ag.patia.syska.statemachine.StateMachine;

public class DrivingForwardAction extends Action {
	
	public DrivingForwardAction(StateMachine fsm,GlobalController controller) {
		super(fsm,controller);
	}
	
	public void run() {
		this.getController().log("On roule jusqu'� toucher.");
		this.getController().getDrive().forward();
	}
	
	public Action needsToBeReplaced() {
		Action ret = null;
		if (this.getFsm().isGloballyIterrupted())
			return new TerminatingAction(this);
		if (this.getController().getTouchSensor().isTouching())
			return new SizingAction(this);
		return ret;
	}
	
	/* 
	 *  Only true return in ending actions : terminate 
	 */
	public boolean needsGlobalEnding() {
		return false;
	}


}
