package coupon.coupon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Coupon {

    private static final int MIN_DISCOUNT_RATE = 3;
    private static final int MAX_DISCOUNT_RATE = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CouponName couponName;

    @Embedded
    private DiscountAmount discountAmount;

    @Embedded
    private MinimumOrderAmount minimumOrderAmount;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Embedded
    private IssuablePeriod issuablePeriod;

    public Coupon() {
    }

    public Coupon(Long id, CouponName couponName, DiscountAmount discountAmount, MinimumOrderAmount minimumOrderAmount,
                  Category category, IssuablePeriod issuablePeriod) {
        validateDiscountRate(discountAmount, minimumOrderAmount);
        this.id = id;
        this.couponName = couponName;
        this.discountAmount = discountAmount;
        this.minimumOrderAmount = minimumOrderAmount;
        this.category = category;
        this.issuablePeriod = issuablePeriod;
    }

    public Coupon(CouponName couponName, DiscountAmount discountAmount, MinimumOrderAmount minimumOrderAmount,
                  Category category, IssuablePeriod issuablePeriod) {
        this(null, couponName, discountAmount, minimumOrderAmount, category, issuablePeriod);
    }

    private void validateDiscountRate(DiscountAmount discountAmount, MinimumOrderAmount minimumOrderAmount) {
        int discountRate = discountAmount.getValue() * 100 / minimumOrderAmount.getValue();
        if (discountRate < MIN_DISCOUNT_RATE) {
            throw new IllegalArgumentException("할인율은 3% 이상이어야 합니다.");
        }
        if (discountRate > MAX_DISCOUNT_RATE) {
            throw new IllegalArgumentException("할인율은 20% 이하여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coupon coupon = (Coupon) o;
        return Objects.equals(id, coupon.id)
                && Objects.equals(couponName, coupon.couponName)
                && Objects.equals(discountAmount, coupon.discountAmount)
                && Objects.equals(minimumOrderAmount, coupon.minimumOrderAmount)
                && category == coupon.category
                && Objects.equals(issuablePeriod, coupon.issuablePeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, couponName, discountAmount, minimumOrderAmount, category, issuablePeriod);
    }
}
