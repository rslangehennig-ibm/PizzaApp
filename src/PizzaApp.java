import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class PizzaApp {
    private static final Logger logger = Logger.getLogger(PizzaApp.class.getName());

    public static void main(String[] args) throws IOException {
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", new MyHandler());
        server.setExecutor(null); 
        server.start();
        logger.info("Pizza app running on the port " + port);
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            logger.info("Received request: " + t.getRequestURI());
            String response;
            try {
                String filePath = "/app/index.html";
                logger.info("Reading file: " + filePath);
                response = new String(Files.readAllBytes(Paths.get(filePath)));
                t.sendResponseHeaders(200, response.length());
                logger.info("Successfully read file: " + filePath);
            } catch (IOException e) {
                response = "404 (Not Found)\n";
                t.sendResponseHeaders(404, response.length());
                logger.severe("File not found: " + e.getMessage());
            }
            try (OutputStream os = t.getResponseBody()) {
                os.write(response.getBytes());
                logger.info("Response sent to clients");
            }
        }
    }
}
