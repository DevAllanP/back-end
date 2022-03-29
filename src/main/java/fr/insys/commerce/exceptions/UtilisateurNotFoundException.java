package fr.insys.commerce.exceptions;

public class UtilisateurNotFoundException extends  RuntimeException{

    public UtilisateurNotFoundException(String message){
        super(message);
    }
}
