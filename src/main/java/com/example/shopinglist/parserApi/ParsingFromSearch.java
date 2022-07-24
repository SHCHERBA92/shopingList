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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParsingFromSearch implements ParsingFromApi {

    private final String urlApi = "https://www.perekrestok.ru/api/customer/1.4.1.0/catalog/search/all?textQuery=";
    private final String urlSufix = "&entityTypes[]=category&entityTypes[]=product";
    private String authentication;

    public ParsingFromSearch() {
        authentication = getAuth();
    }

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
            connection.setRequestProperty("auth", "Bearer " + authentication);

            connection.connect();

            if (connection.getResponseCode() == 401) {
                this.authentication = this.getAuth();

                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("auth", "Bearer " + authentication);

                connection.connect();

            }
            if (connection.getResponseCode() <= 299) {
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
                            stringBuffer.insert(price.length() - 2, ".");
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
            connection.setRequestProperty("auth", "Bearer " + authentication);

            connection.connect();

            if (connection.getResponseCode() == 401) {
                this.authentication = this.getAuth();

                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("auth", "Bearer " + authentication);

                connection.connect();

            }
            if (connection.getResponseCode() <= 299) {
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
                    stringBuffer.insert(price.length() - 2, ".");
                    BigDecimal bigDecimal = new BigDecimal(stringBuffer.toString());

                    String img = String.format(jsonNode.findValue("image").get("cropUrlTemplate").asText(), "400x400-fit");
                    return new ProductPars(name, bigDecimal, img);
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

    public String getAuth() {
        String strUrl = "https://www.perekrestok.ru/";
        URL url = null;
        String authentication = null;

        try {
            url = new URL(strUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() <= 299) {
                var allHeaders =
                        httpURLConnection.getHeaderFields();
                var cookies = allHeaders.get("Set-Cookie");
                var sessionCookie = cookies.stream().filter(s ->
                        s.contains("session")).findFirst().get();
                authentication =
                        Arrays.stream(sessionCookie.replace("session=j%3A%7B%22accessToken%22%3A%22", "!!!!!")
                                .replace("%22%2C%22refreshToken%22%3A%22", "!!!!!")
                                .split("!!!!!")).filter(s -> !
                                s.isEmpty()).collect(Collectors.toList()).get(0);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return authentication;
    }

}
