package tech.abede.walletapi.wallets;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.abede.walletapi.customers.Customer;
import tech.abede.walletapi.transactions.Transaction;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Customer customer;

    @OneToMany(mappedBy = "wallet")
    private List<Transaction> transactions;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
}
