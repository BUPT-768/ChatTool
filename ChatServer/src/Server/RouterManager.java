package Server;

/**
 * Created by Jayvee on 2014/11/26.
 */
public class RouterManager {
    SocketManager socketManager;

    public RouterManager(SocketManager socketManager) {
        this.socketManager = socketManager;
    }

    public void SubmitMsg(String sourceID, String targetID, String msg) {

        socketManager.getSocket(sourceID);
    }
}
