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
      
      List<Person> victims;
      int i;
      
      
      for(i = 0; i < nbGroups-1; i++)
      {
    	  victims = new ArrayList<Person>();
    	  for(int j = 1+i*nbPeoplePerGroup; j<(i+1)*nbPeoplePerGroup; j++)
    	  {
    		  victims.add(personList.get(j));
    	  }
                  	  
    	  groups.add(new Group(personList.get(i*nbPeoplePerGroup), victims));
    	    	  
      }
      
      // pour le dernier groupe plus grand
      victims = new ArrayList<Person>();
      for(int j = 1+i*nbPeoplePerGroup; j<(i+1)*nbPeoplePerGroup+personList.size()%nbPeoplePerGroup; j++)
	  {
		  victims.add(personList.get(j));		  
	  }     
      groups.add(new Group(personList.get(i*nbPeoplePerGroup), victims));
      return groups;
   }
   
   public List<Message> generateMessages()
   {
      //List<Prank> pranks = new ArrayList<Prank>();     
      return cm.getMessagesList();  // sortir un message random?
   }      
   
}
