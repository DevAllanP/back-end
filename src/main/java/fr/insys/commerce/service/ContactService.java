package fr.insys.commerce.service;

import fr.insys.commerce.dto.ContactRequest;
import fr.insys.commerce.dto.UtilisateurDto;


public interface ContactService {
	String contact(ContactRequest form);
	String lostmdp();
}
