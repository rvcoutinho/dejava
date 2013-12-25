package test;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * TODO
 */
public class DebuggerPL implements PhaseListener {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		event.getClass();
	}

	/**
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		event.getClass();
	}

	/**
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.ANY_PHASE;
	}
}
