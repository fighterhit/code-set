package leetcode.middle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class M535_EncodeandDecodeTinyURL {

    String dict = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final int DICT_LEN = dict.length();
    String PREFIX = "http://tinyurl.com/";
    Map<String, String> long2short = new HashMap<>();
    Map<String, String> short2long = new HashMap<>();
    Random random = new Random();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String res = long2short.get(longUrl);
        if (res != null) {
            return PREFIX + res;
        }
        StringBuilder sb = new StringBuilder();
        //产生6位短网址
        for (int i = 0; i < 6; i++) {
            sb.append(dict.charAt(random.nextInt(Integer.MAX_VALUE) % DICT_LEN));
        }
        int index = 0;
        //若产生的短网址已存在，则继续随机生成字符覆盖
        while (short2long.containsKey(sb.toString())) {
            sb.setCharAt(index, dict.charAt(random.nextInt() % DICT_LEN));
            index = (index + 1) % 6;
        }
        long2short.put(longUrl, sb.toString());
        short2long.put(sb.toString(), longUrl);
        return PREFIX + sb.toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String code = shortUrl.substring(shortUrl.lastIndexOf('/') + 1);
        String res = short2long.get(code);
        return res;
    }
}

class M535_EncodeandDecodeTinyURL2{
    Map<Integer, String> map = new HashMap();
    String host = "http://tinyurl.com/";

    public String encode(String longUrl) {
        int key = longUrl.hashCode();
        map.put(key, longUrl);
        return host+key;
    }

    public String decode(String shortUrl) {
        int key = Integer.parseInt(shortUrl.replace(host,""));
        return map.get(key);
    }
}
