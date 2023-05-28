package tech.abede.walletapi.customers;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.abede.walletapi.applicationuser.ApplicationUser;
import tech.abede.walletapi.transactions.Transaction;
import tech.abede.walletapi.transactions.TransactionService;
import tech.abede.walletapi.transactions.TransactionType;
import tech.abede.walletapi.wallets.Wallet;
import tech.abede.walletapi.wallets.WalletRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final TransactionService transactionService;


    public Customer createOne(Customer customer) {

        ApplicationUser applicationUser = customer.getApplicationUser();
        String hashPassword = bCryptPasswordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(hashPassword);
        customer.setApplicationUser(applicationUser);

        return customerRepository.save(customer);
    }

    public Customer getOneByid(UUID uuid){
        Customer customer = customerRepository.getReferenceById(uuid);
        return customer;
    }

    public void topUp(Customer customer, Double amount) {
        Wallet wallet = customer.getWallet();
        Transaction transaction = Transaction.builder()
            .amount(amount)
            .description("Top Up")
            .transactionType(TransactionType.KREDIT)
            .wallet(wallet)
            .build();
        
        wallet.setBalanceAfterTransaction(transaction);
        transactionService.createOne(transaction);
    }

}
