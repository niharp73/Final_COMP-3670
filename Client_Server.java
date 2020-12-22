import java.net.*;
import java.io.*;
public class Job {
 private Socket socket;
 private BufferedReader in;
 private PrintWriter out;
 //Defining the funstions to be used to show up the application
 
 // Make a connection to the application
 private void connectToApp()
 {
    try 
    {
     socket = new Socket(InetAddress.getLocalHost(), App.SERVER_PORT);
     in = new BufferedReader(new InputStreamReader(
     socket.getInputStream()));
     out = new PrintWriter(socket.getOutputStream());
    } 
    catch(IOException e)
    {
     System.out.println("JOB-TAKER: Cannot connect to application");
     System.exit(-1);
    }
 } 
 
// Disconnect from the application
 private void disconnectFromApp()
 {
    try
    {
     msocket.close();
    }
    catch(IOException e) 
    {
    System.out.println("JOB-TAKER: Cannot disconnect from application");
    }
 }
 
 // Ask the job creater for the current progress in job
 private void askForProgress() 
 {
    connectToApp();
    out.println("What is the status of the job?");
    out.flush();
    
    try
    {
    String status = in.readLine();
    System.out.println("JOB-TAKER: The current progress is " + status);
    } 
    catch(IOException e)
    {
    System.out.println("JOB-TAKER: Cannot receive status from the application");
    }
    disconnectFromApp();
 }
 
 // Ask the application to show up the total job postings avaialable at one time 
 private void askForNumberOfJobs()
 {
     
    connectToApp();
    out.println("How many job are available at the moment for the JOB-SEEKER?");
    out.flush();
    int count = 0;
    
    try
    {
    
    count = Integer.parseInt(in.readLine());
    } 
    catch(IOException e) 
    {
    System.out.println("JOB-SEEKER: Cannot receive count of available job postings!");
    }
    System.out.println("JOB-SEEKER: The count of job postings are " + count);
    disconnectFromApp();
 }
 //Ask the Job seeker to look for the other job available at the same time
 private void askForOtherJob() 
 {
    connectToApp();
    out.println("Please do the other job by swithing the existing job!");
    out.flush();
    disconnectFromApp();
 }
}
 //main function body
 public static void main (String[] args) 
 {
 Job j = new Job();
 j.connectToApp();    
 j.askForNumberOfJobs();
 j.askForProgress();
 j.askForOtherJob();
 j.disconnectFromApp();
 }