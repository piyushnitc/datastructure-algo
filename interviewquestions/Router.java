package interviewquestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {
    private final Map<Pattern, String> routes;

    public Router() {
        this.routes = new HashMap<>();
    }

    public static void main(String[] args) {
        Router router = new Router();

        // Register routes with the same service
        // 1. static routes
        router.addRoute("/hello", "helloService");
        router.addRoute("/hello/user/1", "helloService1");
        router.addRoute("/hello/user/2", "helloService2");
        router.addRoute("/hello/user/profile/1", "helloUser1");
        router.addRoute("/hello/user/profile/2", "helloUser2");
        router.addRoute("/greet/user/1/profile1", "helloProfileService1");
        router.addRoute("/greet/user/2/profile2", "helloProfileService2");
        router.addRoute("/hi", "helloService");
        router.addRoute("/goodbye", "goodbyeService");
        router.addRoute("/farewell", "goodbyeService");
        router.addRoute("/see-you", "goodbyeService");

        //Test Routes
        System.out.println("Static Routes and Static Request Starts");
        System.out.println("=======================================");
        router.routeRequest("/hello"); // Should print "Matched path: /hello -> Service: helloService"
        router.routeRequest("/hello/user/1"); // Should print "Matched path: /greet -> Service: helloService"
        router.routeRequest("/greet/user/1/profile1"); // Should print "Matched path: /hi -> Service: helloService"

//        System.out.println("\nStatic Routes and Wild Card Request Starts");
//        System.out.println("==========================================");
//        router.routeRequest("/hello/*/1"); // Should print "Matched path: /greet -> Service: helloService"
//        router.routeRequest("/greet/*"); // Should print "Matched path: /hi -> Service: helloService"
//        router.routeRequest("/greet*"); // Should print "Matched path: /hi -> Service: helloService"
//        router.routeRequest("/greet*/profile1"); // Should print "Matched path: /hi -> Service: helloService"
//        router.routeRequest("/greet*/na"); // Should print "Matched path: /hi -> Service: helloService"


        // 2. Wildcard routes
        router.addRoute("/wild/*", "wildcardService");
        router.addRoute("/foo/*/bar*", "fooService");
        router.addRoute("/foo/*/bar/*", "fooBarService");
        router.addRoute("/foo/*/bar/baz", "fooBarBazService");
        router.addRoute("/foo/ABC/bar/baz/1", "fooBarBazService1");
        router.addRoute("/foo/ABC/bar/baz/2", "fooBarBazService2");
        router.addRoute("/foo/bar/baz/XYZ/1", "fooXYZ1");
        router.addRoute("/foo/bar/baz/XYZ/2", "fooXYZ2");

        router.addRoute("/api/*/resource/*", "apiResourceService");
        router.addRoute("/api/v1/*/details", "apiV1DetailsService");

        // Test routes
        System.out.println("\nWildCard Routes and Static Request Starts");
        System.out.println("==========================================");
        router.routeRequest("/wild/something"); // Should print "Matched path: /wild/something -> Service: wildcardService"
        router.routeRequest("/foo/xy/bar/xy"); // Should print "Matched path: /foo/xy/bar/xy -> Service: fooService"
        router.routeRequest("/foo/abc/bar/xyz"); // Should print "Matched path: /foo/abc/bar/xyz -> Service: fooService"
        router.routeRequest("/foo/abc/bar/baz"); // Should print "Matched path: /foo/abc/bar/baz -> Service: fooBarBazService"

        System.out.println("\nWildCard Routes and Wild card Request Starts");
        System.out.println("==========================================");
        router.routeRequest("/foo/*");
        router.routeRequest("/foo*");
        router.routeRequest("/foo/*/1");
        router.routeRequest("/foo/*/baz");


//        router.routeRequest("/goodbye"); // Should print "Matched path: /goodbye -> Service: goodbyeService"
//        router.routeRequest("/farewell"); // Should print "Matched path: /farewell -> Service: goodbyeService"
//        router.routeRequest("/see-you"); // Should print "Matched path: /see-you -> Service: goodbyeService"
//
//        router.routeRequest("/wild/something"); // Should print "Matched path: /wild/something -> Service: wildcardService"
          router.routeRequest("/foo/xy/bar/xy"); // Should print "Matched path: /foo/xy/bar/xy -> Service: fooService"
//        router.routeRequest("/foo/abc/bar/xyz"); // Should print "Matched path: /foo/abc/bar/xyz -> Service: fooService"
//        router.routeRequest("/foo/abc/bar/baz"); // Should print "Matched path: /foo/abc/bar/baz -> Service: fooBarBazService"
//        router.routeRequest("/api/v1/resource/123"); // Should print "Matched path: /api/v1/resource/123 -> Service: apiResourceService"
//        router.routeRequest("/api/v1/user/details"); // Should print "Matched path: /api/v1/user/details -> Service: apiV1DetailsService"
//
//        router.routeRequest("/notfound"); // Should print "No matching route found for path: /notfound"


    }

    public void addRoute(String path, String routeName) {
        // Convert the path to a regex pattern
        String regex = path.replace("*", "([^/]+)").replaceAll("/+", "/");
        Pattern pattern = Pattern.compile("^" + regex + "$");
        routes.put(pattern, routeName);
    }

    public String routeRequest(String path) {
        for (Map.Entry<Pattern, String> entry : routes.entrySet()) {
            Matcher matcher = entry.getKey().matcher(path);
            if (matcher.matches()) {
                List<String> params = new ArrayList<>();
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    params.add(matcher.group(i));
                }
                System.out.println("Matched route: " + entry.getValue() + ", Params: " + params);
                return "Matched route: " + entry.getValue() + ", Params: " + params;
            }
        }
        return "404 Not Found";
    }


    private boolean match(String routePath, String path) {
        // Convert routePath to regex
        String regexRoute = routePath.replace("*", "[^/]*").replace("/*", "(/.*)?");
        String regexPath = path.replace("*", "[^/]*").replace("/*", "(/.*)?");
        Pattern pattern = Pattern.compile("^" + regexRoute + "$");
        Matcher matcher = pattern.matcher(regexPath);
        return matcher.matches();
    }
}


/**
 * Routes registered
 *  - static    request static
 *   *  - wildcard    request static
 *  - static    request wildcard
 *       *      - /hello/my/tenis/123 (stored)      tenis
 *              - /hello/your/tenis/234             tenis2
 *
 */