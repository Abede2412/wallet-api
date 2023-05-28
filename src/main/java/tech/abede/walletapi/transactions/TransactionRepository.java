package tech.abede.walletapi.transactions;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tech.abede.walletapi.wallets.Wallet;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    @Query("select t from Transaction t where t.wallet = :wallet")
    Page<Transaction> findAllWithWallet(Pageable pageable, Wallet wallet);

    // @Query("select t from Transaction t join t.wallet w where w = ?5 group by and t.amount between ?1 and ?2 and t.createdAt between ?3 and ?4")
    // Page<Transaction> findAllByFilter(Double minAmount, Double maxAmount, LocalDateTime from,
    //         LocalDateTime to, Wallet wallet, Pageable pageable);

}
