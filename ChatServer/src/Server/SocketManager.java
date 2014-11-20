package Server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jayvee on 2014/11/18.
 */
public class SocketManager {
    private Map<String, Socket> socketMap;

    public SocketManager() {
        this.socketMap = new HashMap<String, Socket>();
    }

    public void addSocket(String ID, Socket socket) {
        socketMap.put(ID, socket);
        System.out.println(ID + "-socket添加完成");
    }

    public void delSocket(String ID) {
        socketMap.remove(ID);
    }

    public Socket getSocket(String ID) {
        return socketMap.get(ID);
    }


    public Socket toAcceptSocket(int port) throws IOException {
        ServerSocket ss = new ServerSocket(port);

        if (ss.isBound()) {
            return null;
        } else {
            Socket socket = ss.accept();
            return socket;
        }
    }

    public void onAddSocket(String ID, Socket socket) {
        addSocket(ID, socket);
    }


}


