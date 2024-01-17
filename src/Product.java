// Enums to represent product types
enum Product {

    PRODUCT_A("Product A", "$20"),
    PRODUCT_B("Product B", "$40"),
    PRODUCT_C("Product C", "$50");

    private String productName;
    private String price;

    Product(String productName, String price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return Integer.parseInt(price.substring(1));
    }
}
