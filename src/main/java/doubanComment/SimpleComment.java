package doubanComment;

public class SimpleComment {
	private String bookName;
	private String bookId;
	private String name;
	private String personalUrl;
	public String getPersonalUrl() {
		return personalUrl;
	}
	public void setPersonalUrl(String personalUrl) {
		this.personalUrl = personalUrl;
	}
	private int rating=0;
	private String content;
	private String date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append(bookName+"\t");
		sb.append(bookId+"\t");
		sb.append(name+"\t");
		sb.append(personalUrl+"\t");
		sb.append(rating+"\t");
		sb.append(content+"\t");
		sb.append(date+"\n");
		return sb.toString();
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

}
