package Old;


public class SubscriptionInput {
    String customer;

    Product product;

    public SubscriptionInput(String customer, Product product) {
        this.customer = customer;
        this.product = product;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
