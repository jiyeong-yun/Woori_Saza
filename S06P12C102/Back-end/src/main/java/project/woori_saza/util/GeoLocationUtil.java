package project.woori_saza.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class GeoLocationUtil {

    private final static String API_KEY = "Your Google Map API KEY";
    private final static String PRE_URL = "https://maps.googleapis.com/maps/api/geocode/json?address="; // + URLEncoder.encode(LOCATION, "UTF-8")+"&key="+API_KEY;

    public Double[] parseLocationToLngLat(String location) {
        try {
            URL url = new URL(PRE_URL + URLEncoder.encode(location, "UTF-8") + "&key=" + API_KEY);
            InputStream is = url.openConnection().getInputStream();

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            JSONObject jo = new JSONObject(responseStrBuilder.toString());
            JSONArray results = jo.getJSONArray("results");
            String region = null;
            String province = null;
            String zip = null;
            if (results.length() > 0) {
                JSONObject jsonObject;
                jsonObject = results.getJSONObject(0);
                Double lat = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                Double lng = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng");

                return new Double[] {lng, lat};
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
