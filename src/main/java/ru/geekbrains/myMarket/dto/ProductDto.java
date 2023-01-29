package ru.geekbrains.myMarket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.myMarket.entities.Product;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;

    @NotNull(message = "Товар должен иметь название")
    @Length(min = 3, max = 45, message = "Длина названия товара должна составлять 3-45 символов")
    private String name;

    @Min(value = 1, message = "Цена товара должна быть не менее 1 рубля")
    private BigDecimal price;

    @NotNull(message = "Товар должен иметь категорию")
    private String categoryName;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.categoryName = product.getCategory().getName();
    }
}
