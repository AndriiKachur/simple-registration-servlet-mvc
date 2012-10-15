package service;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Service to handle customer data
 * 
 * @author andrey
 * 
 */
public class CustomerService {

	private static final String XML_DATABASE_FILE = "/database.xml";

	/**
	 * Check if user exists in xml file
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	public boolean checkXmlCredentials(String user, String password) {
		boolean result = false;
		if (user == null || password == null) return result;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			UsersXmlHandler handler = new UsersXmlHandler();
			saxParser.parse(getClass().getResourceAsStream(XML_DATABASE_FILE),
					handler);

			if (handler.getUsers().get(user) != null
					&& handler.getUsers().get(user).equals(password)) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: log exception
		}
		return result;
	}

}

/**
 * Handler to parse users xml to Map<Username, Password> User getUsers() after
 * parse process.
 */
class UsersXmlHandler extends DefaultHandler {
	private String username = null;
	private String password = null;

	private boolean user = false;
	private boolean bUsername = false;
	private boolean bPassword = false;

	private Map<String, String> users = new HashMap<String, String>();

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("user")) {
			user = true;
		}
		if (qName.equalsIgnoreCase("username")) {
			bUsername = true;
		}
		if (qName.equalsIgnoreCase("password")) {
			bPassword = true;
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("user")) {
			user = false;
			if (username != null && password != null) {
				users.put(username, password);
			}
			username = null;
			password = null;
		}
		if (qName.equalsIgnoreCase("username")) {
			bUsername = false;
		}
		if (qName.equalsIgnoreCase("password")) {
			bPassword = false;
		}
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {
		if (bUsername) {
			username = new String(ch, start, length);
			bUsername = false;
		}
		if (bPassword) {
			password = new String(ch, start, length);
			bPassword = false;
		}
	}

	/**
	 * Retrieve users map
	 * 
	 * @return Map<Username, Password>
	 */
	public Map<String, String> getUsers() {
		return users;
	}
};
