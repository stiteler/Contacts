package view.events;

import java.util.EventListener;

public interface ContactEventListener extends EventListener {
	public void contactEventOccurred(ContactEvent ce);
}
