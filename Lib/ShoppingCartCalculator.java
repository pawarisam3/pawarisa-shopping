import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * - คำนวณราคาสินค้าและการใช้คูปองส่วนลดโดยคำนวณจาก ราคาสินค้า*ชิ้น
     * - ถ้า items เป็น null หรือ empty จะทำการ return ค่า 0.0 กลับไป
     * - ถ้า CartItem มี price หรือ quantity ติดลบ เริ่มแรกจะเข้าตรวจสอบติดลบหรือไม่
     * - กฏคูปองส่วนลด "BOGO" ให้ใช้โปรโมชั่นซื้อ 1 แถม 1 (เช่น ซื้อ 2 จ่าย 1 )
     * - กฏ "BULK" หากซื้อตั้งแต่ 6 ชิ้นขึ้นไป จะได้รับส่วนลด 10%
     * จากราคารวมของสินค้านั้น
     * 
     * @param คำนวณราคาสินค้า price*quantity
     * @return คืนค่าราคาสินค้าและหากมีการ error จะส่งข้อมูลคืนมา
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        // ไม่มีสินค้าหรือตะกร้าวางเปล่า
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        // มีสินค้าตั้งแต่ 1 ขึ้นไป
        double total = 0.0;
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            double itemTotal = 0.0;
            String sku = item.sku(); // คูปอง
            if (item.quantity() > 0 && item.price() > 0) {
                switch (sku) { // แยกเคสคูปอง
                    case "BOGO": { // ซื้อ 1แถม 1
                        int p = (item.quantity() / 2) + (item.quantity() % 2); // เหลือกี่ชิ้น
                        itemTotal = p * item.price(); // ราคาสินค้าทั้งหมด
                        break;
                    }
                    case "BLUK": { // ซื้อตั้งแต่ 6ขึ้นไป
                        if (item.quantity() >= 6) {
                            itemTotal = item.quantity() * item.price() * 0.9; // ลด 10%ต้องคูณ0.9
                        } else {
                            itemTotal = item.quantity() * item.price(); // ถ้าไม่ตรงกฏคิดปกติ
                        }
                        break;
                    }
                    default: {
                        itemTotal = item.quantity() * item.price(); // กรณีไม่มีsku
                    }
                }

            } else {
                continue;
            }
            total += itemTotal; // total = total + itemtotal

        }
        return total;
    }

}
