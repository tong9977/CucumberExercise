package atm;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UseStepdefs {
    private ATM atm;

    @Given("^the following accounts exist:$")
    public void theFollowingAccountsExist(List<Map<String, String>> accounts) {
        Bank bank = new Bank();
        for (Map<String, String> account : accounts) {
            int id = Integer.parseInt(account.get("id"));
            int pin = Integer.parseInt(account.get("pin"));
            double balance = Double.parseDouble(account.get("balance"));
            bank.addCustomer(new Customer(id, pin, balance));
        }
        atm = new ATM(bank);
    }

    @When("^customer id (\\d+) login with pin (\\d+)$")
    public void customerIdLoginWithPin(int id, int pin) {
        atm.validateCustomer(id, pin);
    }

    @When("^deposit (.+)$")
    public void deposit(double amount) {
        atm.deposit(amount);
    }

    @When("^withdraw (.+)$")
    public void withdraw(double amount) {
        atm.withdraw(amount);
    }

    @When("^transfer (.+) to customer (\\d+)$")
    public void transferToCustomer(double amount, int id) {
        atm.transfer(id, amount);
    }

    @When("^customer leaves the ATM$")
    public void customerLeavesTheATM() {
        atm.reset();
    }

    @Then("^the balance should be (.+)$")
    public void theBalanceShouldBe(double balance) {
        assertEquals(balance, atm.getBalance());
    }
}
