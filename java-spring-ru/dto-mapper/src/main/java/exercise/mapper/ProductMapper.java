package exercise.mapper;

// BEGIN
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.dto.ProductDTO;
import exercise.model.Product;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public abstract class ProductMapper {
    @Mapping(target = "name", source = "title")
    @Mapping(target = "cost", source = "price")
    @Mapping(target = "barcode", source = "vendorCode")
    public abstract Product map(ProductCreateDTO dto);

    // Наследует конфигурацию оригинального метода map
    // Нам не нужно копировать @Mapping(target = "title", source = "name")
    @InheritConfiguration
    public abstract void update(ProductUpdateDTO dto, @MappingTarget Product model);

    @Mapping(target = "title", source = "name")
    @Mapping(target = "price", source = "cost")
    @Mapping(target = "vendorCode", source = "barcode")
    public abstract ProductDTO map(Product product);
}
// END