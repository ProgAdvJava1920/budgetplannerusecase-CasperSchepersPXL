package be.pxl.student.dao;

import be.pxl.student.entity.Account;

public interface AccountDao {
    Account findAccountByName(String name);

    Account findAccountByIBAN(String counterAccountIBAN);

    Account createAccount(Account counterAccount);

    void updateAccount(Account account);
}
