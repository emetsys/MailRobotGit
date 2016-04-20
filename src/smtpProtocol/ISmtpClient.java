package smtpProtocol;

import java.io.IOException;

/**
*
* @author Raphael Henocq et Luciens badoux
*/

import logic.Mail;

public interface ISmtpClient {
	
	public void sendMail(Mail mail) throws IOException;

}
