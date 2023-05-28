package tech.abede.walletapi.transactions;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.abede.walletapi.wallets.Wallet;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction createOne(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Transaction createOne(Wallet wallet, Double amount, String message, TransactionType transactionType){
        Transaction debit = Transaction.builder()
            .amount(amount)
            .transactionType(transactionType)
            .description(message)
            .wallet(wallet)
            .build();

        wallet.setBalanceAfterTransaction(debit);
        Transaction saveTransaction = createOne(debit);
        return saveTransaction;
    }

    // public Page<Transaction> getByFilter(Double minAmount, Double maxAmount, LocalDateTime from,
    //         LocalDateTime to, Wallet wallet, Pageable pageable) {
    //     return transactionRepository.findAllByFilter(
    //         minAmount,
    //         maxAmount,
    //         from,
    //         to,
    //         wallet,
    //         pageable
    //     );
    // }

    public Page<Transaction> getAll(Pageable pageable, Wallet wallet) {
        return transactionRepository.findAllWithWallet(pageable, wallet);
    }    
}
