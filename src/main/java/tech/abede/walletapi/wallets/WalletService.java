package tech.abede.walletapi.wallets;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet createOne(Wallet wallet){
        return walletRepository.save(wallet);
    }
}