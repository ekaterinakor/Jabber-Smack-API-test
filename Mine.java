package smackapitest;

import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;

import java.io.IOException;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

public class Mine //implements Runnable 
{

	private static XMPPTCPConnectionConfiguration.Builder connConfig;
	private static XMPPTCPConnection connection;
	
	public static void Mymessage(String nick, String password, String domain, String server, int port) throws Exception
	{
		connConfig = XMPPTCPConnectionConfiguration.builder();
		connConfig.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        connConfig.setUsernameAndPassword( nick, password);
        connConfig.setXmppDomain(JidCreate.from(nick+"@"+domain).asDomainBareJid());
        connConfig.setHost(server);
        connConfig.setPort(port);
        //connConfig.setDebuggerEnabled(true);
       // connConfig.setSocketFactory(SSLSocketFactory.getDefault());
        //SASLAuthentication.blacklistSASLMechanism("SCRAM-SHA-1");
        //SASLAuthentication.blacklistSASLMechanism("X-OAUTH2");
        //SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");
        //SASLAuthentication.blacklistSASLMechanism("PLAIN");
        connection=new XMPPTCPConnection(connConfig.build());
        System.out.println(1);
        connection.setPacketReplyTimeout(10000);
       	connection.connect();
        System.out.println(2);
        connection.login();
        System.out.println(3);
       	//System.out.println(connection.getConnectionCounter());
       	ChatManager chatmanager = ChatManager.getInstanceFor(connection);
		Chat newChat = chatmanager.createChat(JidCreate.from("ekaterina_kor@jabber.ru").asEntityJidIfPossible());
		 System.out.println(4);
		newChat.sendMessage("dgdfgdf");
		 System.out.println(5);
        Chat chatr = chatmanager.createChat(JidCreate.from("ekaterina_kor@jabber.ru").asEntityJidIfPossible(), new ChatMessageListener() { 
            public void processMessage(Chat chat, Message message) {
                    System.out.println("Received message: " + message);
            }});
        System.out.println(6);
        Thread.sleep(100000);
        connection.disconnect();

	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {
        	Mymessage("ekaterinatest3","ekaterinatest3","jabber.ru","jabber.ru",5222);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
