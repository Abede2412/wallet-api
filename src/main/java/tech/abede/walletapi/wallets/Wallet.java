package tech.abede.walletapi.wallets;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.abede.walletapi.customers.Customer;
import tech.abede.walletapi.transactions.Transaction;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @Column(name = "customer_id")
    private UUID id;

    private Double balance;

    @OneToOne
    @MapsId
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "wallet")
    private List<Transaction> transactions;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setBalanceAfterTransaction(Transaction transaction){
        Double amount = transaction.getAmount()*transaction.getTransactionType().getValue();
        Double balance = this.balance + amount;

        if(balance < 0){
            throw new BalanceNotEnoughException();
        }

        this.balance = balance;
    }
    
}
