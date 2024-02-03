package exercise.mapper;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;

import exercise.model.BaseEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

// BEGIN
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ReferenceMapper {

    @Autowired
    private EntityManager entityManager;

    public <T extends BaseEntity> T toEntity(Long id, @TargetType Class<T> entityClass){
        return id != null ? entityManager.find(entityClass, id): null;
    }

}
// END
