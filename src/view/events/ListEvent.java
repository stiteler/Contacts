package view.events;

import java.util.EventObject;

// write, restore, print (handles events affecting all records)
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

