package view.events;

import java.util.EventListener;

public interface ListEventListener extends EventListener {
	public void listEventOccurred(ListEvent le);
}
