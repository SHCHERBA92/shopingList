package com.example.shopinglist.parserApi;

import java.util.List;
import java.util.Map;

public interface ParsingFromApi {
    Map<String, ?> getTitleAndObject(String nameQuery);

    List<?> getListProducts(String nameQuery);

    String getAuth();
}
