package fr.insys.commerce.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailConfiguration {
	@Value("${mail.host}")
	private String mailServerHost;

	@Value("${mail.port}")
	private Integer mailServerPort;

	@Value("${mail.username}")
	private String mailServerUsername;

	@Value("${mail.password}")
	private String mailServerPassword;

	@Value("${mail.auth}")
	private String mailServerAuth;

	@Value("${mail.ttls}")
	private String mailServerStartTls;

	@Value("${mail.commercial}")
	private String mailCommercial;

	@Value("${mail.admin}")
	private String mailAdmin;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(mailServerHost);
		mailSender.setPort(mailServerPort);

		mailSender.setUsername(mailServerUsername);
		mailSender.setPassword(mailServerPassword);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", mailServerAuth);
		props.put("mail.smtp.starttls.enable", mailServerStartTls);
		props.put("mail.debug", "true");

		return mailSender;
	}
}
