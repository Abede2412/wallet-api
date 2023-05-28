package tech.abede.walletapi.customers;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.abede.walletapi.applicationuser.ApplicationUser;
import tech.abede.walletapi.applicationuser.ApplicationUserService;
import tech.abede.walletapi.transactions.Transaction;
import tech.abede.walletapi.transactions.TransactionService;
import tech.abede.walletapi.transactions.TransactionType;
import tech.abede.walletapi.wallets.Wallet;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final TransactionService transactionService;

    private final ApplicationUserService applicationUserService;


    public Customer createOne(Customer customer) {

        customerIsValid(customer);
        ApplicationUser applicationUser = customer.getApplicationUser();
        String hashPassword = bCryptPasswordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(hashPassword);
        customer.setApplicationUser(applicationUser);

        return customerRepository.save(customer);
    }

    public void customerIsValid(Customer customer){

        if (applicationUserService.findByEmail(customer.getApplicationUser().getEmail()) != null){
            throw new CustomerNotValidException("Email already is used");
        }

        if (applicationUserService.findByUsername(customer.getApplicationUser().getUsername()) != null){
            throw new CustomerNotValidException("Username already is used");
        }

        if (customerIsExist(customer.getNIK()) != null){
            throw new CustomerNotValidException("NIK already is used");
        }
    }

    public Customer getOneByid(UUID uuid){
        Customer customer = customerRepository.getReferenceById(uuid);
        return customer;
    }

    public Customer customerIsExist(String nik){
        return customerRepository.findByNik(nik);
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
