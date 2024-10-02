package socketProgramming;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class TCPServer {
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.setReceiveBufferSize(1000);
        serverSocket.bind(new InetSocketAddress(3081),10);

        //This blocks the calling thread for infinite period of time
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        while(true) {
            var mychar = inputStream.read();
            if(mychar == -1) {
                System.out.println("Stream Ended..");
                System.exit(0);
            }
            System.out.println((char)mychar);
        }
    }


}
