package exercise.mapper;

import exercise.dto.*;
import exercise.model.Category;
import exercise.model.Product;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;

// BEGIN
@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ReferenceMapper.class, JsonNullableMapper.class}
)
public abstract class ProductMapper {

    @Mapping(source = "categoryId", target = "category")
    public abstract Product map(ProductCreateDTO dto);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    public abstract ProductDTO map(Product model);

    @Mapping(source = "categoryId", target = "category")
    public abstract void update(ProductUpdateDTO dto, @MappingTarget Product model);

}
// END
