package in.nbt.document.mappers;

import in.nbt.document.dto.responses.Template;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TemplateMapper {
    in.nbt.document.model.Template mapRequestToModel(in.nbt.document.dto.Template templateRequest);

    Template mapTemplateModelToResponse(in.nbt.document.model.Template templateModel);

    List<Template> mapTemplateModelsToResponses(List<in.nbt.document.model.Template> templateModel);

}
