package doubanComment;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Test2 {
	
	public static void getComments() throws DocumentException{
		 SAXReader reader = new SAXReader();
	        Comment comment=new Comment();
	   
	            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
	            Document document = reader.read(new File("C:/Users/xuwei/Desktop/test.xml"));
	            // 通过document对象获取根节点bookstore
	            Element root = document.getRootElement();
	            List<Element> elements=root.elements("entry");
	          for(Element element:elements){
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
	        	  comment.setVotes(Integer.parseInt(votes.attribute("value").getValue()));
	        	  Element rating=element.element("rating");
	        	  comment.setRating(Integer.parseInt(rating.attribute("value").getValue()));
	        	  System.out.println(comment.toString());
	        	  
	        	
	          }
	}
  
    /**
     * @param args
     * @throws DocumentException 
     */
    public static void main(String[] args) throws DocumentException {
        // 解析books.xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        Comment comment=new Comment();
   
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            Document document = reader.read(new File("C:/Users/xuwei/Desktop/test.xml"));
            // 通过document对象获取根节点bookstore
            Element root = document.getRootElement();
            List<Element> elements=root.elements("entry");
          for(Element element:elements){
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
        	  comment.setVotes(Integer.parseInt(votes.attribute("value").getValue()));
        	  Element rating=element.element("rating");
        	  comment.setRating(Integer.parseInt(rating.attribute("value").getValue()));
        	  System.out.println(comment.toString());
        	  
        	
          }
}
}
            
         