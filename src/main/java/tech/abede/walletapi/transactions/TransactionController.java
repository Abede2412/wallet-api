package tech.abede.walletapi.transactions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import tech.abede.walletapi.authentication.model.UserPrincipal;
import tech.abede.walletapi.wallets.Wallet;
import tech.abede.walletapi.wallets.WalletService;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    private final WalletService walletService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/wallets/transactions")
    public ResponseEntity<Page<Transaction>> getTransactions(@RequestParam(name = "page", required = false, defaultValue = "1") Optional<Integer> optionalPage
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Wallet wallet = walletService.findById(userPrincipal.getId());

        Pageable pageable = PageRequest.of(optionalPage.get()-1, 5, Sort.by("createdAt").descending());
        Page<Transaction> transactions = transactionService.getAll(pageable, wallet);
        return ResponseEntity.ok().body(transactions);



    }

    // @SecurityRequirement(name = "bearerAuth")
    // @GetMapping("/wallets/transactions")
    // public ResponseEntity<Page<Transaction>> getTransactions(
    //     @RequestParam(name = "transaction type", required = false) Optional<TransactionType> transactionType,
    //     @RequestParam(name = "min", required = false) Optional<Double> minAmount,
    //     @RequestParam(name = "max", required = false) Optional<Double> maxAmount,
    //     @RequestParam(name = "from", required = false) Optional<LocalDateTime> from,
    //     @RequestParam(name = "to", required = false) Optional<LocalDateTime> to,
    //     @RequestParam(name = "sortTransactionType", required = false) Optional<Direction> sortTransactionType,
    //     @RequestParam(name = "sortAmount", required = false) Optional<Direction> sortAmount,
    //     @RequestParam(name = "sortDate", required = false, defaultValue = "DESC") Optional<Direction> sortDate,
    //     @RequestParam(name = "page", defaultValue = "1") Optional<Integer> optionalPage
    //     ){
        
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    //     Wallet wallet = walletService.findById(userPrincipal.getId());

    //     Double min = 0.0;
    //     Double max = Double.MAX_VALUE;
    //     LocalDateTime fromDate = wallet.getTransactions().get(0).getCreatedAt();
    //     LocalDateTime toDate = LocalDateTime.now();

    //     if (minAmount.isPresent()){
    //         min = minAmount.get();
    //     }
    //     if (maxAmount.isPresent()){
    //         max = maxAmount.get();
    //     }
    //     if (from.isPresent()){
    //        fromDate = from.get();
    //     }
    //     if (to.isPresent()){
    //         toDate = to.get();
    //     }

    //     if (max < min){
    //         throw new FilterNotValidException("range amount is not valid");
    //     }

    //     if (Duration.between(fromDate, toDate).isNegative()){
    //         throw new FilterNotValidException("range transaction date is not valid");
    //     }

    //     List<Order> orders = new ArrayList<Order>();

    //     Order order = new Order(sortDate.get(), "createdAt");
    //     orders.add(order);
        
    //     if (sortTransactionType.isPresent()){
    //         Order newOrder = new Order(sortTransactionType.get(), "transactionType");
    //         orders.add(newOrder);
    //     }

    //     if (sortAmount.isPresent()){
    //         Order newOrder = new Order(sortAmount.get(), "amount");
    //         orders.add(newOrder);
    //     }

    //     Pageable pageable = PageRequest.of(optionalPage.get()-1, 5, Sort.by(orders));
    //     //if (transactionType.isPresent()){
    //         Page<Transaction> transactions = transactionService.getByFilter(
    //             min,
    //             max,
    //             fromDate,
    //             toDate,
    //             wallet,
    //             pageable
    //         );
    //     //}
    //     return ResponseEntity.ok().body(transactions);
    // }

}
