package project.woori_saza.controller;

import io.swagger.annotations.ApiOperation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/validation")
    public Object validateUrl(@RequestParam String sublink) {
        UriComponents uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("www.virustotal.com")
                .port(443)
                .path("vtapi/v2/url/report")
                .queryParam("apikey", "68614fc23b4200870247adf446056257420f8c45e525776827cf027c589e0cee")
                .queryParam("resource", sublink + "com")
                .queryParam("allinfo", false)
                .queryParam("scan", 0)
                .build();
        return restTemplate.getForObject(uri.toUri(), Map.class);
    }

    @GetMapping("/metaimage")
    public String getMetaImage(@RequestParam String url) {
        try {
            int index = url.indexOf('?');
            Document doc;
            if (index > 0) {
                String prefix = url.substring(0, index + 1);
                String queryString = url.substring(index + 1);
                queryString = URLEncoder.encode(queryString, "UTF-8");
                doc = Jsoup.connect(prefix + queryString).get();
            } else {
                doc = Jsoup.connect(url).get();
            }

            return doc.select("meta[property=og:image]").get(0).attr("content");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
