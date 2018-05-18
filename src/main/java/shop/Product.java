package shop;

public class Product {
    private double price;
    private String name;
    private int stockQuantity = 0;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, int stockQuantity) {
        this.price = price;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Price must be not be negative zero");
        this.price = price;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
