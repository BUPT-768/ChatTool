package Server;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServiceSocketThread extends Thread {
    int port;
    String ID;
    SocketManager socketManager;

    public ServiceSocketThread(int port, String ID, SocketManager socketManager) {
        this.port = port;
        this.ID = ID;
        this.socketManager = socketManager;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket socket = ss.accept();
            if (socket != null) {
                String users = "";
                ArrayList<String> userList = socketManager.getUserList();
                StringBuffer sb = new StringBuffer("服务器发来欢迎消息！当前服务器上有");
                sb.append(userList);
                socket.getOutputStream().write(sb.toString().getBytes("utf-8"));
                socketManager.onAddSocket(this.ID, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
