package request;

import utility.ObjectForServer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;

public class Deserializator {

    public ObjectForServer deserialize(SocketChannel socketChannel) throws ClassNotFoundException, IOException {
        int count = 0;
        int previousCount;

        ByteBuffer buffer = ByteBuffer.allocate(4096);

        do {
            previousCount = count;
            try {
                count = socketChannel.read(buffer);
            } catch (ClosedChannelException e) {
                System.out.println("execute_script was complete");
                break;
            }
        } while (buffer.hasRemaining() && (count > 0 || previousCount == 0));
        try {
            buffer.flip();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.slice().array());
            ObjectInputStream inObj = new ObjectInputStream(byteArrayInputStream);
            ObjectForServer response = (ObjectForServer) inObj.readObject();
            return response;
        } catch (StreamCorruptedException e) {
            System.out.println("--------------------------");
        }
        return new ObjectForServer("");
    }
}
