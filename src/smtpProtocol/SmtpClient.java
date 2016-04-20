/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smtpProtocol;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import configData.ConfigurationManager;
import logic.Mail;

/**
 *
 * @author Raphael
 */
public class SmtpClient implements ISmtpClient{
	
	private PrintWriter writer;
	private ConfigurationManager cm;
	
	public SmtpClient (ConfigurationManager cm){
		this.cm = cm;
	}
   
   public void sendMail(Mail mail) throws IOException {
	   
	   System.out.println("TEST data mail: " + mail.getData());
	   
	   Socket socket = new Socket(cm.getServeurAddress(), cm.getServeurPort());
	   writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
	   
	   writer.write("MAIL FROM:");
	   writer.write(mail.getSender());
	   writer.write("\r\n");
	   writer.flush();
	   
	   for(String receiver: mail.getReceivers()){
		   writer.write("RCPT TO:");
		   writer.write(receiver);
		   writer.write("\r\n");
		   writer.flush();
	   }
	   
	   writer.write("RCPT TO:");
	   writer.write(mail.getCC());
	   writer.write("\r\n");
	   writer.flush();
	   
	   writer.write("DATA");
	   writer.write("\r\n");
	   writer.flush();
	   
	   writer.write(mail.getData());
	   writer.flush();
	   
	   writer.write("\r\n");
	   writer.write(".");
	   writer.write("\r\n");
	   writer.flush();
	   
	   writer.write("QUIT");
	   writer.flush();
	   writer.close();
	   socket.close();	   
   }
}
