import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final static List<String> validPaths = List.of("/index.html",
            "/spring.svg", "/spring.png", "/resources.html", "/styles.css",
            "/app.js", "/links.html", "/forms.html", "/classic.html", "/events.html",
            "/events.js");
    private int port;

    public Server(int port) throws IOException {
        this.port = port;
    }

    ExecutorService eS = Executors.newFixedThreadPool(8);

    public void toStart() {
        try (final var serverSocket = new ServerSocket(port)) {
            while (true) {
                final var socket = serverSocket.accept();
                eS.submit(new Cal(socket, validPaths));
                eS.shutdown();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

