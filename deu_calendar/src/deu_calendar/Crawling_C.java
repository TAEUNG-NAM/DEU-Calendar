package deu_calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Crawling_C {
	public static void main(String[] args) {
		try {
			// ũ�Ѹ� �� Door ����Ʈ �ּ�
			String url = "http://door.deu.ac.kr/Home/Index";
			
			// �� ����Ʈ�� HTML ��������
			Document doc = Jsoup.connect(url).get();
			
			// Ư�� Ŭ���� ��� ����
			Elements elements = doc.select(".info_l");
			
			for(Element element : elements) {
				System.out.println(element.text());
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
