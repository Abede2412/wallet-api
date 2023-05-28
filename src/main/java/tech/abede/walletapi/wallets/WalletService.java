package tech.abede.walletapi.wallets;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.abede.walletapi.transactions.Transaction;
import tech.abede.walletapi.transactions.TransactionService;
import tech.abede.walletapi.transactions.TransactionType;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    private final TransactionService transactionService;

    public Wallet createOne(Wallet wallet){
        return walletRepository.save(wallet);
    }

    public Wallet findById(UUID id) {
        return walletRepository.getReferenceById(id);
    }

    public Wallet transfer(Wallet wallet, Double amount, UUID walletId){
        Wallet walletTujuan = findById(walletId);
        if(walletTujuan == null){
            throw new WalletNotFoundException();
        }
        Transaction transactionDebit = transactionService.createOne(wallet, amount, "transfer ke "+ walletTujuan.getCustomer().getName(),TransactionType.DEBIT);
        Transaction transactionKredit = transactionService.createOne(walletTujuan, amount, "terima dari "+wallet.getCustomer().getName(), TransactionType.KREDIT);      

        return wallet;
    }
}
