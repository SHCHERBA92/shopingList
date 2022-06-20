package com.example.shopinglist.parserApi;

import com.example.shopinglist.DTO.GoodsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ParsingFromSearch implements ParsingFromApi{
    private final String urlApi = "https://www.perekrestok.ru/api/customer/1.4.1.0/catalog/search/all?textQuery=";
    private final String urlSufix = "&entityTypes[]=category&entityTypes[]=product";

    @Override
    public Map<String, BigDecimal> getTitleAndObject(String nameQuery) {

        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        ObjectMapper objectMapper = new ObjectMapper();

        String strUrl = urlApi + URLEncoder.encode(nameQuery, StandardCharsets.UTF_8) + urlSufix;
        Map<String, BigDecimal> mapNameAndPrice = null;

        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("auth", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzUxMiJ9.eyJqdGkiOiIxOTkzMjVjYi1jYjg3LTQ2MWMtYmQwNi01MjM1ZjU4OTE5YTYiLCJpYXQiOjE2NTU3NTUxMzgsImV4cCI6MTY1NTc4MzkzOCwiZCI6IjVjMDI0Y2VlLTA3MmEtNDQ4Yy1iYzNkLWU2NmQxOWQwNzg2MiIsImFwaSI6IjEuNC4xLjAiLCJpcCI6Ijk1LjI0LjIwLjE5NSIsInUiOiJkZWIwMzljYy1kMzU0LTQ2ZjctYmVjMC0zYjZlN2ZjODNhMDQiLCJ0IjoxfQ.AV4CzC2xQWDNuenA3I-AjOIzQszT8ce7Sy_k6Hc1YrCa11oEQ5KiYBZFUFVG8fD61fWTuVqpTcWYnvyrgdMm_ymKAYa1y01kG678M0pKEE1FNajkDbdV9hi7kbUUPkkrlTSvuw154ejEv1tBghH_UsB8-EYdn1X8_89D46se_v44WsQ6");

            if (connection.getResponseCode() <= 299){
                inputStreamReader = new InputStreamReader(connection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);

                var rootJson = objectMapper.readTree(bufferedReader.readLine());
                var listProductJson = rootJson.findParent("textQuery")
                        .findParent("products")
                        .findParents("category");

                mapNameAndPrice = listProductJson.stream().collect(Collectors.toMap(jsonNode -> jsonNode.get("title").asText(),
                        jsonNode -> {
                            String price = jsonNode.get("medianPrice").asText();
                            StringBuffer stringBuffer = new StringBuffer(price);
                            stringBuffer.insert(price.length()-2,".");
                            BigDecimal bigDecimal = new BigDecimal(stringBuffer.toString());
                            return bigDecimal;
                        })
                );
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapNameAndPrice;
    }

    @Override
    public List<?> getListProducts(String nameQuery) {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        ObjectMapper objectMapper = new ObjectMapper();

        String strUrl = urlApi + URLEncoder.encode(nameQuery, StandardCharsets.UTF_8) + urlSufix;
        List<ProductPars> products = null;

        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("auth", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzUxMiJ9.eyJqdGkiOiIxOTkzMjVjYi1jYjg3LTQ2MWMtYmQwNi01MjM1ZjU4OTE5YTYiLCJpYXQiOjE2NTU3NTUxMzgsImV4cCI6MTY1NTc4MzkzOCwiZCI6IjVjMDI0Y2VlLTA3MmEtNDQ4Yy1iYzNkLWU2NmQxOWQwNzg2MiIsImFwaSI6IjEuNC4xLjAiLCJpcCI6Ijk1LjI0LjIwLjE5NSIsInUiOiJkZWIwMzljYy1kMzU0LTQ2ZjctYmVjMC0zYjZlN2ZjODNhMDQiLCJ0IjoxfQ.AV4CzC2xQWDNuenA3I-AjOIzQszT8ce7Sy_k6Hc1YrCa11oEQ5KiYBZFUFVG8fD61fWTuVqpTcWYnvyrgdMm_ymKAYa1y01kG678M0pKEE1FNajkDbdV9hi7kbUUPkkrlTSvuw154ejEv1tBghH_UsB8-EYdn1X8_89D46se_v44WsQ6");

            if (connection.getResponseCode() <= 299){
                inputStreamReader = new InputStreamReader(connection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);

                var rootJson = objectMapper.readTree(bufferedReader.readLine());
                var listProductJson = rootJson.findParent("textQuery")
                        .findParent("products")
                        .findParents("category");

                products = listProductJson.stream().map(jsonNode -> {
                    String name = jsonNode.get("title").asText();

                    String price = jsonNode.get("medianPrice").asText();
                    StringBuffer stringBuffer = new StringBuffer(price);
                    stringBuffer.insert(price.length()-2,".");
                    BigDecimal bigDecimal = new BigDecimal(stringBuffer.toString());

                    String img = String.format(jsonNode.findValue("image").get("cropUrlTemplate").asText(),"400x400-fit");
                    return new ProductPars(name,bigDecimal,img);
                }).collect(Collectors.toList());

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

}
