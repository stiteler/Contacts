package view.events;

import java.util.EventListener;

/*
 * ContactEventListener is an interface implemented by any class
 * that is listening to contact events for the observer pattern.
 */
public interface ContactEventListener extends EventListener {
	public void contactEventOccurred(ContactEvent ce);
}
