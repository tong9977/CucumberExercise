package shop;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyStepdefs {
    private ProductCatalog catalog;
    private Order order;

    @Before
    public void setup() {
        catalog = new ProductCatalog();
        order = new Order();
    }

    @Given("^a product \"([^\"]*)\" with price (.+) and quantity (\\d+) exists$")
    public void a_product_with_price_and_quantity_exists(String name, double price, int quant) {
        catalog.addProduct(name, price, quant);
    }

    @Given("^the following products exist:$")
    public void the_following_products_exist(List<Map<String, String>> products) {
        for (Map<String, String> product : products) {
            String name = product.get("name");
            double price = Double.parseDouble(product.get("price"));
            int quantity = Integer.parseInt(product.get("quantity"));
            catalog.addProduct(name, price, quantity);
        }
    }

    @When("^I buy \"([^\"]*)\" with quantity (\\d+)$")
    public void i_buy_with_quantity(String name, int quant) {
        Product prod = catalog.getProduct(name);
        order.addItem(prod, quant);
    }

    @Then("^total should be (.+)$")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

    @Then("^\"([^\"]*)\" should have (\\d+) left$")
    public void shouldHaveStockLeft(String name, int stock) {
        int actualStock = catalog.getProduct(name).getStockQuantity();
        assertEquals(stock, actualStock);
    }
}

