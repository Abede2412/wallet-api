package tech.abede.walletapi.transactions;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction createOne(Transaction transaction){
        return transactionRepository.save(transaction);
    }



    
}
