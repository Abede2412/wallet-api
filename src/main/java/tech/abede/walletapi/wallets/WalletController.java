package tech.abede.walletapi.wallets;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    
    
}
