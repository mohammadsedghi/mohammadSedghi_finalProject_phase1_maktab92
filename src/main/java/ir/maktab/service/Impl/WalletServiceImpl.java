package ir.maktab.service.Impl;

import ir.maktab.base.service.BaseServiceImpl;
import ir.maktab.entity.Wallet;
import ir.maktab.repository.WalletRepository;
import ir.maktab.service.WalletService;

public class WalletServiceImpl extends BaseServiceImpl<Wallet, WalletRepository,Long >
        implements WalletService {
    public WalletServiceImpl(WalletRepository repository) {
        super(repository);
    }
}
