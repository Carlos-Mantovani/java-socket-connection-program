import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public Client(String address, int port) {
        Socket socket;
        DataInputStream input;
        DataOutputStream out;
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            input = new DataInputStream(System.in);

            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException i) {
            System.out.println(i);
            return;
        } catch (IOException i) {
            System.out.println(i);
            return;
        }

        String line = "";

        while (!line.equals("Over")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch(IOException i) {
                System.out.println(i);
            }
        }

        try {
            input.close();
            out.close();
            socket.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
    }
}
