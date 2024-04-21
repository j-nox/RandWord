package words;

import java.io.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.jayway.jsonpath.JsonPath;

import java.net.URL;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;


public class Image {
    private String image;
    public Image(String word) {
        try {
            String apiURL = "https://serpapi.com/search?";
            String apiKey = "6031444458b70ef4b3fad1c9ba75cc889f8b219ea2fb4d8b8e438a891bd2b660";
            String engine = "yandex_images";
            String url = apiURL + "text=" + word + "&engine=" + engine + "&api_key=" + apiKey;
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
