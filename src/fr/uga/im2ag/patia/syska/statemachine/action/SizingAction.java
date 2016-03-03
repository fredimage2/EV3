package fr.uga.im2ag.patia.syska.statemachine.action;

public class SizingAction  extends Action {
	
	public SizingAction(Action leapFrogCreatingAction) {
		super(leapFrogCreatingAction);
	}
	
	public void run() {
		this.getController().log("On touche ; on saisit.");
		this.getController().getDrive().stop();
		b_Action_First = false;
	}
	
	public Action needsToBeReplaced() {
		Action ret = null;
		if (this.getFsm().isGloballyIterrupted())
			return new TerminatingAction(this);
		if (!b_Action_First)
			return new TerminatingAction(this); // as a beginning
		return ret;
	}
	
	/* 
	 *  Only true return in ending actions : terminate 
	 */
	public boolean needsGlobalEnding() {
		return false;
	}
}
