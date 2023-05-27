package tech.abede.walletapi.transactions;

public enum TransactionType {

    KREDIT(1),
    DEBIT(-1);

    private int value;

    private TransactionType(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

}
