// Class to encapsulate product details and gift wrapping status
public class ProductField {

    private Product product;

    private int quantity;

     private boolean isWrapped;

    public ProductField(Product product, int quantity, boolean isWrapped) {
        this.product = product;
        this.quantity = quantity;
        this.isWrapped = isWrapped;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isWrapped() {
        return isWrapped;
    }

    public void setWrapped(boolean wrapped) {
        isWrapped = wrapped;
    }
}
