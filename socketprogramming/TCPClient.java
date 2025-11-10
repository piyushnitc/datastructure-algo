package socketprogramming;
import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = new Socket(host.getHostName(), 3081);;
        OutputStream oos = socket.getOutputStream();

        //ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        //ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());;

        for(int i=0; i<5;i++){
            //write to socket using ObjectOutputStream
            System.out.println("Sending request to Socket Server "+i);
            if(i==4)oos.write(4);//writeObject("bye1");
            else
                oos.write(i);
            //read the server response message
            //String message = (String) ois.readObject();
            //System.out.println("Message: " + message);
            //close resources
            //ois.close();
            Thread.sleep(1000);
        }
        //socket.close();
    }
}
