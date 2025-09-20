package module_3;

import java.util.HashMap;
import java.util.Map;

enum RequestMethod {
    GET, POST, PATCH, DELETE, PUT, HEAD, OPTIONS
}

interface Server {
    final Map<String, Integer> cache = new HashMap<>();

    int request(RequestMethod type, String url);
}

class ActualServer implements Server {

    @Override
    public int request(RequestMethod type, String url) {
        String key = type + ": " + url;
        if (url.isEmpty()) return 404;

        switch (type) {
            case GET:
            case HEAD:
            case OPTIONS:
                return cache.containsKey(key) ? 200 : 404;
            case POST:
            case PUT:
                cache.put(key, 201);
                return 201;
            case PATCH:
                return cache.containsKey(key) ? 300 : 404;
            case DELETE:
                cache.remove(url);
                return 204;
            default:
                return 500;
        }
    }
}

class ProxyServer implements Server {
    private final ActualServer srv = new ActualServer();

    @Override
    public int request(RequestMethod type, String url) {

        if (type != RequestMethod.DELETE && cache.containsKey(url)) {
            System.err.println("ProxyServer: getting status from cache for: " + url);
            return cache.get(url);
        }

        System.err.println("ProxyServer: sending request to ActualServer for " + url);
        int status = srv.request(type, url);

        if (type != RequestMethod.DELETE) {
            cache.put(url, status);
        } else {
            cache.remove(url);
        }

        return status;
    }
}

public class ProxyPattern {
    public static void main(String[] args) {
        Server srv = new ProxyServer();

        System.err.println("POST: HTTP Status Code: " + srv.request(RequestMethod.POST, "aston.ru"));
        System.err.println("GET: HTTP Status Code: " + srv.request(RequestMethod.GET, "aston.ru"));
        System.err.println("POST: HTTP Status Code: " + srv.request(RequestMethod.POST, "aston.ru/users/djgerah"));
        System.err.println("PATCH: HTTP Status Code: " + srv.request(RequestMethod.PATCH, "aston.ru/users/djgerah"));
        System.err.println("GET: HTTP Status Code: " + srv.request(RequestMethod.GET, "aston.ru/users/djgerah"));
        System.err.println("GET: missing page: HTTP Status Code: " + srv.request(RequestMethod.GET, ""));
        System.err.println("PATCH: missing page: HTTP Status Code: " + srv.request(RequestMethod.PATCH, ""));
        System.err.println("DELETE: HTTP Status Code: " + srv.request(RequestMethod.DELETE, "aston.ru/users/djgerah"));
        System.err.println("GET: user: HTTP Status Code: " + srv.request(RequestMethod.GET, "aston.ru/users/djgerah"));
        System.err.println("GET: HTTP Status Code: " + srv.request(RequestMethod.GET, "aston.ru"));
    }
}