package tech.abede.walletapi.wallets;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import tech.abede.walletapi.authentication.model.UserPrincipal;

@RestController
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/wallets")
    public ResponseEntity<Wallet> getWalletInfo(){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Wallet wallet = walletService.findById(userPrincipal.getId());

        return ResponseEntity.ok().body(wallet);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/wallets/transfers")
    public ResponseEntity<WalletResponseForTransfer> transfer(@RequestParam(name = "idPenerima") UUID id, 
        @RequestParam(name = "amount") Double amount){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Wallet wallet = walletService.findById(userPrincipal.getId());
        wallet = walletService.transfer(wallet, amount, id);

        return ResponseEntity.ok().body(wallet.convertToResponseForTransfer());
    }
    

}
