package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Jayvee on 2014/12/3.
 */
class ConnectThread extends Thread {
    String addr = null;
    int port = 0;
    ClientEntity clientEntity;

    public ConnectThread(ClientEntity clientEntity, String addr, int port, String loginMsg) {
        this.clientEntity = clientEntity;
        this.addr = addr;
        this.port = port;
    }

    @Override
    public void run() {

        try {
            System.out.println("正在连接服务器" + addr + ":" + port);
            Socket connectSocket = new Socket(addr, port);

            if (connectSocket.isConnected()) {
                OutputStream out = connectSocket.getOutputStream();
                out.write(("test-" + System.currentTimeMillis()).getBytes("utf-8"));
            }
            InputStream in = connectSocket.getInputStream();
            int count = 0;
            String port_str = null;
            while (count == 0) {
                try {
                    count = in.available();
                    byte[] b = new byte[count];
                    in.read(b);
                    port_str = new String(b, "utf-8");

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (port_str != null) {
                connectSocket.close();
                int port = Integer.parseInt(port_str);
                connectSocket = new Socket("127.0.0.1", port);
                if (connectSocket.isConnected()) {
                    clientEntity.onConnected(connectSocket);
                }
//                    in = connectSocket.getInputStream();
//                    final InputStream finalIn = in;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}