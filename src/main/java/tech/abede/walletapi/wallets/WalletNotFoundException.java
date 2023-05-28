package tech.abede.walletapi.wallets;

public class WalletNotFoundException extends RuntimeException {

    public WalletNotFoundException(){
        super("Wallet not found");
    }
}
