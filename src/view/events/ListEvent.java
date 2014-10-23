package view.events;

import java.util.EventObject;

/*
 * ListEvent is a class that encases all information relating to the whole
 * contact list.  At the moment handles write, restore, print list.
 */
public class ListEvent extends EventObject {
	private ListEventType listEventType;
	
	public ListEvent(Object source, ListEventType listEventType) {
		super(source);
		this.listEventType = listEventType;
	}
	
	public enum ListEventType {
		WRITE, RESTORE, PRINT;
	}

	public ListEventType getListEventType() {
		return listEventType;
	}

	public void setListEventType(ListEventType listEventType) {
		this.listEventType = listEventType;
	}
}

