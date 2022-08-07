import java.io.*;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    final static int PORT = 9999;
    public static void main(String[] args) throws IOException {
        Server myServer = new Server(PORT);
        myServer.toStart();
    }
}


