package Old;


public class Product {

    String name;

    Subsciption subscription;

    public Product(String name, Subsciption subscription) {
        this.name = name;
        this.subscription = subscription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subsciption getSubscription() {
        return subscription;
    }

    public void setSubscription(Subsciption subscription) {
        this.subscription = subscription;
    }
}
