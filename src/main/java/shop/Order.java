package shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private List<OrderItem> items;
    private LocalDateTime date;

    public Order(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.date = LocalDateTime.now();
    }

    public Order() {
        this.id = 0;
        this.items = new ArrayList<>();
        this.date = LocalDateTime.now();
    }

    public void addItem(Product prod, int quantity) {
        if (quantity <= prod.getStockQuantity()) {
            prod.setStockQuantity(prod.getStockQuantity() - quantity);
            items.add(new OrderItem(prod, quantity));
        } else {
            throw new RuntimeException("Not enough product(s) in stock");
        }
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

}
