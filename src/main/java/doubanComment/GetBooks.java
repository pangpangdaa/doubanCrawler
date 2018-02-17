package doubanComment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetBooks implements Runnable{
	public BufferedReader readId=new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:/Users/xuwei/Desktop/id.txt"))));
   public   BufferedWriter bw=new BufferedWriter(new FileWriter(new File("C:/Users/xuwei/Desktop/books.txt"), true));
   public static long number=0;
	public GetBooks() throws IOException {
	super();
	BufferedReader readId=new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:/Users/xuwei/Desktop/id.txt"))));
    BufferedWriter bw=new BufferedWriter(new FileWriter(new File("C:/Users/xuwei/Desktop/books.txt"), true));
     Random rand=new Random();
}

	public void run() {
		System.out.println("starting!");
		try {
			getBooks();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void getBooks() throws IOException, JSONException, InterruptedException, DocumentException{
       
    	String count=null;
       while((count=readId.readLine())!=null){
    	number++;
    	Random rand=new Random();
    	int interval=rand.nextInt(5);
    	TimeUnit.SECONDS.sleep(25+interval);
    	BufferedReader br=null;
    	StringBuilder sb=new StringBuilder();
    	StringBuilder output=new StringBuilder();
    	String line="";
    	String urlAddress="https://api.douban.com/v2/book/"+count;
    	URL url=new URL(urlAddress);
       InputStream is=url.openStream();
    	br=new BufferedReader(new InputStreamReader(is));
    	while((line=br.readLine())!=null){
    		sb.append(line);
    	}
        String jsonString =sb.toString();
        JSONObject json = new JSONObject(jsonString);
        String id=json.getString("id");
        output.append(id+"\t");
        String isbn13=json.getString("isbn13");
        output.append(isbn13+"\t");
        String title=json.getString("title");
        title=title.replaceAll("\t", " ");
        output.append(title+"\t");
        String price=json.getString("price");
        output.append(price+"\t");
        JSONObject images=json.getJSONObject("images");
        output.append(images.getString("small")+"\t");
        output.append(images.getString("medium")+"\t");
        output.append(images.getString("large")+"\t");
        String author=json.getString("author");
        if(author.length()>2){
        author=author.substring(2,author.length()-2);
        author=author.replaceAll("\t", " ");
        }
        output.append(author+"\t");
        String publisher=json.getString("publisher");
        publisher=publisher.replaceAll("\t"," ");
        publisher=publisher.replaceAll("\n", " ");
        output.append(publisher+"\t");
        JSONObject rate=json.getJSONObject("rating");
        String rating=rate.getString("average");
        output.append(rating+"\t");
        JSONArray jsonTags=json.getJSONArray("tags");
       

        List<String> tags=new ArrayList<String>();
        for(int i=0;i<jsonTags.length();i++){
        	JSONObject tag=jsonTags.getJSONObject(i);
          tags.add(tag.getString("name"));
        }
        for(int i=0;i<tags.size();i++){
        	if(i==0)output.append(tags.get(i));
        	else output.append(","+tags.get(i));
        }
        output.append("\t");
        String summary=json.getString("summary");
        summary=summary.replaceAll("\t", " ");
        summary=summary.replaceAll("\n", " ");
        output.append(summary+"\t");
        String author_intro=json.getString("author_intro");
        author_intro=author_intro.replaceAll("\t", " ");
        author_intro=author_intro.replaceAll("\n", " ");
        output.append(author_intro+"\t");
        output.append("\n");
    
        bw.write(output.toString());
        bw.flush();
        System.out.println("number "+number+" id: "+id+" "+title+" finished");
       TimeUnit.SECONDS.sleep(10);
        createXml(isbn13, title);
        
       }
       readId.close();
      
       bw.close();
        System.out.println("结束");
} 
	
	
	public static void main(String[] args) throws IOException{
		GetBooks getBooks=new GetBooks();
		new Thread(getBooks).start();
	}
	
	public void createXml(String isbn,String bookName) throws ClientProtocolException, IOException, DocumentException{
		String url="http://api.douban.com/book/subject/isbn/"+isbn+"/reviews";
		String address="C:/Users/xuwei/Desktop/xmls/"+isbn+".xml";
		DefaultHttpClient client = new DefaultHttpClient();
	    HttpGet get = new HttpGet(url);
	    HttpResponse response = client.execute(get);
	        HttpEntity entity = response.getEntity();
	        InputStream is = entity.getContent();
	        BufferedReader br=new BufferedReader(new InputStreamReader(is));
	        BufferedWriter bw=new BufferedWriter(new FileWriter(address));
	        String line=br.readLine();
	        while((line=br.readLine())!=null){
	        	bw.write(line+"\n");
	        }
	        bw.flush();
	        br.close();
	        bw.close();
	        
	        getComments(isbn, bookName, address);
	        
	}
	
	public void getComments(String isbn,String bookName,String address) throws DocumentException, IOException{
		 SAXReader reader = new SAXReader();
	       
		 BufferedWriter bw=new BufferedWriter(new FileWriter(new File("C:/Users/xuwei/Desktop/comments.txt"), true));
	            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
	            Document document = reader.read(new File(address));
	            // 通过document对象获取根节点bookstore
	            Element root = document.getRootElement();
	            List<Element> elements=root.elements("entry");
	          for(Element element:elements){
	        	  Comment comment=new Comment();
	        	  comment.setBookName(bookName);
	        	  comment.setIsbn(isbn);
	        	  Element id=element.element("id");
	        	  comment.setId(id.getStringValue());
	        	  Element title=element.element("title");
	        	  comment.setTitle(title.getStringValue());
	        	  Element author=element.element("author");
	        	  Element name=author.element("name");
	        	  comment.setName(name.getStringValue());
	        	  Element uri=author.element("uri");
	        	  comment.setUri(uri.getStringValue());
	        	  Element published=element.element("published");
	        	  comment.setPublished(published.getStringValue());
	        	  Element updated=element.element("updated");
	        	  comment.setUpdated(updated.getStringValue());
	        	  Element summary=element.element("summary");
	        	  comment.setSummary(summary.getStringValue());
	        	  Element votes=element.element("votes");
	        	  if(votes!=null){
	        	  comment.setVotes(Integer.parseInt(votes.attribute("value").getValue()));
	        	  }
	        	  Element rating=element.element("rating");
	        	  if(rating!=null){
	        	  comment.setRating(Integer.parseInt(rating.attribute("value").getValue()));
	        	  }
	        	  bw.write(comment.toString());
	        	  bw.flush();
	        	 
	        	
	          }
	          
	          bw.close();
        	  
        	  System.out.println(bookName+" "+"isbn="+isbn+" 共"+elements.size()+"条评论已存储");
        	  
	}

	
	}
      

