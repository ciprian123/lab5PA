import java.io.*;  
import java.net.*; 
  
public class GameServer  {
    public static void main(String[] args) throws IOException { 
        // server is listening on port 6969
        ServerSocket serverSocket = new ServerSocket(6969); 

        // running infinite loop for getting client request 
        while (true) { 
            Socket socket = null; 
            try { 
                socket = serverSocket.accept(); 
                System.out.println("A new client is connected : " + socket); 

                // obtaining input and out streams 
                DataInputStream dataInputStream   = new DataInputStream(socket.getInputStream()); 
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream()); 

                System.out.println("[SERVER] Assigning new thread for this client"); 

                // create a new thread object 
                Thread threadClient = new ClientThread(socket, dataInputStream, dataOutputStream); 

                // Invoking the start() method
                threadClient.start(); 
            } 
            catch (Exception ex) { 
                socket.close(); 
                ex.printStackTrace(); 
            } 
        } 
    } 
} 