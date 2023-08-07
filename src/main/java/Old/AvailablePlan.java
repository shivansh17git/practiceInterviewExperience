package Old;

public enum AvailablePlan {
    TRIAL(0.0),
    BASIC(9.99),
    STANDARD(49.99),
    PREMIUM(249.99);

    public double rate;

    AvailablePlan(double rate) {
        this.rate = rate;
    }
}
