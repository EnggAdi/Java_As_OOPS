package Assignments;

import java.util.Scanner;

public class InventoryManagementSystem {
    private static final int MAX_PRODUCTS = 100;
    private static final String[] products = new String[MAX_PRODUCTS];
    private static final int[] quantities = new int[MAX_PRODUCTS];
    private static final double[] costs = new double[MAX_PRODUCTS];
    private static int totalProducts = 0;

    public static void main(String[] args) {
        // Add some default products
        addProduct("Laptop", 10, 999.99);
        addProduct("Mobile Phone", 20, 599.99);
        addProduct("Headphones", 30, 49.99);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    viewProductList();
                    break;
                case 2:
                    viewProductCount(scanner);
                    break;
                case 3:
                    viewProductDetails(scanner);
                    break;
                case 4:
                    editProduct(scanner);
                    break;
                case 5:
                    updateInventory(scanner);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void displayMenu() {
        System.out.println("========== Inventory Management System ==========");
        System.out.println("1. Product List");
        System.out.println("2. Product Count");
        System.out.println("3. View Product Details");
        System.out.println("4. Edit Product");
        System.out.println("5. Update Inventory");
        System.out.println("0. Exit");
        System.out.println("===============================================");
    }

    public static void viewProductList() {
        System.out.println("========== Product List ==========");
        if (totalProducts == 0) {
            System.out.println("No products available.");
        } else {
            for (int i = 0; i < totalProducts; i++) {
                System.out.println(products[i]);
            }
        }
        System.out.println("=================================");
    }

    public static void viewProductCount(Scanner scanner) {
        System.out.print("Enter the product name: ");
        String productName = scanner.nextLine();

        int count = getProductCount(productName);
        if (count == -1) {
            System.out.println("Product not found!");
        } else {
            System.out.println("Product count for " + productName + ": " + count);
        }
    }

    public static void viewProductDetails(Scanner scanner) {
        System.out.print("Enter the product name: ");
        String productName = scanner.nextLine();

        int index = getProductIndex(productName);
        if (index == -1) {
            System.out.println("Product not found!");
        } else {
            System.out.println("========== Product Details ==========");
            System.out.println("Product Name: " + products[index]);
            System.out.println("Product Specification: ...");
            System.out.println("Product Cost: $" + costs[index]);
            System.out.println("Product Count: " + quantities[index]);
            System.out.println("====================================");
        }
    }

    public static void editProduct(Scanner scanner) {
        System.out.print("Enter the product name: ");
        String productName = scanner.nextLine();

        int index = getProductIndex(productName);
        if (index == -1) {
            System.out.println("Product not found!");
        } else {
            System.out.println("========== Edit Product ==========");
            System.out.println("Current Product Details:");
            System.out.println("Product Name: " + products[index]);
            System.out.println("Product Specification: ...");
            System.out.println("Product Cost: $" + costs[index]);
            System.out.println("Product Count: " + quantities[index]);
            System.out.println("=================================");

            System.out.println("Enter new product details:");
            System.out.print("Product Specification: ");
            scanner.nextLine();  // Consume newline character

            System.out.print("Product Cost: $");
            double cost = scanner.nextDouble();

            System.out.print("Product Count: ");
            int count = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            costs[index] = cost;
            quantities[index] = count;

            System.out.println("Product details updated successfully.");
        }
    }

    public static void updateInventory(Scanner scanner) {
        System.out.print("Enter the product name: ");
        String productName = scanner.nextLine();

        int index = getProductIndex(productName);
        if (index == -1) {
            System.out.println("Product not found!");
        } else {
            System.out.print("Enter the quantity to add/delete (use positive/negative values): ");
            int quantity = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            quantities[index] += quantity;
            System.out.println("Inventory updated successfully.");
        }
    }

    public static void addProduct(String productName, int quantity, double cost) {
        products[totalProducts] = productName;
        quantities[totalProducts] = quantity;
        costs[totalProducts] = cost;
        totalProducts++;
    }// This method can be used if needed to add new products in the list

    public static int getProductCount(String productName) {
        for (int i = 0; i < totalProducts; i++) {
            if (products[i].equalsIgnoreCase(productName)) {
                return quantities[i];
            }
        }
        return -1;
    }

    public static int getProductIndex(String productName) {
        for (int i = 0; i < totalProducts; i++) {
            if (products[i].equalsIgnoreCase(productName)) {
                return i;
            }
        }
        return -1;
    }
}
