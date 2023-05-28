package tech.abede.walletapi.transactions;

public class FilterNotValidException extends RuntimeException{

    public FilterNotValidException(String message){
        super(message);
    }

}
