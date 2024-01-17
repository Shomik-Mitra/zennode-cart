import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {

        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the Product A quantity");
            int QA=sc.nextInt();
            System.out.println("Enter if that product A is wrapped");
            boolean isWrapA=sc.nextBoolean();
            ProductField productField_A=new ProductField(Product.PRODUCT_A,QA,isWrapA);
            System.out.println("Enter the Product B quantity");
            int QB=sc.nextInt();
            System.out.println("Enter if that product B is wrapped");
            boolean isWrapB=sc.nextBoolean();
            ProductField productField_B=new ProductField(Product.PRODUCT_B,QB,isWrapB);
            System.out.println("Enter the Product C quantity");
            int QC=sc.nextInt();
            System.out.println("Enter if that product C is wrapped");
            boolean isWrapC=sc.nextBoolean();
            ProductField productField_C=new ProductField(Product.PRODUCT_C,QC,isWrapC);
            System.out.println("\n");
            displayProduct(productField_A,productField_B,productField_C);

        }
        //To display all the data for carts
        private static void displayProduct(ProductField productFieldA, ProductField productFieldB, ProductField productFieldC) {
                System.out.println("ProductName :"+ productFieldA.getProduct().getProductName());
                System.out.println("Quantity :"+ productFieldA.getQuantity());
                int totalAmountOfA=productFieldA.getQuantity()*productFieldA.getProduct().getPrice();
                System.out.println("Total Amount of "+ productFieldA.getProduct().getProductName()+" : "+"$"+totalAmountOfA);
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("ProductName :"+ productFieldB.getProduct().getProductName());
                System.out.println("Quantity :"+ productFieldB.getQuantity());
                int totalAmountOfB=productFieldB.getQuantity()*productFieldB.getProduct().getPrice();
                System.out.println("Total Amount of "+ productFieldB.getProduct().getProductName()+" : "+"$"+totalAmountOfB);
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("ProductName :"+ productFieldC.getProduct().getProductName());
                System.out.println("Quantity :"+productFieldC.getQuantity());
                int totalAmountOfC=productFieldC.getQuantity()*productFieldC.getProduct().getPrice();
                System.out.println("Total Amount of "+ productFieldC.getProduct().getProductName()+" : "+"$"+totalAmountOfC);
                System.out.println("------------------------------------------------------------------------------------------");
                double subtotal=totalAmountOfA+totalAmountOfB+totalAmountOfC;
                System.out.println("SUBTOTAL"+": "+"$"+subtotal);
                System.out.println("-------------------------------------------------------------------------------------------");
                List<ProductField> productFields = List.of(productFieldA, productFieldB, productFieldC);
                int totalQuantity = productFieldA.getQuantity() + productFieldB.getQuantity() + productFieldC.getQuantity();

                DiscountRule maxDiscount = findApplicableDiscount(subtotal, totalQuantity, productFields);
                double discountAmount=0;
                if (maxDiscount != null) {
                     discountAmount = calculateDiscountAmount(maxDiscount, subtotal, totalQuantity, productFields);
                    System.out.println("Discount Name: " + maxDiscount.getType());
                    System.out.println("Discount Value: " + discountAmount);
                    System.out.println("Subtotal after Discount: $" + (subtotal - discountAmount));
                } else {
                    System.out.println("No applicable discounts.");
                }
                System.out.println("---------------------------------------------------------------------------------------------");
                double shippingFee=totalShippingCost(totalQuantity);
                double giftWrapFee=TotalGiftWrapFee(productFields);
                System.out.println("ShippingFee : "+"$"+shippingFee+"\n"+"Gift_Wrapped_Fee :"+"$"+giftWrapFee);
                System.out.println("----------------------------------------------------------------------------------------------");
                double total=subtotal+shippingFee+giftWrapFee-discountAmount;
                System.out.println("TOTAL : "+"$"+total);

              }

        //Method to find the GiftWrapFee
        private static double TotalGiftWrapFee(List<ProductField> productFields) {
            double res=0;
            for(ProductField productField:productFields){
                if(productField.isWrapped()){
                    res+=productField.getQuantity();
                }
            }
            return res;
        }

        //Method to find the Shipping Cost
        private static double totalShippingCost(int totalQuantity) {
            double res=totalQuantity*0.5;
            return res;

        }

    // Method to find the applicable discount with the highest discount amount
         private static DiscountRule findApplicableDiscount(double subtotal, int totalQuantity, List<ProductField> productFields) {
            List<DiscountRule> applicableDiscounts = new ArrayList<>();

            //ADD FLAT_10_DISCOUNT IF CONDITION SATISFIED
            if (subtotal > 200) {
                applicableDiscounts.add(new DiscountRule(DiscountType.FLAT_10_DISCOUNT, 200, "$10"));
            }
            //ADD BULK_10_DISCOUNT IF CONDITION SATISFIED
            if (totalQuantity > 20) {
                applicableDiscounts.add(new DiscountRule(DiscountType.BULK_10_DISCOUNT, 20, "10%"));
            }
            //ADD TIERED_50_DISCOUNT IF CONDITION SATISFIED
            if (totalQuantity > 30) {
                for (ProductField productField : productFields) {
                    if (productField.getQuantity() > 15) {
                        applicableDiscounts.add(new DiscountRule(DiscountType.TIERED_50_DISCOUNT, 30, "50%"));
                        break;
                    }
                }
            }
            //ADD BULK_5_DISCOUNT IF CONDITION SATISFIED
            for (ProductField productField : productFields) {
                if (productField.getQuantity() > 10) {
                    applicableDiscounts.add(new DiscountRule(DiscountType.BULK_5_DISCOUNT, 10, "5%"));
                    break;
                }
            }

            if (applicableDiscounts.isEmpty()) {
                return null;
            }

            // To find the discount with the highest discount amount
            DiscountRule maxDiscount = applicableDiscounts.get(0);
            for (DiscountRule discount : applicableDiscounts) {
                if (calculateDiscountAmount(discount, subtotal, totalQuantity, productFields) > calculateDiscountAmount(maxDiscount, subtotal, totalQuantity, productFields)) {
                    maxDiscount = discount;
                }
            }
            return maxDiscount;
         }
    // Method to calculate the discount amount based on the discount rule
        private static double calculateDiscountAmount(DiscountRule discount, double subtotal, int totalQuantity, List<ProductField> productFields) {
                switch (discount.getType()) {
                    case FLAT_10_DISCOUNT:
                        return 10;
                    case BULK_10_DISCOUNT:
                        return  (subtotal * 0.1);
                    case TIERED_50_DISCOUNT:
                        double discountedAmount=0;
                        for (ProductField productField : productFields) {
                            if (productField.getQuantity() > 15) {
                                discountedAmount += (productField.getQuantity() - 15) * productField.getProduct().getPrice() *0.5;
                            }
                        }
                        return discountedAmount;
                    case BULK_5_DISCOUNT:
                        List<Double> l=new ArrayList<>();
                        for (ProductField productField : productFields) {
                            if (productField.getQuantity() > 10) {
                                l.add((productField.getQuantity() * productField.getProduct().getPrice() * 0.05));
                            }
                        }
                        //Check for Max discount for the products which are above 10 units in quantity
                        double discountMax=0;
                        for(double d:l){
                            discountMax=Math.max(discountMax,d);
                        }
                        return discountMax;
                    default:
                        return 0;
                }
        }
}
