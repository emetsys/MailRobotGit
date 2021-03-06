/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;

/**
 *
 * @author Raphael Henocq et Luciens badoux
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

	public List<Person> getReceivers(){ 
		
		return group.getReceivers();
	}

	public String getData() {
		String data = "";
		data += "From: " + getSender() + "\n";
		data += "To: ";
		for(Person p:getReceivers())
			data+= p.getAddress()+ ",";
		data = data.substring(0, data.length()-1);
		data += "\n";
		data += "Cc: " + witnessCc.getAddress()+ "\n";
		data += message.getMessage();

		return data;
	}

	public String getCC() { 
		return witnessCc.getAddress();
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
