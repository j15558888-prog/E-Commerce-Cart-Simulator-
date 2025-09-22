import java.util.ArrayList;
import java.util.Scanner;

// Product class
class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + ". " + name + " - ₹" + price;
    }
}

// CartItem class
class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getTotalPrice() {
        return product.price * quantity;
    }

    @Override
    public String toString() {
        return product.name + " x " + quantity + " = ₹" + getTotalPrice();
    }
}

// Cart class
class Cart {
    ArrayList<CartItem> items = new ArrayList<>();

    void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.product.id == product.id) {
                item.quantity += quantity;
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    void removeItem(int productId) {
        items.removeIf(item -> item.product.id == productId);
    }

    void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }
        System.out.println("\n🛒 Your Cart:");
        for (CartItem item : items) {
            System.out.println(item);
        }
        System.out.println("Total: ₹" + getTotalAmount());
    }

    double getTotalAmount() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    void checkout() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout!");
        } else {
            System.out.println("Final Bill: ₹" + getTotalAmount());
            System.out.println("✅ Thank you for shopping!");
            items.clear();
        }
    }
}

// Main Application
public class ECommerceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Product Catalog
        ArrayList<Product> catalog = new ArrayList<>();
        catalog.add(new Product(1, "Laptop", 55000));
        catalog.add(new Product(2, "Smartphone", 30000));
        catalog.add(new Product(3, "Headphones", 2500));
        catalog.add(new Product(4, "Keyboard", 1200));
        catalog.add(new Product(5, "Mouse", 800));

        Cart cart = new Cart();
        int choice;

        do {
            System.out.println("\n===== E-Commerce Cart Simulator =====");
            System.out.println("1. Show Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. Remove from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n📦 Product Catalog:");
                    for (Product p : catalog) {
                        System.out.println(p);
                    }
                    break;
                case 2:
                    System.out.print("Enter product ID to add: ");
                    int id = sc.nextInt();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    Product selected = null;
                    for (Product p : catalog) {
                        if (p.id == id) {
                            selected = p;
                        }
                    }
                    if (selected != null) {
                        cart.addItem(selected, qty);
                        System.out.println(qty + " x " + selected.name + " added to cart.");
                    } else {
                        System.out.println("Invalid Product ID!");
                    }
                    break;
                case 3:
                    System.out.print("Enter product ID to remove: ");
                    int removeId = sc.nextInt();
                    cart.removeItem(removeId);
                    System.out.println("Removed item with ID " + removeId + " from cart.");
                    break;
                case 4:
                    cart.displayCart();
                    break;
                case 5:
                    cart.checkout();
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 6);

        sc.close();
    }
}