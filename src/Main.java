import Observer_Observable.Simple;
import Observer_Observable.Task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Simple s1 = new Simple(new BigDecimal("2"));
        Simple s2 = new Simple(new BigDecimal("4"));
        System.out.println("el coste de s1 es: " + s1.costInEuros());
        System.out.println("el coste de s2 es: " + s2.costInEuros());

        List<Task> tasks = new ArrayList<Task>();
    }
}