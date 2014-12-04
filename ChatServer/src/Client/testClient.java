package Client;

/**
 * Created by Jayvee on 2014/12/3.
 */
public class testClient {
    public static void main(String a[]) {


        ClientEntity clientEntity = new ClientEntity();
        clientEntity.onLogin("127.0.0.1", 768, "jayvee", null);
    }
}
