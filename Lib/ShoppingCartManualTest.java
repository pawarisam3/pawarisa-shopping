import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1)); // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }
        // Test 4 : ใช้คูปองส่วนลด BOGO
        ArrayList<CartItem> BOGOCart = new ArrayList<>();
        BOGOCart.add(new CartItem("BOGO", "spoon", 20.0, 3)); // 40
        BOGOCart.add(new CartItem("BOGO", "fork", 15.0, 3)); // 30
        double total4 = ShoppingCartCalculator.calculateTotalPrice(BOGOCart);
        if (total4 == 70.0) {
            System.out.println("PASSED: BOGO cart total is correct (70.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart total expected 70.0 but got " + total4);
            failedCount++;
        }
        // Test 5 : ใช้คูปองส่วนลด BLUK ซื้อ 6 ชิ้นขึ้นไป
        ArrayList<CartItem> BLUKCart = new ArrayList<>();
        BLUKCart.add(new CartItem("BLUK", "pen", 15.0, 8)); // 120*0.9=108
        BLUKCart.add(new CartItem("BLUK", "pencil", 10.0, 6)); // 60*0.9=54
        double total5 = ShoppingCartCalculator.calculateTotalPrice(BLUKCart);
        if (total5 == 162.0) {
            System.out.println("PASSED: BLUK cart total is correct (162.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BLUK cart total expected 162.0 but got " + total5);
            failedCount++;
        }
        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}