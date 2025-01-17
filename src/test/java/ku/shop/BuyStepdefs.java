package ku.shop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.naming.InsufficientResourcesException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;

    private Exception exception;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();

    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) {
        try {
            Product prod = catalog.getProduct(name);
            order.addItem(prod, quantity);
        } catch (InsufficientResourcesException e) {
            this.exception = e;
        }
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

    @Then("a product {string} with a stock of {int} exists")
    public void a_product_exists_after_purchasing(String name, int stock) {
        assertEquals(catalog.getProduct(name).getStock(), stock);
    }

    @Then("an exception {string} should be thrown")
    public void InsufficientResourcesException_product(String exceptionName) {
        assertEquals(exceptionName, exception.getClass().getSimpleName());
    }
}

