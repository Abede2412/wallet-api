package tech.abede.walletapi.wallets;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.abede.walletapi.transactions.Transaction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletInfo {

    private UUID id;
    private Double balance;
    private List<Transaction> transactions;
    private LocalDateTime createdAt;
    
}
