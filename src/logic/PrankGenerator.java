/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import configData.ConfigurationManager;

/**
 *
 * @author Raphael
 */
public class PrankGenerator {
   private ConfigurationManager cm;
   
   public PrankGenerator(ConfigurationManager cm)
   {
      this.cm = cm;
   }
   
   public List<Group> generateGroup()
   {
      List<Group> groups = new ArrayList<Group>();
      int nbGroups = cm.getNumberOfGroups();

      List<Person> personList = cm.getPersonList();
      int nbPeoplePerGroup = personList.size()/nbGroups; // si 0 groupes arithmetic exception, à voir si on gère
      
      List<Person> victims = new ArrayList<Person>();
      victims.clear();
      int i;
      
      
      for(i = 0; i < nbGroups-1; i++)
      {
    	  for(int j = 1+i*nbPeoplePerGroup; j<(i+1)*nbPeoplePerGroup; j++)
    	  {
    		  victims.add(personList.get(j));
    	  }
                  	  
    	  groups.add(new Group(personList.get(i*nbPeoplePerGroup), victims));
    	  
    	  /*for(Group g:groups)
    		  for(Person p:g.getReceivers())
    			  System.out.println("receivers " + p.getAddress() + " i: " + i);*/
    	  for(Person p:groups.get(0).getReceivers())
			  System.out.println("receivers " + p.getAddress() + " i: " + i);
    	  
    	  System.out.println("Taille tabGroup" + groups.size());
    	  victims.clear();  	  
    	  
      }
      
      System.out.println("Taille groupe: " + groups.size());
      /*for(Group g:groups)
		  for(Person p:g.getReceivers())
			  System.out.println("receivers " + p.getAddress());*/
      
      System.out.println("i:= " +i);
      
      // pour le dernier groupe plus grand
      for(int j = 1+i*nbPeoplePerGroup; j<(i+1)*nbPeoplePerGroup+personList.size()%nbPeoplePerGroup; j++)
	  {
		  victims.add(personList.get(j));		  
	  }     
      groups.add(new Group(personList.get(i*nbPeoplePerGroup), victims));
      System.out.println("Taille groupe" + groups.size());
      
      
      /*System.out.println("nbGroupe: " + groups.size());
      System.out.println("sender\n" + groups.get(0).getSender().getAddress());
      for(Person p:groups.get(1).getReceivers())
		  System.out.println("receivers\n" + p.getAddress());*/
      
      return groups;
   }
   
   //A implÃ©menter
   public List<Message> generateMessages()
   {
      //List<Prank> pranks = new ArrayList<Prank>();     
      return cm.getMessagesList();  // sortir un message random?
   }      
   
}
