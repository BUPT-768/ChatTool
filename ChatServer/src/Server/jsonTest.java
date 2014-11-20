package Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Jayvee on 2014/11/18.
 */
public class jsonTest {
    public static void main(String[] a) {
        int t = (int) 0.7f;
        System.out.println(t);
//        try {
//            Socket socket = new Socket("127.0.0.1", 1234);
//            System.out.println(socket.getRemoteSocketAddress());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JSONObject root = new JSONObject();
//        try {
//            JSONObject l = new JSONObject();
//            l.put("adad", 123123123);
//            JSONArray array = new JSONArray();
//            array.put("jjjjjjjjj");
//            array.put("dddddddddd");
//            root.put("ID", 123);
//            root.put("msg", "sakdfjlakdjfa");
//            root.put("obj", l);
//            root.put("shuzu", array);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        System.out.println(root.toString());
//
//        String str = root.toString();
//        JSONTokener tokener = new JSONTokener(str);
//        try {
//            JSONObject receiveRoot = (JSONObject) tokener.nextValue();
//            String msg = receiveRoot.getString("msg");
//            System.out.println(msg);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
}
