package doubanComment;

public class Comment {
	public String bookName;
	public String isbn;
	public String id;
	public String title;
	public String name;
	public String uri;
	public String published;
	public String updated;
	public String summary;
	public int votes;
	public int rating;
	public Comment() {
		super();
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append(bookName+"\t");
		sb.append(isbn+"\t");
		sb.append(id+"\t");
		sb.append(title+"\t");
		sb.append(name+"\t");
		sb.append(uri+"\t");
		sb.append(votes+"\t");
		sb.append(rating+"\t");
		sb.append(published+"\t");
		sb.append(updated+"\t");
		sb.append(summary+"\n");
		
		return sb.toString();
	}

}
