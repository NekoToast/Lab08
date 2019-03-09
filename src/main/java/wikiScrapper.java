import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Scanner;

public class wikiScrapper {
    public static void main(String[] args) throws IOException{
        String url = "http://pl.wikipedia.org/wiki/";
        System.out.println("Enter a name of an article to find on Wikipedia(use underscore(_) as space)");
        Scanner sc = new Scanner(System.in);
        String artName = sc.nextLine();
        Document doc = null;

        url+= artName;
        try {
             doc = Jsoup.connect(url).get();
            System.out.println(("Scrapping from " + url));
        }catch (HttpStatusException e)
        {
            System.out.println("Couldn't find article with that name");
        }

        Elements scrapNames = doc.select("h1#firstHeading");
         Elements artText = doc.select("div#bodyContent, p:gt(0)");
        Elements externalLinks = doc.select("a.external.text");

        System.out.println("Article name:"+scrapNames.text());
        System.out.println("Article text:");
                for (Element text : artText) {
                    System.out.println(text.text());
                }
        System.out.println("External links:" + externalLinks.size());
        for (Element link : externalLinks) {
            System.out.println(link.attr("abs:href") + "<-  "+link.text());
        }
    }
}