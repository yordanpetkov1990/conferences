import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Server {
    private static List<User> userList = Collections.synchronizedList(new ArrayList<>());
    private int port;
    private ServerSocket serverSocket;
    private final Object userLock;
    public Server(int port) {
        this.port = port;
        userList.add(new Lektor("yordan","12345"));
        userList.add(new Participant("georgi","54321"));
        userLock = new Object();
    }
    public void run(){
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server is listening...");
                BufferedReader bf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter pf = new PrintWriter(clientSocket.getOutputStream(),true);
                Thread clientThread = new Thread(() -> {

                    try {
                        pf.println("Please enter your username..");
                        String username = bf.readLine();
                        pf.println("Please enter your password..");
                        String password = bf.readLine();
                        User user = login(username,password);
                        UserType userType = user.getUserType();
                        switch (userType){
                            case LEKTOR:
                                pf.println("i am lektor");
                                break;
                            case ORGANIZATOR:
                                pf.println("i am organizator");
                                break;
                            case PARTICIPANT:
                                pf.println("i am participant");
                                break;
                        }
                    }catch (IOException e){
                        System.out.println(e.getMessage());
                    }

                });
                clientThread.start();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }


    }

    private User login(String username, String password) {
        synchronized (userLock){
            for (User user : userList) {
                if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                    return user;
                }
            }
            return null;
        }
    }
}
