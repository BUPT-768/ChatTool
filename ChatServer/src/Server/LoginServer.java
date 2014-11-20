package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Jayvee on 2014/11/18.
 */
public class LoginServer extends Thread {
    ServerSocket serverSocket;
    int serverPort = 768;
    int waitLen = 10;
    boolean isStop = false;

    SocketManager socketManager;

    public LoginServer(int serverPort, int waitLen) {
        this.serverPort = serverPort;
        this.waitLen = waitLen;
        this.socketManager = new SocketManager();
    }

//    class LoginThread extends Thread {

//        public LoginThread() {
//        }

    public void shutdown() {
        this.isStop = true;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(serverPort, waitLen);
            while (!isStop) {
                Socket socket = serverSocket.accept();
                //首先读取连接用户的ID
                InputStream in = socket.getInputStream();
                while (!socket.isClosed()) {
                    int count = 0;
                    String ID = null;
                    while (count == 0) {
                        try {
                            count = in.available();
                            byte[] b = new byte[count];
                            in.read(b);
                            ID = new String(b, "utf-8");
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (ID!=null) {
                        System.out.println(ID+"连接！");
                        OutputStream out = socket.getOutputStream();
                        int port = (int) (Math.random() * 1000);
                        out.write((port + "").getBytes("utf-8"));
                        new newSocketThread(port, ID, this.socketManager).start();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//    }
}
