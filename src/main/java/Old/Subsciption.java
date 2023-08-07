package Old;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;


public class Subsciption {
    AvailablePlan plan;

    DateTime startDate;

    DateTime endDate;

    AvailablePlan queuedPlan;

    DateTime startDateQueedPlan;

    @JsonProperty
    void setPlan(String s) {
        plan = AvailablePlan.valueOf(s);
    }

    public Subsciption(AvailablePlan plan, DateTime startDate) {
        this.plan = plan;
        this.startDate = startDate;
    }


    public AvailablePlan getPlan() {
        return plan;
    }

    public void setPlan(AvailablePlan plan) {
        this.plan = plan;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }
}
