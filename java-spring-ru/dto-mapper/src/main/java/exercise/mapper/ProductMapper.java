package exercise.mapper;

// BEGIN
import ch.qos.logback.core.model.ComponentModel;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;


@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class ProductMapper {

    @Mapping(target = "name", source = "title")
    @Mapping(target = "cost", source = "price")
    @Mapping(target = "barcode", source = "vendorCode")
    public abstract Product map(ProductCreateDTO dto);

    @Mapping(target = "cost", source = "price")
    public abstract void update(ProductUpdateDTO dto, @MappingTarget Product model);
    @Mapping(target = "title", source = "name")
    @Mapping(target = "price", source = "cost")
    @Mapping(target = "vendorCode", source = "barcode")
    public abstract ProductDTO map(Product model);


}
// END
