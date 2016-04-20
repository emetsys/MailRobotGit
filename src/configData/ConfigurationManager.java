/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import logic.Message;
import logic.Person;

/**
 *
 * @author Raphael
 */
public class ConfigurationManager {
	// Raphaël
	//private final String fileName = "C:\\Heig\\Teaching-HEIGVD-RES-2016-LabBox\\RES\\Laboratoires\\Labo3\\MailRobot\\MailRobot\\Config\\config.properties";
	//private final String mailList = "C:\\Heig\\Teaching-HEIGVD-RES-2016-LabBox\\RES\\Laboratoires\\Labo3\\MailRobot\\MailRobot\\Config\\victimes.utf8";
	//private final String messagesList = "C:\\Heig\\Teaching-HEIGVD-RES-2016-LabBox\\RES\\Laboratoires\\Labo3\\MailRobot\\MailRobot\\Config\\messages.utf8";
	private final String fileName = "C:\\Users\\LBX\\Documents\\_COURS_HEIG\\S2_A2\\RES\\Teaching-HEIGVD-RES-2016-LabBox\\MailRobot\\Config\\config.properties";
	private final String mailList = "C:\\Users\\LBX\\Documents\\_COURS_HEIG\\S2_A2\\RES\\Teaching-HEIGVD-RES-2016-LabBox\\MailRobot\\Config\\victimes.utf8";
	private final String messages = "C:\\Users\\LBX\\Documents\\_COURS_HEIG\\S2_A2\\RES\\Teaching-HEIGVD-RES-2016-LabBox\\MailRobot\\Config\\messages.utf8";

	private Person witnessCC;
	private String smtpServerAddress;
	private int smtpServerPort;
	private int numberOfGroups;
	private List<Person> personList = new LinkedList<Person>();
	private List<Message> messagesList = new LinkedList<Message>();

	public ConfigurationManager() {
		BufferedReader input = null;
		String line;
		String message;
		try {
			input = new BufferedReader(new FileReader(fileName));
			line = input.readLine();
			line = line.substring(line.indexOf('=') + 1);
			smtpServerAddress = line;

			line = input.readLine();
			line = line.substring(line.indexOf('=') + 1);
			smtpServerPort = Integer.parseInt(line);

			line = input.readLine();
			line = line.substring(line.indexOf('=') + 1);
			numberOfGroups = Integer.parseInt(line);

			line = input.readLine();
			line = line.substring(line.indexOf('=') + 1);
			witnessCC = new Person(line);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			input = new BufferedReader(new FileReader(mailList));
			while ((line = input.readLine()) != null) {
				personList.add(new Person(line));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Récupérer les messages pour la prank
		String delimiter = "-----";
		try {
			input = new BufferedReader(new FileReader(messages));

			line = input.readLine();		
			
			while (line != null) {
				message = "";
				if(line.equals(delimiter)){
					line = input.readLine();
					while(line != null && (!(line.equals(delimiter)))){
						message+=line+"\n";	
						line = input.readLine();
					}
					messagesList.add(new Message(message));
				}
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public Person getWitnessCc() {
		return witnessCC;
	}

	public String getServerAddress() {
		return smtpServerAddress;
	}

	public int getServeurPort() {
		return smtpServerPort;
	}

	public int getNumberOfGroups() {
		return numberOfGroups;
	}

	public List<Person> getPersonList() {
		return personList;
	}
	public List<Message> getMessagesList(){
		return messagesList;
	}

}
