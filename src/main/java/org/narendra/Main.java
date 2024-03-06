package org.narendra;

import org.narendra.cache.Cache;
import org.narendra.cache.exceptions.KeyNotFoundException;
import org.narendra.cache.factory.CacheFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException, KeyNotFoundException {
        Cache<String, Integer> cache = new CacheFactory<String, Integer>().getDefaultCache(4);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while(true) {
            System.out.println("/*** Enter Input ***/" +
                    "1. Put - Key, Value" +
                    "2. Get - Key");
            switch (reader.readLine()) {
                case "1": {
                    System.out.println("/*** Enter Key Value ***/");
                    String key = reader.readLine();
                    Integer value = Integer.valueOf(reader.readLine());
                    cache.put(key, value);
                    System.out.println("/*** Key Value saved ***/");
                }
                case  "2": {
                    System.out.println("/*** Enter Key ***/");
                    String key = reader.readLine();
                    Integer value = cache.get(key);
                    System.out.println("Key: " + key + "Value: " + value);
                }
            }
        }
    }
}
