// A Java program for a Server
import java.net.*;
import java.io.*;
import java.util.*;

public class server
{
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
 
    // constructor with port
    public server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");
 
            System.out.println("Waiting for a client ...");
 
            socket = server.accept();
            System.out.println("Client accepted");
 
            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            //Scanner sc= new Scanner(System.in);
            String line = "";
            String name = "";
            String text = "";
            File myObj;

            // reads message from client until "Over" is sent
            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    switch(line)
                    {
                        case "cf":
                            name = in.readUTF();
                            myObj = new File(name);
                            if (myObj.createNewFile()) 
                            {
                                System.out.println("File created: " + myObj.getName());
      			    } 
                            else 
        		    {
                                System.out.println("File already exists.");
      			    }
                            break;
                        case "vf":
                            try 
                            {
                                name = in.readUTF();
                                myObj = new File(name);
                                Scanner myReader = new Scanner(myObj);
                                while (myReader.hasNextLine()) 
                                {
                                    String data = myReader.nextLine();
                                    System.out.println(data);
                                }
                                myReader.close();
                            } 
                            catch (FileNotFoundException i) 
                            {
                                System.out.println("i");
                            }
                            break;
                        case "ef":
                            try 
                            {
                                  name = in.readUTF();
                                  FileWriter myWriter = new FileWriter(name);
                                  text = in.readUTF();
                                  myWriter.write(text);
                                  myWriter.close();
                                  System.out.println("Successfully wrote to the file.");
                            } 
                            catch (IOException i) 
                            {
                                  System.out.println("i");
                            }
                            break;
			case "df":
                            name = in.readUTF();
                            myObj = new File(name); 
                            if (myObj.delete()) 
                            { 
                                System.out.println("Deleted the file: " + myObj.getName());
                            } 
                            else 
                            {
                                System.out.println("Failed to delete the file.");
                            } 
                            break;    
                        default:
                            line="Over";
                    }
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
 
            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
 
    public static void main(String args[])
    {
        server server = new server(5000);
    }
}