package ir.maktab.service;


import ir.maktab.entity.Wallet;

public interface WalletService  {
    Wallet createWallet();
    Double payByWallet(Double cost);
    Double payByPaymentGateway(Double cost);
}
