import java.io.*; 
import java.net.*;

class ClientThread extends Thread { 
    final DataInputStream dataInputStream; 
    final DataOutputStream dataOutputStream; 
    final Socket socket; 
    
    public ClientThread(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.socket           = socket; 
        this.dataInputStream  = dataInputStream; 
        this.dataOutputStream = dataOutputStream; 
    } 

    @Override
    public void run() { 
        while (true) {
            try {
                // Ask user what he wants 
                dataOutputStream.writeUTF("[SERVER] Please enter your option or `exit` to terminate connection..."); 
                
                // receive the answer from client 
                String receivedString = dataInputStream.readUTF(); 
                System.out.println(receivedString);
                if (receivedString.equals("stop")) { 
                    this.socket.close();
                    System.out.println("[SERVER] Server stopped... "); 
                    try {
                        // closing resources 
                        this.dataInputStream.close(); 
                        this.dataOutputStream.close(); 
                    } catch(IOException ex) { 
                        ex.printStackTrace(); 
                    }
                    System.exit(0);
                }
                else if (receivedString.equals("exit")) {
                    // the clients is exiting
                    this.socket.close();
                    System.out.println("[SERVER] Client exited... "); 
                    break;
                }
                else {
                    System.out.println("[Server] Server received the request ... ");
                }
            } catch (IOException ex) { 
                ex.printStackTrace(); 
            }
        } 
        try {
            // closing resources 
            this.dataInputStream.close(); 
            this.dataOutputStream.close(); 
        } catch(IOException ex){ 
            ex.printStackTrace(); 
        }
    } 
} 