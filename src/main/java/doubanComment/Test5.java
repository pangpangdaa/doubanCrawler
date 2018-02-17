package doubanComment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Test5 {

	
	public static void main(String[] args) throws IOException{
		URL url=new URL("https://book.douban.com/tag/%E6%88%90%E5%8A%9F%E5%AD%A6");
		BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
		String line=null;
		while((line=br.readLine())!=null){
			System.out.println(line);
		}
	}
}
