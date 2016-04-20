/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raphael Henocq et Luciens badoux
 */
public class Group {
   private Person sender;
   private List<Person> receivers = new ArrayList<Person>();
   
   public Group(Person sender, List<Person> receivers)
   {
      this.sender = sender;
      this.receivers = receivers;
   }
   
   public Person getSender() {return sender;}
   public List<Person> getReceivers() {return receivers;}
}
