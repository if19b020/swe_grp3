package businesslayer;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Getter
@Setter
public class MapQuest {
    public String startMapQuest(String name, String from, String to) throws IOException, JSONException, InterruptedException {
        String key = ConfigurationManager.GetConfigProperty("MapQuestKey");

        String uri = "http://www.mapquestapi.com/directions/v2/route?key="+key+"&from="+from+"&to="+to;
        uri = uri.replace(" ","%20");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject route = new JSONObject(response.body()).getJSONObject("route");
        JSONObject boundingBox = route.getJSONObject("boundingBox");

        JSONObject lr = boundingBox.getJSONObject("lr");
        JSONObject ul = boundingBox.getJSONObject("ul");


        double distance = route.getDouble("distance");
        String sessionId = route.getString("sessionId");
        double lrlat = lr.getDouble("lat");
        double lrlng = lr.getDouble("lng");
        double ullat = ul.getDouble("lat");
        double ullng = ul.getDouble("lng");


        uri = "https://www.mapquestapi.com/staticmap/v5/map?key=" +key+"&size=1000,600&session="+sessionId+"&boundingBox=" +lrlat+"," +lrlng+"," +ullat+"," +ullng;
        request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();


        HttpResponse<byte[]> imageresponse = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        byte[] imgInBytes = imageresponse.body();
        ByteArrayInputStream bis = new ByteArrayInputStream(imgInBytes);

        BufferedImage img = ImageIO.read(bis);
        ImageIO.write(img, "jpg", new File("Images/"+ name + ".jpg"));

        return String.valueOf(distance);
    }

}
