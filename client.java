// A Java program for a Client
import java.net.*;
import java.io.*;
import java.util.*;
 
public class client
{
    // initialize socket and input output streams
    private Socket socket            = null;
    private BufferedReader  input   = null;
    private DataOutputStream out     = null;
 
    // constructor to put ip address and port
    public client(String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connection to server Successfull!");
 
            // takes input from terminal
            input  = new BufferedReader(new InputStreamReader(System.in));
 
            // sends output to the socket
            out    = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
 
        // string to read message from input
        String line = "";
        String name = "";
        String text = "";
        String ip = "";
        Scanner sc=new Scanner(System.in);
        int ch=0;
			
        // keep reading until 5 is input
        while (!line.equals("Over"))
        {		System.out.println("\n1.Create\n2.Edit\n3.Delete\n4.View\n5.Exit\nEnter choice: ");
            ch = sc.nextInt();
            switch(ch)
            {	
                case 1:
					try
                    {
                        out.writeUTF("cf");
                        System.out.print("\nEnter file name to be created: ");
                        name= sc.next();
                        out.writeUTF(name);
						System.out.print("File creation...");
						
                    }
                    catch(IOException i)
                    {
                        System.out.println(i);
                    }
                    break;
                case 2:
                    try
                    { 
                        out.writeUTF("ef");
                        System.out.print("\nEnter file name: ");
                        name= sc.next();
                        out.writeUTF(name);
                        System.out.print("Enter line to be added, # to end input:\n");
                        sc.nextLine(); //ignores leading whitespaces
                        while(!ip.equals("#"))
                        {
                                ip = sc.nextLine();
                        	text = text + ip + "\n";
			}
                        out.writeUTF(text);
                    }
                    catch(IOException i)
                    {
                        System.out.println(i);
                    }
                    break;
                case 3:
                    try
                    {
                        out.writeUTF("df");
                        System.out.print("\nEnter file name: ");
                        name= sc.next();
                        out.writeUTF(name);
						System.out.print("File deletion...");
                    }
                    catch(IOException i)
                    {
                        System.out.println(i);
                    }
                    break;
                case 4:
                    try
                    {
                        out.writeUTF("vf");
                        System.out.print("\nEnter file name: ");
                        name= sc.next();
                        out.writeUTF(name);
						System.out.print("Displaying contents of file");
                    }
                    catch(IOException i)
                    {
                        System.out.println(i);
                    }
                    break;
                case 5:line="Over";
                    try
                    {   
						System.out.print("Exiting and closing connection... ");
                        out.writeUTF(line);
                    }
                    catch(IOException i)
                    {
                        System.out.println(i);
                    }
                    break;
                default:
                    System.out.println("\nInvalid Input");
                    break;
            }
        }
 
        // close the connection
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
 
    public static void main(String args[])
    {
        client client = new client("127.0.0.1", 5000);
    }
}