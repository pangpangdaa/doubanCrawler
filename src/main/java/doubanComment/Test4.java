package doubanComment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class Test4 {
	
	public static void main(String[] args) throws IOException, InterruptedException{
         GetTotalComments gtc=new GetTotalComments("???", "19954788");
         gtc.getTotalComments();
	}
}

