package fr.uja.im2ag.patia.syska.statemachine.action;

public class TerminatingAction extends Action {
	
	public TerminatingAction(Action leapFrogCreatingAction) {
		super(leapFrogCreatingAction);
	}
	
	public void run() {
		this.getController().log("STOP !!");
		this.getController().getDrive().stop();
	}
	
	public Action needsToBeReplaced() {
		return null;
	}
	
	/* 
	 *  Only true return in ending actions : TerminatingAction 
	 */
	public boolean needsGlobalEnding() {
		return true;
	}


}
