package fr.insys.commerce.exceptions;

import javax.mail.SendFailedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(EmailAlreadyUsedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorResponse handleEmailAlreadyUsedException(EmailAlreadyUsedException ex) {
		return setErrorResponse(ex, HttpStatus.FORBIDDEN, "L'e-mail a déjà été utilisé.");
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleNotFoundException(NotFoundException ex) {
		return setErrorResponse(ex, HttpStatus.NOT_FOUND, "L'objet est introuvable.");
	}
	
	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ErrorResponse handleAuthenticationException(AuthenticationException ex) {
		return setErrorResponse(ex, HttpStatus.UNAUTHORIZED, "Accès non autorisé.");
	}
	
	@ExceptionHandler(NotSameRoleException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorResponse handleNotSameRoleException(NotSameRoleException ex) {
		return setErrorResponse(ex, HttpStatus.FORBIDDEN, "Accès refusé.");
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
		return setErrorResponse(ex, HttpStatus.FORBIDDEN, "Accès refusé.");
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		return setErrorResponse(ex, HttpStatus.BAD_REQUEST, "Données non conformes.");
	}
	
	@ExceptionHandler(SendFailedException.class)
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	public ErrorResponse handleSendFailedException(SendFailedException ex) {
		return setErrorResponse(ex, HttpStatus.BAD_GATEWAY, "L'e-mail n'a pas pu être envoyé.");
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse handleException(Exception ex) {
		return setErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Erreur interne.");
	}
	
	private ErrorResponse setErrorResponse(Exception ex, HttpStatus forbidden, String message) {
		logger.error("Exception: {}", ex.getClass().getName());
		logger.error("Message: {}", ex.getMessage());
		logger.error("Trace: ", ex);
		ErrorResponse err = new ErrorResponse();
		err.setMessage(message);
		err.setStatus(forbidden.value());
		return err;
	}
}
