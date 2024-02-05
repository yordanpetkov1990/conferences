import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",5555);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pf = new PrintWriter(socket.getOutputStream(),true)){
            Scanner scan = new Scanner(System.in);
            String input1 = bufferedReader.readLine();
            System.out.println(input1);
            String username = scan.nextLine();
            pf.println(username);
            String input2 = bufferedReader.readLine();
            System.out.println(input2);
            String password = scan.nextLine();
            pf.println(password);
            String input33 = bufferedReader.readLine();
            System.out.println(input33);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
