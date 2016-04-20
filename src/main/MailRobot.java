package main;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.List;

import configData.ConfigurationManager;
import logic.*;
import smtpProtocol.SmtpClient;

/**
 *
 * @author Raphael
 */
public class MailRobot {
   
   public static void main(String[] args) throws IOException
   {
      ConfigurationManager cm = new ConfigurationManager();
      // cm OK
      /*System.out.println(cm.getServeurAddress());
      System.out.println(cm.getServeurPort());
      System.out.println(cm.getNumberOfGroups());
      System.out.println(cm.getWitnessCc());*/
      
      PrankGenerator pg = new PrankGenerator(cm);
      List<Group> groups = pg.generateGroup();
      List<Message> messages = pg.generateMessages();
      Mail mail = new Mail(groups.get(0), messages.get(0), cm.getWitnessCc());
	  System.out.println("sender\n" + groups.get(0).getSender().getAddress());
	  for(Person p:groups.get(0).getReceivers())
		  System.out.println("receivers\n" + p.getAddress());

      
      SmtpClient smtpClient = new SmtpClient(cm);
      
      smtpClient.sendMail(mail);
   }   
}
