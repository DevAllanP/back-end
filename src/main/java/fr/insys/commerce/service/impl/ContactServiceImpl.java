package fr.insys.commerce.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



import fr.insys.commerce.controllers.UtilisateurController;
import fr.insys.commerce.dto.ContactRequest;
import fr.insys.commerce.dto.EContactRequest;
import fr.insys.commerce.dto.UtilisateurDto;
import fr.insys.commerce.mapper.UtilisateurMapper;
import fr.insys.commerce.repository.UtilisateurRepository;
import fr.insys.commerce.security.jwt.JwtUtils;
import fr.insys.commerce.service.ContactService;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
	private final UtilisateurController utilisateurController;
	private final JwtUtils jwtUtils;	
	private final UtilisateurRepository utilisateurRepository;	
    private final JavaMailSender emailSender;

	@Value("${mail.commercial}")
	private String mailCommercial;

	@Value("${mail.admin}")
	private String mailAdmin;
	
	private String getSender() {
		Object usr = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usr instanceof String ? "un utilisateur non connecté" : ((UserDetails) usr).getUsername();
	}

	@Override
	public String contact(ContactRequest form) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@waoo.fr");
        message.setTo(mailCommercial);
        message.setSubject(form.type().getContent()); 
        message.setText("Envoyé par " + getSender() + "\n\n" + form.objet() + "\n\n" + form.message());
        emailSender.send(message);
		return "Votre message a été envoyé et sera examiné au plus vite !";
	}

	@Override
	public String lostmdp() {
		Object usr = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@waoo.fr");
        message.setTo(mailCommercial);
        
        message.setSubject("Mots de passe oublié"); 
        message.setText("Vous avez oublié votre mots de passe " );
        emailSender.send(message);
      
		return "Votre message a été envoyé et sera examiné au plus vite !";
	}
}
