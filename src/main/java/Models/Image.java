package words;

import com.jayway.jsonpath.JsonPath;
import java.net.URL;

public class Image {
    private String image;
    public Image(String word) {
        // Пробуем получить тематическу картику из поиска Яндекс
        try {
            String apiURL = "https://serpapi.com/search?";
            String apiKey = "6031444458b70ef4b3fad1c9ba75cc889f8b219ea2fb4d8b8e438a891bd2b660";
            String engine = "yandex_images";
            String url = apiURL + "text=" + word + "&engine=" + engine + "&api_key=" + apiKey;
            // Парсим полученный из API json
            Object json = JsonPath.read(new URL(url), "$.images_results[0].original");
            image = json.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getImage() {
        return image;
    }

}
