package main;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import configData.ConfigurationManager;
import logic.*;
import smtpProtocol.SmtpClient;

/**
 *
 * @author Raphael Henocq et Luciens badoux
 */
public class MailRobot {
   
   public static void main(String[] args) throws IOException
   {
      ConfigurationManager cm = new ConfigurationManager();

      
      PrankGenerator pg = new PrankGenerator(cm);
      List<Group> groups = pg.generateGroup();
      List<Message> messages = pg.generateMessages();
      List<Mail> mails = new ArrayList<Mail>();
      for (int i = 0 ; i < groups.size(); i++)
      {
    	  mails.add(new Mail(groups.get(i),messages.get(i),cm.getWitnessCc()));
      }
   
      SmtpClient smtpClient = new SmtpClient(cm);
      
      
      System.out.println("Début envoie des mails ! ");
      for (int i = 0 ; i < groups.size(); i++)
      {
    	  smtpClient.sendMail(mails.get(i));
      }
      
      System.out.println("Mails envoyés ! ");
      
     
   }   
}
