package view.events;

import java.util.EventObject;

/*
 * SearchEvent is a class that encases the information required
 * to handle events dealing with searching in our application.
 * At the moment, this event is used to handle search by name/ email
 * This can be extended in a future implementation for more advanced search
 */
public class SearchEvent extends EventObject {
	private String query;
	private SearchType searchType;
	
	public SearchEvent(Object source, String query, SearchType searchType) {
		super(source);
		this.query = query;
		this.searchType = searchType;
	}
	
	public enum SearchType {
		NAME, EMAIL;
	}

	public String getQuery() {
		return query;
	}

	public SearchType getSearchType() {
		return searchType;
	}
}
