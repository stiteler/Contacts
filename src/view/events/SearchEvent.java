package view.events;

import java.util.EventObject;

// handles search by name/ email (all events related to searching)
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
