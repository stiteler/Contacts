package view.events;

import java.util.EventListener;
/*
 * ListEventListener is an interface implemented by any class
 * that is listening to list events for the observer pattern.
 */
public interface ListEventListener extends EventListener {
	public void listEventOccurred(ListEvent le);
}
