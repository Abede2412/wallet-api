package tech.abede.walletapi.customers;

public class CustomerNotValidException extends RuntimeException{

    public CustomerNotValidException(String message){
        super(message);
    }

}
