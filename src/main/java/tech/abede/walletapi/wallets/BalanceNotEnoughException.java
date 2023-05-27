package tech.abede.walletapi.wallets;

public class BalanceNotEnoughException extends RuntimeException{

    public BalanceNotEnoughException(){
        super("Your balance not enough");
    }

}
