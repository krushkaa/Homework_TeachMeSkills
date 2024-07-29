package org.bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountManagerTest {
    @InjectMocks
    private BankAccountManager manager;
    @Mock
    private BankAccountHelper helper;

    @Test
    public void testTransferMoney() throws InvalidBankOperationException {
        BankAccount toAccount = new BankAccount();
        BankAccount fromAccount = new BankAccount();

        toAccount.setAccountCurrency(BankAccountHelper.USD_ACCOUNT);
        toAccount.setDailyLimit(500);
        fromAccount.setAccountCurrency(BankAccountHelper.USD_ACCOUNT);
        fromAccount.setDailyLimit(500);
        fromAccount.setCurrentBalance(1000);

        when(helper.isBalanceValidForWithdraw(fromAccount, 100.0, BankAccountHelper.USD_ACCOUNT))
                .thenReturn(true);
        when(helper.isAvailableForDailyWithdraw(fromAccount, 100.0))
                .thenReturn(true);
        when(helper.convertCurrency(BankAccountHelper.USD_ACCOUNT, BankAccountHelper.USD_ACCOUNT, 100.0))
                .thenReturn(100.0);

        manager.transferMoney(fromAccount, toAccount, 100);

        assertEquals(900, fromAccount.getCurrentBalance());
        assertEquals(500, fromAccount.getDailyLimit());
    }

    @Test
    public void testWithdrawMoney() throws InvalidBankOperationException {
        BankAccount account = mock(BankAccount.class);

        account.getCurrentBalance();
        account.getDailyLimit();
        account.getAccountCurrency();

        when(helper.isBalanceValidForWithdraw(account, 100, BankAccountHelper.USD_ACCOUNT)).thenReturn(true);
        when(helper.isAvailableForDailyWithdraw(account, 100)).thenReturn(true);
        when(helper.convertCurrency(BankAccountHelper.USD_ACCOUNT, BankAccountHelper.USD_ACCOUNT, 100)).thenReturn(100.0);

        double withdrawnAmount = manager.withdrawMoney(account, 100, BankAccountHelper.USD_ACCOUNT);

        verify(account).setCurrentBalance(900.0);
        verify(account).setDailyLimit(400.0);
        assertEquals(100, withdrawnAmount);
    }

    @Test
    public void testAddMoney() {
        BankAccount account = new BankAccount();
        account.setCurrentBalance(0);
        account.setAccountCurrency(BankAccountHelper.USD_ACCOUNT);

        double amountToAdd = new Random().nextDouble();

        when(helper.convertCurrency(account.getAccountCurrency(), account.getAccountCurrency(), amountToAdd))
                .thenReturn(amountToAdd);

        manager.addMoney(account, amountToAdd, BankAccountHelper.USD_ACCOUNT);

        verify(helper, times(1)).convertCurrency(account.getAccountCurrency(), account.getAccountCurrency(), amountToAdd);
        assertEquals(amountToAdd, account.getCurrentBalance(), 0.001);
    }
}