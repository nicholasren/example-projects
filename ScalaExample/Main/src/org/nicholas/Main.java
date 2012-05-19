package org.nicholas;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by IntelliJ IDEA.
 * User: nicholasren
 * Date: 11-7-23
 * Time: 上午9:54
 */
public class Main {
    public static void main(String [] args) throws UnsupportedEncodingException {
        String encode = "%C1%AC%20%BD%D3";
        System.out.print(URLDecoder.decode(encode, "GB2312"));
    }
}
