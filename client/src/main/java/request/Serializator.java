package request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class Serializator {
    public static long serialVersionUID = 1128932627584373707L;
    ObjectOutputStream outputStream;
    ByteArrayOutputStream bytesOut;

    public Serializator(){
    }

    public ByteBuffer serialize(Object o) throws IOException {
        bytesOut = new ByteArrayOutputStream();
        outputStream = new ObjectOutputStream(bytesOut);
        outputStream.writeObject(o);
        outputStream.flush();
        outputStream.close();
        byte[] bytes = bytesOut.toByteArray();
        bytesOut.close();
        outputStream.close();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return buffer;
    }
}
