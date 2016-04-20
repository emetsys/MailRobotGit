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
 * @author Raphael Henocq et Luciens badoux
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
	   
	   Socket socket = new Socket(cm.getServerAddress(), cm.getServeurPort());
	   writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
	   reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
	   
	   line = reader.readLine(); //Message de connexion au serveur
	   
	   writer.write("EHLO localhost\r\n");
	   writer.flush();
	   
	   line = reader.readLine();
	   
	   while(line.startsWith("250-")){  //Accès au serveur ok 
		   line = reader.readLine();
	   }
		   
	   
	   writer.write("MAIL FROM:");
	   writer.write(mail.getSender());
	   writer.write("\r\n");
	   writer.flush();
	   
	   line = reader.readLine(); //Mail From Ok
	   
	   for(Person receiver: mail.getReceivers()){
		   writer.write("RCPT TO:");
		   writer.write(receiver.getAddress());
		   writer.write("\r\n");
		   writer.flush();
		   line = reader.readLine(); //RCPT Ok
	   }
	   
	   
	   writer.write("RCPT TO:");
	   writer.write(mail.getCC());
	   writer.write("\r\n");
	   writer.flush();
	   
	   line = reader.readLine(); //RCPT Ok
	   
	   writer.write("DATA");
	   writer.write("\r\n");
	   writer.flush();
	   line = reader.readLine();  //Data Ok
	   
	   writer.write(mail.getData());
	   writer.flush();
	   
	   writer.write("\r\n");
	   writer.write(".");
	   writer.write("\r\n");
	   writer.flush();
	   line = reader.readLine();
	   
	   writer.write("QUIT");
	   writer.flush();
	   writer.close();
	   reader.close();
	   socket.close();	   
   }
}