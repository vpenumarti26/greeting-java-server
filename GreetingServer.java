import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalTime;

public class GreetingServer {

    public static void main(String[] args) throws Exception {

        // Create server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Create route "/"
        server.createContext("/", new GreetingHandler());

        server.start();

        System.out.println("Server started at http://localhost:8080");
    }

    static class GreetingHandler implements HttpHandler {

        public void handle(HttpExchange exchange) {

            try {
                int hour = LocalTime.now().getHour();

                String greeting;

                if (hour < 12) {
                    greeting = "Good Morning";
                } else if (hour < 18) {
                    greeting = "Good Afternoon";
                } else {
                    greeting = "Good Evening";
                }

                String response = "Hi Ram, " + greeting;

                exchange.sendResponseHeaders(200, response.length());

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}