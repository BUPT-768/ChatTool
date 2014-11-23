package Client;

import java.io.*;
import java.net.Socket;

/**
 * Created by Jayvee on 2014/11/20.
 */
public class ClientEntity {
    public static void main(String a[]) {
        try {
            Socket socket = new Socket("127.0.0.1", 768);
            if (socket.isConnected()) {
                OutputStream out = socket.getOutputStream();
                out.write(("test-"+System.currentTimeMillis()).getBytes("utf-8"));
            }
            InputStream in = socket.getInputStream();
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
                socket.close();
                int port = Integer.parseInt(port_str);
                socket = new Socket("127.0.0.1", port);
                in = socket.getInputStream();
                final InputStream finalIn = in;
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            int count = 0;
                            String text = null;
                            while (count == 0) {
                                try {
                                    count = finalIn.available();
                                    byte[] b = new byte[count];
                                    finalIn.read(b);
                                    text = new String(b, "utf-8");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println(text);
                        }
                    }
                }.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
