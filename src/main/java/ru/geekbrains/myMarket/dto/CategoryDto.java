package ru.geekbrains.myMarket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.myMarket.entities.Category;
import ru.geekbrains.myMarket.entities.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;

    @NotNull(message = "Название категории не должно быть пустым")
    @Length(min = 3, max = 45, message = "Длина названия категории должна составлять 3-45 символов")
    private String name;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
