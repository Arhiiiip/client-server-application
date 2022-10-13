package request;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Sender {

    public static void send(ByteBuffer serializeObject, SocketChannel socketClient) throws IOException {
        try {
            socketClient.write(serializeObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
