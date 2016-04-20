/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smtpProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import configData.ConfigurationManager;
import logic.Mail;
import logic.Person;

/**
 *
 * @author Raphael
 */
public class SmtpClient implements ISmtpClient{
	
	private PrintWriter writer;
	private BufferedReader reader;
	private ConfigurationManager cm;
	private String line;
	
	public SmtpClient (ConfigurationManager cm){
		this.cm = cm;
	}
   
   public void sendMail(Mail mail) throws IOException {
	   
	   System.out.println("TEST data mail: " + mail.getData());
	   
	   Socket socket = new Socket(cm.getServerAddress(), cm.getServeurPort());
	   writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
	   reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
	   
	   System.out.println(reader.readLine());
	   
	   writer.write("EHLO localhost\r\n");
	   writer.flush();
	   
	   line = reader.readLine();
	   
	   while(line.startsWith("250-")){
		   System.out.println(line);
		   line = reader.readLine();
	   }
		   
	   
	   writer.write("MAIL FROM:");
	   writer.write(mail.getSender());
	   writer.write("\r\n");
	   writer.flush();
	   
	   System.out.println("after from " +reader.readLine());
	   
	   for(Person receiver: mail.getReceivers()){
		   writer.write("RCPT TO:");
		   writer.write(receiver.getAddress());
		   writer.write("\r\n");
		   writer.flush();
		   System.out.println("after RCPT TO " +reader.readLine());
	   }
	   
	   
	   writer.write("RCPT TO:");
	   writer.write(mail.getCC());
	   writer.write("\r\n");
	   writer.flush();
	   
	   System.out.println("cc: " + reader.readLine());
	   
	   writer.write("DATA");
	   writer.write("\r\n");
	   writer.flush();
	   System.out.println("data: " + reader.readLine());
	   
	   writer.write(mail.getData());
	   writer.flush();
	   
	   writer.write("\r\n");
	   writer.write(".");
	   writer.write("\r\n");
	   writer.flush();
	   System.out.println("after data " + reader.readLine());
	   
	   writer.write("QUIT");
	   writer.flush();
	   writer.close();
	   socket.close();	   
   }
}