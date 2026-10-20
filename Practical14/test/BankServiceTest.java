import model.Account;
import repository.Repository;
import service.BankService;
import service.TransactionProcessor;
import exception.InsufficientFundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankServiceTest {
    @Test
    void depositIncreasesBalance() throws Exception {
        Repository<Account> repository = new Repository<>();
        TransactionProcessor processor = new TransactionProcessor();
        BankService bank = new BankService(repository, processor);

        bank.openAccount("AC0001", "Riya", 1000);
        bank.deposit("AC0001", 500);

        assertEquals(1500, repository.findById("AC0001").getBalance());
    }

    @Test
    void overWithdrawThrows() throws Exception {
        Repository<Account> repository = new Repository<>();
        TransactionProcessor processor = new TransactionProcessor();
        BankService bank = new BankService(repository, processor);

        bank.openAccount("AC0001", "Riya", 1000);

        assertThrows(InsufficientFundsException.class, () -> bank.withdraw("AC0001", 1500));
    }

    @Test
    void concurrentDepositsAreCorrect() throws Exception {
        Repository<Account> repository = new Repository<>();
        TransactionProcessor processor = new TransactionProcessor();
        BankService bank = new BankService(repository, processor);

        bank.openAccount("AC0001", "Riya", 0);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> processor.deposit("AC0001", 1));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(10, repository.findById("AC0001").getBalance());
    }
}
