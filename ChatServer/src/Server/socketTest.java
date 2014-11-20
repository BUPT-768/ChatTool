package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Jayvee on 2014/11/18.
 */
public class socketTest {
    public static void main(String a[]) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234, 10);//请求队列长度10
            Socket socket = serverSocket.accept();
            System.out.println(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
