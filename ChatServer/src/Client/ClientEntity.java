package Client;

import javax.naming.ldap.SortKey;
import java.io.*;
import java.net.Socket;

/**
 * Created by Jayvee on 2014/11/20.
 */
public class ClientEntity {
    private Socket socket = null;
    private InputListenerThread inputListenerThread;
    private OutputStream out = null;
    private ConnectThread connectThread;

//    public ClientEntity() {
//
////        connectThread = new ConnectThread(this, "127.0.0.1", 768, null);
////        connectThread.start();
//    }

    public void onLogin(String addr, int port, String username, String password) {
        connectThread = new ConnectThread(this, addr, port, username);
        connectThread.start();
    }

    public static void main(String a[]) {
        try {
            Socket socket = new Socket("127.0.0.1", 768);
            if (socket.isConnected()) {
                OutputStream out = socket.getOutputStream();
                out.write(("test-" + System.currentTimeMillis()).getBytes("utf-8"));
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

    class InputListenerThread extends Thread {
        InputStream in;

        public InputListenerThread(InputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            while (true) {
                int count = 0;
                String text = null;
                while (count == 0) {
                    try {
                        count = in.available();
                        byte[] b = new byte[count];
                        int read = in.read(b);
                        text = new String(b, "utf-8");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                System.out.println(text);
                onCallback(text);
            }
        }
    }


    /**
     * 消息回调函数
     *
     * @param msg 应该是符合json规范的字符串
     */

    private void onCallback(String msg) {
        System.out.println(msg);
    }

    /**
     * 连接成功后的回调函数
     *
     * @param socket 连接成功后的socket
     */
    protected void onConnected(Socket socket) {
        this.socket = socket;
        InputStream in = null;

        try {
            in = socket.getInputStream();
            this.out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != in) {
            inputListenerThread = new InputListenerThread(in);
            inputListenerThread.start();
        }
    }

    /**
     * 向服务器发出信息
     *
     * @param out
     * @param text
     */
    public void onSendMsg(OutputStream out, String text) {
        try {
            out.write(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
