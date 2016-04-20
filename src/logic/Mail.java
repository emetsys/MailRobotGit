/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Raphael
 */
public class Mail {
	private Group group;
	private Message message;
	private Person witnessCc;

	public Mail(Person witnessCc) {
		this.witnessCc = witnessCc;
	}

	public Mail(Group group, Message message, Person witnessCc) {
		this.group = group;
		this.message = message;
		this.witnessCc = witnessCc;
	}

	public String getSender() {
		return group.getSender().getAddress();
	}

	/*public String getReceivers() {
		String tmp = "";
		for (Person p : group.getReceivers())
			tmp += p.getAddress() + ";";
		return tmp;
	}*/
	public List<String> getReceivers(){ // je sais plus si c'est possible avec un foreach
		
		List<String> tmp = new LinkedList<String>();
		for (Person p : group.getReceivers())
			tmp.add(p.getAddress());
		return tmp;
	}

	public String getData() {
		String data = "";
		data += "From : " + getSender() + "\n";
		data += "To :" + getReceivers()+ "\n";
		data += "Cc :" + witnessCc.getAddress()+ "\n\n";
		data += message.getMessage();

		return data;
	}

	public String getCC() { // Un seul Cc, peut être faire en sorte de pouvoir en mettre plus
		return witnessCc.getAddress();
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
