package fr.insys.commerce.exceptions;

public class NotFoundExceptionWithMsg extends  RuntimeException{

    public NotFoundExceptionWithMsg(String msg){
        super(msg);
    }
}
