package raf.rs.curring.primerzahtev;

import java.util.Map;
import java.util.function.Function;

public class Test {

    public static void main(String[] args) {
        Function<String,Function<String, Function<String,Function<String, Function<Map<String,String>, Request>>>>> requestCreator =
            method -> host -> port -> path -> requestParameters -> new Request(method, host, port, path, requestParameters);

        Function<String, Function<String,Function<String, Function<Map<String,String>, Request>>>> getRequestCreator = requestCreator.apply("get");


    }
}
