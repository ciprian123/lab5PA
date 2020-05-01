import java.io.*; 
import java.net.*; 
import java.util.*; 

public class GameClient {
    private static int nrOfPlayers;
    private static final int PORT = 6969;
    private static final String ADDRESS = "127.0.0.1";

    private static InetAddress ip;
    private static Scanner scanner;
    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;

    public static void main(String[] args) throws IOException {
        try {
            scanner = new Scanner(System.in); 

            // getting localhost ip 
            ip = InetAddress.getByName(ADDRESS); 
      
            // establish the connection with server port
            socket = new Socket(ip, PORT); 

            // obtaining input and out streams 
            dataInputStream  = new DataInputStream(socket.getInputStream()); 
            dataOutputStream = new DataOutputStream(socket.getOutputStream()); 
      
            // the following loop performs the exchange of 
            // information between client and client thread class 
            while (true) {
                System.out.println(dataInputStream.readUTF()); 
                String toSend = scanner.nextLine();
                dataOutputStream.writeUTF(toSend); 
                  
                // If client sends exit, close this connection  
                // and then break from the while loop 
                if (toSend.equals("exit")) { 
                    socket.close(); 
                    System.out.println("[Client]Connection closed... "); 
                    break;
                }
                else if (toSend.equals("stop")) {
                    socket.close();
                    System.out.println("[Client] The server is stopped. Exiting... ");
                    break;
                }
                else if (toSend.equals("create game")) {
                    System.out.println("Creating game... ");
                }
                else if (toSend.equals("join game")) {
                    System.out.println("Joining game... ");
                }
            }
            dataInputStream.close(); 
            dataOutputStream.close(); 
            scanner.close(); 
        } catch(Exception ex) { 
            ex.printStackTrace(); 
        } 
    } 
} 