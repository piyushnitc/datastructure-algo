package socketProgramming;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;

public class TCPClient {
    public static void main(String args[]) throws IOException {
        Socket socket = new Socket("localhost", 3081);

        // write to socket using object output stream
        OutputStream os = socket.getOutputStream();
        os.write(5);

        // read the socket response using Input Stream
        InputStream is = socket.getInputStream();
        var out = is.read();
        System.out.println(out);

        //close the stream
        is.close();
        os.close();
    }
}
