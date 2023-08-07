package Old;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * CostExplorer ->
 *   monthlyCostList(): Array/List of size 12 filled with cost incurred in each month of the unit year
 *   annualCost(): Total cost in a unit year
 * {planId, monthlyCost}
 * [
 *   {BASIC, 9.99},
 *   {STANDARD, 49.99},
 *   {PREMIUM, 249.99}
 * ]
 *
 *{
 * Customer -> C1
 * Old.Product -> {
 *  Name -> Jira
 *  Subscription -> { BASIC, "2022-03-10”  } //  { planId, startDate }, date in YYYY-MM-DD format
 *  }
 *  }
 *
 * GET /customer/:Id/cost?type=<monthly/yearly>
 *
 *     {
 *         yearly : amount;
 *
 *         monthly : [{
 *             month: name,
 *             cost: amount,
 *         }]
 *     }
 *     { "customer" : "a", "product" : { "name" : "jira" , "subscription" : {"plan" : "TRIAL" , "startDate" : "2022-03-10", endDate : "}}}
 */
public class Test {
    static Map<String , SubscriptionInput> userData = new HashMap<>();

    static List<Pair<Integer,Double>> getMonthlyCost(String name) {
         SubscriptionInput input = userData.get(name);
         DateTime start;

        return IntStream
                .range(1,13)
                .mapToObj(month -> {
                    DateTime startDate = input.getProduct().subscription.getStartDate();
                    Pair<Integer,Double> p;
                    if(startDate.dayOfMonth().get() > month) {
                        p = Pair.of(month , 0.0);
                    } else if (startDate.dayOfMonth().get() < month) {
                        p = Pair.of(month , input.getProduct().subscription.plan.rate);
                    }
                    else {
                        Double rate = (input.getProduct().subscription.plan.rate/30.0) * (30 - startDate.dayOfMonth().get());
                        p = Pair.of(month, rate);
                    }
                    return p;
                })
                .collect(Collectors.toList());
    }

    static Double getYearly(String name) {
        return getMonthlyCost(name)
                .stream()
                .mapToDouble(p -> p.getRight())
                .sum();
    }


    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("¡Descuento aplicado!");

        ObjectMapper o = new ObjectMapper();
        JsonNode n = o.readTree("{\"k\" : \"¡Descuento aplicado!\"}");
        System.out.println(n.get("k").asText());

/*
        Old.Subsciption s1= new Old.Subsciption(Old.AvailablePlan.BASIC , DateTime.parse("2022-10-03"));
        Old.Product p1 = new Old.Product("jira", s1);
        Old.SubscriptionInput i1 = new Old.SubscriptionInput("a",p1);

        userData.put("a",i1);

        System.out.println(getMonthlyCost(
                "a"
        ));

        System.out.println(getYearly(
                "a"
        ));
*/
    }
}


