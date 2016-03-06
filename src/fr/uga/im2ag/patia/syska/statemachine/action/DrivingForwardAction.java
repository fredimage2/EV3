package fr.uga.im2ag.patia.syska.statemachine.action;

import fr.uga.im2ag.patia.syska.controller.GlobalController;

public class DrivingForwardAction extends Action {
	
	public DrivingForwardAction(GlobalController controller) {
		super(controller);
	}
	
	public void run() {
		this.getController().log("On roule jusqu'à toucher.");
		this.getController().getDrive().forward();
	}
	
	public Action needsToBeReplaced() {
		Action ret = null;
		if (this.getController().isGloballyIterrupted())
			return new TerminatingAction(this.getController());
		if (this.getController().getTouchSensor().isTouching())
			return new SizingAction(this.getController());
		return ret;
	}
	
	/* 
	 *  Only true return in ending actions : terminate 
	 */
	public boolean needsGlobalEnding() {
		return false;
	}


}
