import java.io.*;
import java.net.Socket;

/**
 * Created by svetlana on 10.02.17.
 */
public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("\nCLIENT");

            int port = Integer.parseInt(args[1]);
            Socket socket = new Socket(args[0], port);  // Клиент создает сокет и подключает его к порту на хосте
            System.out.println("The connection is established. Loading...");

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String message;
            message = inputStream.readUTF(); // ждем ответа от сервера (подтверждение подключния)
            System.out.println("Server: " + message);

            if (message.equalsIgnoreCase("you are connected.")) {
                System.out.println("Input message and press Enter to send");
                while (true) {
                    message = reader.readLine();    // читаем введенную строку
                    outputStream.writeUTF(message); // отсылаем сообщение серверу
                    outputStream.flush();
                    if (message.equalsIgnoreCase("quit"))
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
