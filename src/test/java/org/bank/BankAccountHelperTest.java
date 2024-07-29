package org.bank;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BankAccountHelperTest {
    private @InjectMocks BankAccountHelper helper;
    private @Mock BankAccount account;

    @Test
    void testConvertCurrency_BynToEuro() {
        double amount = 100;
        double expected = amount * 0.29;
        double result = helper.convertCurrency(BankAccountHelper.BYN_ACCOUNT, BankAccountHelper.EUR_ACCOUNT, amount);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    void testConvertCurrency_BynToUsd() {
        double amount = 100;
        double expected = amount * 0.31;
        double result = helper.convertCurrency(BankAccountHelper.BYN_ACCOUNT, BankAccountHelper.USD_ACCOUNT, amount);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    void testConvertCurrency_EuroToUsd() {
        double amount = 100;
//        double expected = 100 * 1.1;
        double expected = amount * 1.07;
        double result = helper.convertCurrency(BankAccountHelper.EUR_ACCOUNT, BankAccountHelper.USD_ACCOUNT, amount);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    void testConvertCurrency_EuroToByn() {
        double amount = 100;
        double expected = 100 * 3.4;
        double result = helper.convertCurrency(BankAccountHelper.EUR_ACCOUNT, BankAccountHelper.BYN_ACCOUNT, amount);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    void testConvertCurrency_UsdToByn() {
        double amount = 100;
        double expected = 100 * 3.18;
        double result = helper.convertCurrency(BankAccountHelper.USD_ACCOUNT, BankAccountHelper.BYN_ACCOUNT, amount);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    void testConvertCurrency_UsdToEuro() {
        double amount = 100;
        double expected = 100 * 0.94;
        double result = helper.convertCurrency(BankAccountHelper.USD_ACCOUNT, BankAccountHelper.EUR_ACCOUNT, amount);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testIsBalanceValidForWithdraw() {

        double amount = 60.0;

        BankAccount account = new BankAccount();
        account.setCurrentBalance(100.0);
        account.setAccountCurrency(BankAccountHelper.USD_ACCOUNT);

        boolean result = helper.isBalanceValidForWithdraw(account, amount, BankAccountHelper.USD_ACCOUNT);
        assertTrue(result);
    }

    @Test
    public void testIsAvailableForDailyWithdraw_Valid() {
        double dailyLimit = 100.0;
        double amount = 80.0;

        BankAccount account = new BankAccount();
        account.setDailyLimit(dailyLimit);

        boolean result = helper.isAvailableForDailyWithdraw(account, amount);

        assertTrue(result);
    }
}