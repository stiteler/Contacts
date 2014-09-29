package view.events;

import java.util.EventListener;

public interface SearchEventListener extends EventListener {
	public void searchEventOcurred(SearchEvent se);
}
