package in.nbt.document.service.impl;

import in.nbt.document.dto.TemplateContent;
import in.nbt.document.dto.enums.ContentType;
import in.nbt.document.dto.enums.Status;
import in.nbt.document.dto.enums.Tag;
import in.nbt.document.dto.responses.Template;
import in.nbt.document.mappers.TemplateMapper;
import in.nbt.document.repository.TemplateRepository;
import in.nbt.document.service.TemplateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultTemplateServiceTest {

    private final TemplateMapper templateMapper = Mappers.getMapper(TemplateMapper.class);
    @Mock
    TemplateRepository templateRepository;



    @Test
    public void template_should_register_successfully() {
        TemplateService templateService
                = new DefaultTemplateService(templateRepository, templateMapper);

        in.nbt.document.dto.Template request = in.nbt.document.dto.Template.builder()
                .appId("aeps-app-id")
                .name("dummy-invoice-template")
                .status(Status.ACTIVE)
                .tag(Tag.INVOICE)
                .templateContent(
                        TemplateContent.builder()
                                .contentType(ContentType.BASE64)
                                .data("ZHVtbXktZGF0YQ==")
                                .build()
                )
                .build();


        when(templateRepository.save(any()))
                .thenReturn(in.nbt.document.model.Template.builder()
                        .id("123")
                        .build());

        Template template = templateService.registerTemplate(request);

        assertThat(template)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", "123");
    }


}