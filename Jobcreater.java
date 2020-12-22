//Jobcreater and ClientHandler
import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 

public class Jobcreater 
{ 
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException 
	{

		ServerSocket ss = new ServerSocket(5056); 
		
		 
		while (true) 
		{ 
			Socket s = null; 
			
			try
			{ 
				
				s = ss.accept(); 
				
				System.out.println("A new job seeking client is connected : " + s); 
			
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
				
				System.out.println("Assigning new thread for this client"); 

				Thread t = new ClientHandler(s, dis, dos); 

				t.start(); 
				
			} 
			catch (Exception e){ 
				s.close(); 
				e.printStackTrace(); 
			} 
		} 
	} 
} 


class ClientHandler extends Thread 
{ 
	boolean hasjob = false; 
	final DataInputStream dis; 
	final DataOutputStream dos; 
	final Socket s; 

	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) 
	{ 
		this.s = s; 
		this.dis = dis; 
		this.dos = dos;
	} 

	@Override
	public void run() 
	{ 
		String received; 
		String toreturn; 
		while (true) 
		{ 
			try { 

				dos.writeUTF("What can i do for you..\n"+ 
							"Type Exit it you want to terminate connection."); 
			
				received = dis.readUTF(); 
				
				if(received.equals("Exit")) 
				{ 
					System.out.println("Client " + this.s + " sends exit..."); 
					System.out.println("Closing this connection."); 
					this.s.close(); 
					System.out.println("Connection closed"); 
					break; 
				}  
			
				if (received=="Request Job" && hasjob == false) {
						String accept;
						System.out.println("Accept Job Request(y/n): ");
						accept = dis.readUTF();
						if (accept == "y") {
						toreturn =  "Job Request Accepted";
						hasjob = true;
						dos.writeUTF(toreturn); 	
						} 
						else{
						toreturn =  "Job Request Rejected";
						dos.writeUTF(toreturn);
						}
				}
				 else 
				 	if(received=="Request Job" && hasjob == true) {
					System.out.println("You already have a job");
				}

				switch(jobs()) {

                    case 1:
                        System.out.println("Press 1. To Detect by IP address.\n 2. To Detect by host name.");
                        int option = scan.nextInt();
                        if(option == 1)
                            System.out.print("Please enter the IP address: ");
                        else
                            System.out.print("Please enter the host name: ");
                        scan.nextLine();
                        String addr = scan.nextLine();

                        dos.printf("1,%d,%s\n", option, addr);

                        String result = dis.readLine();
                        System.out.println(result);
                        return;
                    case 2:
     					System.out.print("Please enter the IP address: ");
     					scan.nextLine();
                        String addr = scan.nextLine();
                        System.out.print("Please enter the Port: ");
                        scan.nextLine();
                        String prt = scan.nextLine();

                        dos.printf("2,%s,%i\n", addr,prt);

                    	String result = dis.readLine();
                        System.out.println(result);
                        return;
                    case 3:
                    	System.out.print("Please enter the IP address: ");
     					scan.nextLine();
                        String addr = scan.nextLine();
                        System.out.print("Please enter the Port: ");
                        scan.nextLine();
                        String prt = scan.nextLine();

                        dos.printf("3,%s,%i\n", addr,prt);

                    	String result = dis.readLine();
                        System.out.println(result);
                    	
                    case 4:
                    	System.out.print("Please enter the IP address: ");
     					scan.nextLine();
                        String addr = scan.nextLine();
                        System.out.print("Please enter the Port: ");
                        scan.nextLine();
                        String prt = scan.nextLine();

                        dos.printf("4,%s,%i\n", addr,prt);

                    	String result = dis.readLine();
                        System.out.println(result);
                    	
                    default:
                        System.out.println("Invalid option.");
                }

                dos.println("Perform the job\n Print Job one");
                String completion = dis.readLine();

                if(completion.contains("done")){
                    System.out.println("Job Completed");
                    break;
                }

                System.out.println("Jobcreator waiting... Exit to terminate");
                received = dis.readUTF(); 
				
				if(received.equals("Exit")) 
				{ 
					System.out.println("Client " + this.s + " sends exit..."); 
					System.out.println("Closing this connection."); 
					this.s.close(); 
					System.out.println("Connection closed"); 
					break; 
				}  

                socket.close();
                scan.close();
                this.dis.close(); 
				this.dos.close();
            }
            catch (IOException e){ 
			e.printStackTrace(); 
			} 
	    }
	} 
	public static int jobs() {
        System.out.println("Select job for Jobseeker to perform?");
        System.out.println("1. Detect if a given IP address or Host Name is online or not.");
        System.out.println("2. Detect the status of the given port at the given IP address.");
        System.out.println("3. Execute a TCP flood attack against the given port on the given IP.");
        System.out.println("4. Execute a UDP flood attack against the given port on the given IP.");

        return scan.nextInt();
    } 
