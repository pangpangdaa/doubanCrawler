package doubanComment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetTotalComments {
	//"https://book.douban.com/subject/27139942/comments/"
	public int count=0;
	public String url;
	public String bookName;
	public String bookId;
	public String address;
	public  BufferedWriter bw;
	public String href="";
	public GetTotalComments(String bookName,String bookId) throws IOException {
		super();
		this.bookId=bookId;
		this.bookName=bookName;
		this.url="https://book.douban.com/subject/";
		this.address=url+"/"+bookId+"/comments/";
		bw=new BufferedWriter(new FileWriter(new File("C:/Users/xuwei/Desktop/commentsTest.txt"), true));
	}

	public void getTotalComments() throws IOException, InterruptedException{
		Random rand=new Random();
        TimeUnit.SECONDS.sleep(rand.nextInt(8));
		try{
		 Document doc=Jsoup.connect(address+href).get();
		 Elements elements=doc.getElementsByClass("comment");
		 Iterator<Element> it=elements.iterator();
		
		
		
		 while(it.hasNext()){
			 SimpleComment sc=new SimpleComment();
			 sc.setBookId(bookId);
			 sc.setBookName(bookName);
			 Element element=it.next();
			// System.out.println(element.getElementsByClass("comment-content").first().text());
			 sc.setContent(element.getElementsByClass("comment-content").first().text());
			Iterator<Element> it1=element.getElementsByClass("comment-info").iterator();
			
			while(it1.hasNext()){
			Element element1=it1.next();
			Element author=element1.child(0);
			sc.setPersonalUrl(author.attr("href"));
			sc.setName(author.text());
			if(element1.children().size()>=3){
			Element rating=element1.child(1);
			sc.setRating(judge(rating.attr("title")));
			Element date=element1.child(2);
			sc.setDate(date.text());
			}
			else sc.setDate(element1.child(1).text());
		}
		
		if(sc.toString()!=null){
			count++;
	 
	    bw.write(sc.toString());

	    bw.flush();
		}
	}
		 
	
		 Element page=doc.getElementsByClass("comment-paginator").iterator().next();
		if(page==null) return;
		if(page.childNodeSize()>=3){
		href=page.child(2).child(0).attr("href");
		}
		else href="";
		if(!href.equals("") && href!=null){
		
			getTotalComments();
		}
		
		else {
			bw.close();
			System.out.println(bookName+" comments save finished. TOTAL comments "+count);
		}

		 }catch(Exception e){
				return;
			}
	}
	/*
	public void getTotalComments(String href) throws IOException, InterruptedException{
		TimeUnit.SECONDS.sleep(2);
        
		Document doc;
		 if(href!=null && !"".equals(href)){
		 doc=Jsoup.connect(address+href).get();
		 }
		 else doc=Jsoup.connect(address).get();
		 Elements elements=doc.getElementsByClass("comment");
		 Iterator<Element> it=elements.iterator();
		 while(it.hasNext()){
			 SimpleComment sc=new SimpleComment();
			 sc.setBookId(bookId);
			 sc.setBookName(bookName);
			 Element element=it.next();
			// System.out.println(element.getElementsByClass("comment-content").first().text());
			 sc.setContent(element.getElementsByClass("comment-content").first().text());
			Iterator<Element> it1=element.getElementsByClass("comment-info").iterator();
			
			while(it1.hasNext()){
			Element element1=it1.next();
			Element author=element1.child(0);
			sc.setPersonalUrl(author.attr("href"));
			sc.setName(author.text());
			if(element1.children().size()>=3){
			Element rating=element1.child(1);
			sc.setRating(judge(rating.attr("title")));
			Element date=element1.child(2);
			sc.setDate(date.text());
			}
			else sc.setDate(element1.child(1).text());
			
		}
		
		if(sc.toString()!=null){
			count++;
		bw.write(sc.toString());

		bw.flush();
		}
		
	}
		 
	
		 Element page=doc.getElementsByClass("comment-paginator").iterator().next();
		
		href=page.child(2).child(0).attr("href");
	
		if(!href.equals("") && href!=null){
		
			getTotalComments(href);
		}
		
		else {
			bw.close();
			System.out.println(bookName+" comments save finished. TOTAL comments "+count);
		}
		// Iterator<Element> pageIt=page.children().iterator();
		 //while(pageIt.hasNext()){
			// pageIt.next().toString();
		 }
		 */
	
	public int judge(String rating){
		if(rating.equals("力荐")) return 5;
		else if(rating.equals("推荐")) return 4;
		else if(rating.equals("还行")) return 3;
		else if(rating.equals("较差")) return 2;
		else return 1;
	}
	

		 
		}
	


