package smtpProtocol;

import java.io.IOException;

import logic.Mail;

public interface ISmtpClient {
	
	public void sendMail(Mail mail) throws IOException;

}
