package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class newSocketThread extends Thread {
    int port;
    String ID;
    SocketManager socketManager;

    public newSocketThread(int port, String ID, SocketManager socketManager) {
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
                socket.getOutputStream().write("服务器发来欢迎消息！".getBytes("utf-8"));
                socketManager.onAddSocket(this.ID, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
