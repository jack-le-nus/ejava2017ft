package week3.business;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "jms/warehouse",
		activationConfig = {
			@ActivationConfigProperty(
					propertyName = "destinationType",
					propertyValue = "javax.jms.Queue"
			)
		}
)
public class WarehouseBean implements  MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage txtMsg = (TextMessage)message;
		System.out.println("... received");
		try {
			System.out.println("\t" + txtMsg.getText());
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}
}
