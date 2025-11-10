package socketprogramming;
import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        //create the socket server object
        ServerSocket serverSocket = new ServerSocket(3081);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();

        while(true) {
            int message = is.read();
            if(message == -1)
                System.out.println("Sleep for 1000 ms");
                Thread.sleep(1000);
            System.out.println("Waiting for the client request "+message);
            if(String.valueOf(message).equalsIgnoreCase("bye")) {
                System.out.println("Client Disconnected");
                break;
            }
        }
        // Close the connection after the client disconnects
        System.out.println("Shutting down Socket server!!");
        serverSocket.close();
    }
}
