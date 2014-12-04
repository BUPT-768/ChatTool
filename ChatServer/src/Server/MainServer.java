package Server;

/**
 * Created by Jayvee on 2014/11/25.
 */
public class MainServer {
    public static LoginServer loginServer;
    public static SocketManager socketManager;

    public static void main(String a[]) {
        System.out.println("等待连接");
        socketManager = new SocketManager();
        loginServer = new LoginServer(768, 10, socketManager);
        loginServer.start();
    }
}
