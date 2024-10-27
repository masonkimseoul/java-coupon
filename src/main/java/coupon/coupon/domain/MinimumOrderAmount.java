package coupon.coupon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.text.NumberFormat;
import lombok.Getter;

@Embeddable
@Getter
public class MinimumOrderAmount {

    private static final int MIN_AMOUNT = 5000;
    private static final int MAX_AMOUNT = 100000;

    @Column(name = "minimum_order_amount")
    private int value;

    public MinimumOrderAmount() {
    }

    public MinimumOrderAmount(int value) {
        validateAmount(value);
        this.value = value;
    }

    private void validateAmount(int value) {
        if (value < MIN_AMOUNT) {
            throw new IllegalArgumentException(
                    "최소 주문 금액은 %s원 이상이어야 합니다.".formatted(NumberFormat.getInstance().format(MIN_AMOUNT)));
        }
        if (value > MAX_AMOUNT) {
            throw new IllegalArgumentException(
                    "최소 주문 금액은 %s원 이하여야 합니다.".formatted(NumberFormat.getInstance().format(MAX_AMOUNT)));
        }
    }
}
