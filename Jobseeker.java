//Jobseeker
import java.io.*; 
import java.net.*; 
import java.util.Scanner; 


public class Jobseeker 
{ 
	public static void main(String[] args) throws IOException 
	{ 
		try
		{ 
			Scanner scan = new Scanner(System.in); 

			InetAddress ip = InetAddress.getByName("localhost"); 
	 
			Socket s = new Socket(ip, 5056); 
	
			 
			DataInputStream dis = new DataInputStream(s.getInputStream()); 
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
	
			
			while (true) 
			{ 
				System.out.println(dis.readUTF()); 
				String tosend = scan.nextLine(); 
				dos.writeUTF(tosend); 

		        System.out.println("Waiting for job....");
                String job = dis.readLine();
                String[] jNum = job.split(","); 
              switch(Integer.parseInt(jNum[0])) {
                
                case 1:
                    boolean isOnline;
                    
                    if(Integer.parseInt(jNum[1]) == 1) {
                       
                        String jobNum = jNum[2].replace('.', ',');
                        String[] ip = jobNum.split(",");

                        byte[] ipAddr = new byte[ip.length];
                        for(int i = 0; i < ip.length; i++) {
                            int part = Integer.parseInt(ip[i]);
                            ipAddr[i] = (byte) part;
                        }

                        isOnline = InetAddress.getByAddress(ipAddr).isReachable(5000);
                    }
                    
                    else
                        isOnline = InetAddress.getByName(jNum[2]).isReachable(5000);

                    
                    if(isOnline)
                        dis.println(jNum[2] + " is online.");
                    else
                        dis.println(jNum[2] + " is not online.");
                    return;
                case 2:
                	boolean isOnline;

                	String jobNum = jNum[2].replace('.', ',');
                    String[] ip = jobNum.split(",");
                    String[] prt = jobNum.split(",");
                    
                    byte[] ipAddr = new byte[ip.length];
                    for(int i = 0; i < ip.length; i++) {
                        int part = Integer.parseInt(ip[i]);
                        ipAddr[i] = (byte) part;
                    }
                    isOnline = InetAddress.getByAddress(ipAddr).isReachable(prt);

                    if(isOnline)
                        dis.println(jNum[2] + " is online.");
                    else
                        dis.println(jNum[2] + " is not online.");
                    return;

                case 3:
                	String jobNum = jNum[2].replace('.', ',');
                    String[] ip = jobNum.split(",");
                    String[] prt = jobNum.split(",");
                    
                    byte[] ipAddr = new byte[ip.length];
                    for(int i = 0; i < ip.length; i++) {
                        int part = Integer.parseInt(ip[i]);
                        ipAddr[i] = (byte) part;
                    }
                    while(100){
                	Socket trg = new Socket(ipAddr, prt);

                	try {
        				trg.connect();
    					}
                    }
                case 4:
                	String jobNum = jNum[2].replace('.', ',');
                    String[] ip = jobNum.split(",");
                    String[] prt = jobNum.split(",");
                    
                    byte[] ipAddr = new byte[ip.length];
                    for(int i = 0; i < ip.length; i++) {
                        int part = Integer.parseInt(ip[i]);
                        ipAddr[i] = (byte) part;
                    }
                    while(100){
                	Socket trg = new Socket(ipAddr, prt);

                	try {
        				trg.connect();
    					}
                    }
                	
                }
				
			
				if(tosend.equals("Exit")) 
				{ 
					System.out.println("Closing this connection : " + s); 
					s.close(); 
					System.out.println("Connection closed"); 
					break; 
				} 
				 
				String received = dis.readUTF(); 
				System.out.println(received); 
			} 

            System.out.println("JOB: " + job);
            dis.println("done");
            System.out.println("Jobseeker closed.");
            scan.close(); 
            dis.close(); 
            dos.close(); 
        }
		catch(Exception e){ 
			e.printStackTrace(); 
	    }
	} 
} 