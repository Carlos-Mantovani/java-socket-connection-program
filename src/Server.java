import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            Socket socket = server.accept();
            System.out.println("Client accepted");

            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";

            while (!line.equals("Over")) {
                try {
                   line = in.readUTF();
                   System.out.println(line);
                }
                catch(IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
            socket.close();
            in.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }
}
