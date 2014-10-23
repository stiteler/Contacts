package view.events;

import java.util.EventListener;

/*
 * SearchEventListener is an interface implemented by any class
 * that is listening to Search events for the observer pattern.
 */
public interface SearchEventListener extends EventListener {
	public void searchEventOcurred(SearchEvent se);
}
