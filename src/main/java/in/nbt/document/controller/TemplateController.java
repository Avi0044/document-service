package in.nbt.document.controller;

import in.nbt.document.dto.enums.Status;
import in.nbt.document.dto.responses.ApiError;
import in.nbt.document.dto.responses.Template;
import in.nbt.document.dto.responses.TemplateResponse;
import in.nbt.document.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/templates")
public class TemplateController {

    protected static final Logger log = LogManager.getLogger(TemplateController.class);

    private TemplateService defaultTemplateService;

    @Autowired
    public TemplateController(final TemplateService defaultTemplateService) {
        this.defaultTemplateService = defaultTemplateService;
    }

    @PostMapping
    @Tag(name = "Templates", description = "Register & Unregister Templates")
    @Operation(
            operationId = "register-template",
            description = "Creates a new template. If template configuration is not specified " +
                    "in the request body then an empty template is created. " +
                    "Template configuration must be sent in the request body."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Template created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Template.class),
                            examples = {
                                    @ExampleObject(name = "Template created",
                                            value = "{\n" +
                                                    "  \"appId\": \"1212\",\n" +
                                                    "  \"name\": \"Test\",\n" +
                                                    "  \"templateContent\": {\n" +
                                                    "   \"contentType\": \"BASE64\",\n" +
                                                    "    \"data\": \"PCFET0NUWVBFIGh0bWwNCglQVUJMSUMgIi0vL1czQy8vRFREIFhIVE1MIDEuMCBUcmFuc2l0aW9uYWwvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvVFIveGh0bWwxL0RURC94aHRtbDEtdHJhbnNpdGlvbmFsLmR0ZCI+DQo8aHRtbCB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94aHRtbCINCgl4bWxuczp0aD0iaHR0cDovL3d3dy50aHltZWxlYWYub3JnIg0KCXhtbG5zOmxheW91dD0iaHR0cDovL3d3dy51bHRyYXEubmV0Lm56L3RoeW1lbGVhZi9sYXlvdXQiPg0KDQo8aGVhZD4NCjx0aXRsZT5UcmFuc2FjdGlvbiBSZWNlaXB0PC90aXRsZT4NCjxsaW5rIGhyZWY9J2h0dHA6Ly9mb250cy5nb29nbGVhcGlzLmNvbS9jc3M/ZmFtaWx5PVJvYm90bycNCglyZWw9J3N0eWxlc2hlZXQnIHR5cGU9J3RleHQvY3NzJyAvPg0KDQo8c3R5bGU+DQp0YWJsZSB7DQoJcGFkZGluZzogM3B4IDZweCAzcHggNnB4ICFpbXBvcnRhbnQ7DQp9DQoNCmJvZHkgew0KCWZvbnQtZmFtaWx5OiAnQ2FsaWJyaScsIHNhbnMtc2VyaWY7DQp9DQoNCnRyIHsNCglwYWRkaW5nOiA1cHggMHB4IDVweCAwcHggIWltcG9ydGFudDsNCgltYXJnaW46IDVweCAwcHggNXB4IDBweCAhaW1wb3J0YW50Ow0KCS8qb3V0bGluZTogdGhpbiBzb2xpZDsqLw0KfQ0KDQp0ZCB7DQoJcGFkZGluZzogMHB4IDBweCAwcHggMHB4ICFpbXBvcnRhbnQ7DQoJbWFyZ2luOiAwcHggMHB4IDBweCAwcHggIWltcG9ydGFudDsNCgkvKmJvcmRlci1yaWdodDogMXB4IHNvbGlkIGJsYWNrOyovDQp9DQoNCmgyIHsNCglwYWRkaW5nOiA1cHggMHB4IDVweCAwcHggIWltcG9ydGFudDsNCgltYXJnaW46IDVweCAwcHggNXB4IDBweCAhaW1wb3J0YW50Ow0KfQ0KDQpoNCB7DQoJcGFkZGluZzogNXB4IDJweCAycHggNXB4ICFpbXBvcnRhbnQ7DQoJbWFyZ2luOiA1cHggMnB4IDJweCA1cHggIWltcG9ydGFudDsNCgljb2xvcjogIzMzMzMzMyAhaW1wb3J0YW50Ow0KCWZvbnQtc2l6ZTogMThweCAhaW1wb3J0YW50Ow0KfQ0KDQpzdHJvbmcgew0KCXBhZGRpbmc6IDVweCAwcHggMTBweCA1cHggIWltcG9ydGFudDsNCgltYXJnaW46IDVweCAwcHggMnB4IDVweCAhaW1wb3J0YW50Ow0KCWNvbG9yOiAjMzMzMzMzICFpbXBvcnRhbnQ7DQoJZm9udC1zaXplOiAxNnB4ICFpbXBvcnRhbnQ7DQp9DQoNCnAgew0KCXBhZGRpbmc6IDJweCA2cHggMnB4IDZweCAhaW1wb3J0YW50Ow0KCW1hcmdpbjogMnB4IDZweCAycHggNnB4ICFpbXBvcnRhbnQ7DQp9DQoNCmhyIHsNCglib3JkZXI6IDAuNXB4IHNvbGlkICM4RTkwOTA7DQoJbWFyZ2luOiA1cHggMHB4IDVweCAwcHggIWltcG9ydGFudDsNCn0NCg0KLnVzZXItaW1hZ2Ugew0KCXdpZHRoOiAxMDBweCAhaW1wb3J0YW50Ow0KCWhlaWdodDogMTAwcHggIWltcG9ydGFudDsNCglib3JkZXI6IDJweCBzb2xpZCAjQkZDMkM0ICFpbXBvcnRhbnQ7DQoJYm9yZGVyLWNvbGxhcHNlOiBzZXBhcmF0ZSAhaW1wb3J0YW50Ow0KCXBlcnNwZWN0aXZlOiAxcHggIWltcG9ydGFudDsNCglvdmVyZmxvdzogaGlkZGVuICFpbXBvcnRhbnQ7DQp9DQo8L3N0eWxlPg0KPC9oZWFkPg0KDQo8Ym9keSBzdHlsZT0id2lkdGg6IDUwJTsiPg0KCTxkaXY+DQoNCgkJPHRhYmxlIHdpZHRoPSIxMDAlIj4NCgkJCTx0Ym9keT4NCgkJCQk8dHI+DQoJCQkJCTx0ZCB3aWR0aD0iMjAlIj48aW1nIHNyYz0iaHR0cHM6Ly9wYXluZWFyYnkuaW4vd3AtY29udGVudC90aGVtZXMvcGF5bmVhcmJ5L2Fzc2V0cy9pbWFnZXMvbG9nby5wbmciIHdpZHRoPSI5MCINCgkJCQkJCWhlaWdodD0iMzUiIHN0eWxlPSJtYXJnaW4tbGVmdDogMjBweDsiIGFsdD0iIj48L3RkPg0KCQkJCQk8dGQgd2lkdGg9IjgwJSI+PGgyPlRyYW5zYWN0aW9uIFJlY2VpcHQ8L2gyPjwvdGQ+DQoJCQkJPC90cj4NCgkJCTwvdGJvZHk+DQoJCTwvdGFibGU+DQoJCTxici8+DQoJPC9kaXY+DQoJDQoJIDxkaXY+DQoNCgkJCQk8dGFibGUgd2lkdGg9IjEwMCUiPg0KCQkJCQk8dGJvZHk+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIGNvbHNwYW49IjIiPg0KCQkJCQkJCQk8aDQ+Q3VzdG9tZXIgRGV0YWlsczo8L2g0Pg0KCQkJCQkJCTwvdGQ+DQoNCgkJCQkJCTwvdHI+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cD5Nb2JpbGUgTnVtYmVyIChTTVMpPC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHtjdXN0b21lck1vYmlsZX0iPjwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJPC90cj4NCgkJCQkJCTx0cj4NCgkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCTxwPkN1c3RvbWVyIE5hbWU8L3A+DQoJCQkJCQkJPC90ZD4NCgkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCTxwIHRoOnV0ZXh0PSIke2N1c3RvbWVyTmFtZX0iPjwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJPC90cj4NCgkJCQkJPC90Ym9keT4NCgkJCQk8L3RhYmxlPg0KCQkJCTxici8+DQoJCQk8L2Rpdj4gPGJyLz4NCgkJCQk8ZGl2Pg0KCQkJCQk8dGFibGUgd2lkdGg9IjEwMCUiPg0KCQkJCQkJPHRib2R5Pg0KCQkJCQkJCTx0cj4NCgkJCQkJCQkJPHRkIGNvbHNwYW49IjIiPg0KCQkJCQkJCQkJPGg0PlRyYW5zYWN0aW9uIERldGFpbHM6PC9oND4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQk8L3RyPg0KCQkJCQkJCTx0cj4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHA+VHJhbnNhY3Rpb24gU3RhdHVzPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHt0cmFuc2FjdGlvblN0YXR1c30iPjwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQk8L3RyPg0KCQkJCQkJCTx0cj4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHA+VHJhbnNhY3Rpb24gSUQ8L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwIHRoOnV0ZXh0PSIke3RyYW5zYWN0aW9uSUR9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlRyYW5zYWN0aW9uIERhdGUgJiBUaW1lPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHt0aW1lU3RhbXB9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlBheW1lbnQgTWV0aG9kPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHtwYXltZW50TWV0aG9kfSI+PC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCTwvdHI+DQoJCQkJCQkJPHRyPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cD5UcmFuc2FjdGlvbiBNb2RlPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHt0cmFuc2FjdGlvbk1vZGV9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlRyYW5zYWN0aW9uIEFtb3VudDwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7dHJhbnNhY3Rpb25BbW91bnR9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPkNvbnZlbmllbmNlIEZlZTwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7Y29udmVuaWVuY2VGZWV9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlRvdGFsIEFtb3VudDwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7dG90YWxBbW91bnR9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCg0KCQkJCQkJPC90Ym9keT4NCgkJCQkJPC90YWJsZT4NCgkJCQk8L2Rpdj48YnIvPg0KCQkJCTxkaXY+DQoNCgkJCQk8dGFibGUgd2lkdGg9IjEwMCUiPg0KCQkJCQk8dGJvZHk+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIGNvbHNwYW49IjIiPg0KCQkJCQkJCQk8aDQ+QmVuaWZpY2lhcnkgRGV0YWlsczo8L2g0Pg0KCQkJCQkJCTwvdGQ+DQoNCgkJCQkJCTwvdHI+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cD5CZW5pZmljaWFyeSBOYW1lIDwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7YmVuaWZpY2lhcnlOYW1lfSI+PC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQk8L3RyPg0KCQkJCQkJPHRyPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHA+QmVuaWZpY2lhcnkgQmFuazwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7YmVuaWZpY2lhcnlCYW5rfSI+PC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQk8L3RyPg0KCQkJCQkJDQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cD5CZW5pZmljaWFyeSBBY2NvdW50IE51bWJlcjwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7YmVuaWZpY2lhcnlBY2NvdW50TnVtYmVyfSI+PC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQk8L3RyPg0KCQkJCQkJPHRyPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHA+QmFuayBJRlNDIENvZGU8L3A+DQoJCQkJCQkJPC90ZD4NCgkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCTxwIHRoOnV0ZXh0PSIke2JhbmtJRlNDQ29kZX0iPjwvcD4NCgkJCQkJCQk8L3RkPg0KDQoJCQkJCQk8L3RyPg0KCQkJCQk8L3Rib2R5Pg0KCQkJCTwvdGFibGU+DQoJCQkJPGJyLz4NCgkJCTwvZGl2PiANCjwvYm9keT4NCg0KPC9odG1sPg==\"\n" +
                                                    "  },\n" +
                                                    "  \"status\": \"ACTIVE\",\n" +
                                                    "\n" +
                                                    "  \"type\": \"CASH WITHDRAWAL\",\n" +
                                                    "  \"tag\": \"INVOICE\"\n" +
                                                    " \n" +
                                                    "}")
                            })}),
            @ApiResponse(responseCode = "400", description = "Please enter valid template details & content",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Invalid Template",
                                            value = "{\n" +
                                                    "  \"status\": \"BAD_REQUEST\",\n" +
                                                    "  \"timestamp\": \"23-05-2022 01:41:13\",\n" +
                                                    "  \"message\": \"Please Enter Valid Template!\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": [\n" +
                                                    "    {\n" +
                                                    "      \"object\": \"template\",\n" +
                                                    "      \"field\": \"name\",\n" +
                                                    "      \"rejectedValue\": \"NotBlank\",\n" +
                                                    "      \"message\": \"Template name is mandatory\"\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"object\": \"template\",\n" +
                                                    "      \"field\": \"templateContent\",\n" +
                                                    "      \"rejectedValue\": \"NotNull\",\n" +
                                                    "      \"message\": \"Template content is mandatory\"\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
                                                    "}"
                                    )
                            })}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples ={
                                    @ExampleObject(
                                            name = "Unauthorized",
                                            value = "{\n" +
                                                    "  \"status\": \"UNAUTHORIZED\",\n" +
                                                    "  \"timestamp\": \"23-05-2022 02:03:26\",\n" +
                                                    "  \"message\": \"UNAUTHORIZED\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": null\n" +
                                                    "}"
                                    )
                            }
                    )}),

            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples ={
                                    @ExampleObject(
                                            name = "Internal server error",
                                            value = "{\n" +
                                                    "  \"status\": \"INTERNAL_SERVER_ERROR\",\n" +
                                                    "  \"timestamp\": \"23-05-2022 01:45:48\",\n" +
                                                    "  \"message\": \"Something Went Wrong\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": null\n" +
                                                    "}"
                                    )
                            }
                    )})
    })
    public ResponseEntity<Template> registerTemplate(@Valid @RequestBody in.nbt.document.dto.Template template) {
        log.info("Register Template Request Received");
        Template templateResponse = defaultTemplateService.registerTemplate(template);
        return ResponseEntity.status(HttpStatus.OK)
                .body(templateResponse);
    }

    @PatchMapping
    @Tag(name = "Templates")
    @Operation(
            operationId = "update-template",
            description = "Unregister or Update an existing template."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Template",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Template.class),
                            examples = {
                                    @ExampleObject(name = "Update Template",
                                            value = "{\n" +
                                                    "  \"appId\": \"1212\",\n" +
                                                    "  \"name\": \"Test2\",\n" +
                                                    "  \"templateContent\": {\n" +
                                                    "    \"contentType\": \"BASE64\",\n" +
                                                    "    \"data\": \"PCFET0NUWVBFIGh0bWwNCglQVUJMSUMgIi0vL1czQy8vRFREIFhIVE1MIDEuMCBUcmFuc2l0aW9uYWwvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvVFIveGh0bWwxL0RURC94aHRtbDEtdHJhbnNpdGlvbmFsLmR0ZCI+DQo8aHRtbCB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94aHRtbCINCgl4bWxuczp0aD0iaHR0cDovL3d3dy50aHltZWxlYWYub3JnIg0KCXhtbG5zOmxheW91dD0iaHR0cDovL3d3dy51bHRyYXEubmV0Lm56L3RoeW1lbGVhZi9sYXlvdXQiPg0KDQo8aGVhZD4NCjx0aXRsZT5UcmFuc2FjdGlvbiBSZWNlaXB0PC90aXRsZT4NCjxsaW5rIGhyZWY9J2h0dHA6Ly9mb250cy5nb29nbGVhcGlzLmNvbS9jc3M/ZmFtaWx5PVJvYm90bycNCglyZWw9J3N0eWxlc2hlZXQnIHR5cGU9J3RleHQvY3NzJyAvPg0KDQo8c3R5bGU+DQp0YWJsZSB7DQoJcGFkZGluZzogM3B4IDZweCAzcHggNnB4ICFpbXBvcnRhbnQ7DQp9DQoNCmJvZHkgew0KCWZvbnQtZmFtaWx5OiAnQ2FsaWJyaScsIHNhbnMtc2VyaWY7DQp9DQoNCnRyIHsNCglwYWRkaW5nOiA1cHggMHB4IDVweCAwcHggIWltcG9ydGFudDsNCgltYXJnaW46IDVweCAwcHggNXB4IDBweCAhaW1wb3J0YW50Ow0KCS8qb3V0bGluZTogdGhpbiBzb2xpZDsqLw0KfQ0KDQp0ZCB7DQoJcGFkZGluZzogMHB4IDBweCAwcHggMHB4ICFpbXBvcnRhbnQ7DQoJbWFyZ2luOiAwcHggMHB4IDBweCAwcHggIWltcG9ydGFudDsNCgkvKmJvcmRlci1yaWdodDogMXB4IHNvbGlkIGJsYWNrOyovDQp9DQoNCmgyIHsNCglwYWRkaW5nOiA1cHggMHB4IDVweCAwcHggIWltcG9ydGFudDsNCgltYXJnaW46IDVweCAwcHggNXB4IDBweCAhaW1wb3J0YW50Ow0KfQ0KDQpoNCB7DQoJcGFkZGluZzogNXB4IDJweCAycHggNXB4ICFpbXBvcnRhbnQ7DQoJbWFyZ2luOiA1cHggMnB4IDJweCA1cHggIWltcG9ydGFudDsNCgljb2xvcjogIzMzMzMzMyAhaW1wb3J0YW50Ow0KCWZvbnQtc2l6ZTogMThweCAhaW1wb3J0YW50Ow0KfQ0KDQpzdHJvbmcgew0KCXBhZGRpbmc6IDVweCAwcHggMTBweCA1cHggIWltcG9ydGFudDsNCgltYXJnaW46IDVweCAwcHggMnB4IDVweCAhaW1wb3J0YW50Ow0KCWNvbG9yOiAjMzMzMzMzICFpbXBvcnRhbnQ7DQoJZm9udC1zaXplOiAxNnB4ICFpbXBvcnRhbnQ7DQp9DQoNCnAgew0KCXBhZGRpbmc6IDJweCA2cHggMnB4IDZweCAhaW1wb3J0YW50Ow0KCW1hcmdpbjogMnB4IDZweCAycHggNnB4ICFpbXBvcnRhbnQ7DQp9DQoNCmhyIHsNCglib3JkZXI6IDAuNXB4IHNvbGlkICM4RTkwOTA7DQoJbWFyZ2luOiA1cHggMHB4IDVweCAwcHggIWltcG9ydGFudDsNCn0NCg0KLnVzZXItaW1hZ2Ugew0KCXdpZHRoOiAxMDBweCAhaW1wb3J0YW50Ow0KCWhlaWdodDogMTAwcHggIWltcG9ydGFudDsNCglib3JkZXI6IDJweCBzb2xpZCAjQkZDMkM0ICFpbXBvcnRhbnQ7DQoJYm9yZGVyLWNvbGxhcHNlOiBzZXBhcmF0ZSAhaW1wb3J0YW50Ow0KCXBlcnNwZWN0aXZlOiAxcHggIWltcG9ydGFudDsNCglvdmVyZmxvdzogaGlkZGVuICFpbXBvcnRhbnQ7DQp9DQo8L3N0eWxlPg0KPC9oZWFkPg0KDQo8Ym9keSBzdHlsZT0id2lkdGg6IDUwJTsiPg0KCTxkaXY+DQoNCgkJPHRhYmxlIHdpZHRoPSIxMDAlIj4NCgkJCTx0Ym9keT4NCgkJCQk8dHI+DQoJCQkJCTx0ZCB3aWR0aD0iMjAlIj48aW1nIHNyYz0iaHR0cHM6Ly9wYXluZWFyYnkuaW4vd3AtY29udGVudC90aGVtZXMvcGF5bmVhcmJ5L2Fzc2V0cy9pbWFnZXMvbG9nby5wbmciIHdpZHRoPSI5MCINCgkJCQkJCWhlaWdodD0iMzUiIHN0eWxlPSJtYXJnaW4tbGVmdDogMjBweDsiIGFsdD0iIj48L3RkPg0KCQkJCQk8dGQgd2lkdGg9IjgwJSI+PGgyPlRyYW5zYWN0aW9uIFJlY2VpcHQ8L2gyPjwvdGQ+DQoJCQkJPC90cj4NCgkJCTwvdGJvZHk+DQoJCTwvdGFibGU+DQoJCTxici8+DQoJPC9kaXY+DQoJDQoJIDxkaXY+DQoNCgkJCQk8dGFibGUgd2lkdGg9IjEwMCUiPg0KCQkJCQk8dGJvZHk+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIGNvbHNwYW49IjIiPg0KCQkJCQkJCQk8aDQ+Q3VzdG9tZXIgRGV0YWlsczo8L2g0Pg0KCQkJCQkJCTwvdGQ+DQoNCgkJCQkJCTwvdHI+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cD5Nb2JpbGUgTnVtYmVyIChTTVMpPC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHtjdXN0b21lck1vYmlsZX0iPjwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJPC90cj4NCgkJCQkJCTx0cj4NCgkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCTxwPkN1c3RvbWVyIE5hbWU8L3A+DQoJCQkJCQkJPC90ZD4NCgkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCTxwIHRoOnV0ZXh0PSIke2N1c3RvbWVyTmFtZX0iPjwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJPC90cj4NCgkJCQkJPC90Ym9keT4NCgkJCQk8L3RhYmxlPg0KCQkJCTxici8+DQoJCQk8L2Rpdj4gPGJyLz4NCgkJCQk8ZGl2Pg0KCQkJCQk8dGFibGUgd2lkdGg9IjEwMCUiPg0KCQkJCQkJPHRib2R5Pg0KCQkJCQkJCTx0cj4NCgkJCQkJCQkJPHRkIGNvbHNwYW49IjIiPg0KCQkJCQkJCQkJPGg0PlRyYW5zYWN0aW9uIERldGFpbHM6PC9oND4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQk8L3RyPg0KCQkJCQkJCTx0cj4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHA+VHJhbnNhY3Rpb24gU3RhdHVzPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHt0cmFuc2FjdGlvblN0YXR1c30iPjwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQk8L3RyPg0KCQkJCQkJCTx0cj4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHA+VHJhbnNhY3Rpb24gSUQ8L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwIHRoOnV0ZXh0PSIke3RyYW5zYWN0aW9uSUR9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlRyYW5zYWN0aW9uIERhdGUgJiBUaW1lPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHt0aW1lU3RhbXB9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlBheW1lbnQgTWV0aG9kPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHtwYXltZW50TWV0aG9kfSI+PC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCTwvdHI+DQoJCQkJCQkJPHRyPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cD5UcmFuc2FjdGlvbiBNb2RlPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHt0cmFuc2FjdGlvbk1vZGV9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlRyYW5zYWN0aW9uIEFtb3VudDwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7dHJhbnNhY3Rpb25BbW91bnR9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPkNvbnZlbmllbmNlIEZlZTwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7Y29udmVuaWVuY2VGZWV9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlRvdGFsIEFtb3VudDwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7dG90YWxBbW91bnR9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCg0KCQkJCQkJPC90Ym9keT4NCgkJCQkJPC90YWJsZT4NCgkJCQk8L2Rpdj48YnIvPg0KCQkJCTxkaXY+DQoNCgkJCQk8dGFibGUgd2lkdGg9IjEwMCUiPg0KCQkJCQk8dGJvZHk+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIGNvbHNwYW49IjIiPg0KCQkJCQkJCQk8aDQ+QmVuaWZpY2lhcnkgRGV0YWlsczo8L2g0Pg0KCQkJCQkJCTwvdGQ+DQoNCgkJCQkJCTwvdHI+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cD5CZW5pZmljaWFyeSBOYW1lIDwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7YmVuaWZpY2lhcnlOYW1lfSI+PC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQk8L3RyPg0KCQkJCQkJPHRyPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHA+QmVuaWZpY2lhcnkgQmFuazwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7YmVuaWZpY2lhcnlCYW5rfSI+PC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQk8L3RyPg0KCQkJCQkJDQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cD5CZW5pZmljaWFyeSBBY2NvdW50IE51bWJlcjwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7YmVuaWZpY2lhcnlBY2NvdW50TnVtYmVyfSI+PC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQk8L3RyPg0KCQkJCQkJPHRyPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHA+QmFuayBJRlNDIENvZGU8L3A+DQoJCQkJCQkJPC90ZD4NCgkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCTxwIHRoOnV0ZXh0PSIke2JhbmtJRlNDQ29kZX0iPjwvcD4NCgkJCQkJCQk8L3RkPg0KDQoJCQkJCQk8L3RyPg0KCQkJCQk8L3Rib2R5Pg0KCQkJCTwvdGFibGU+DQoJCQkJPGJyLz4NCgkJCTwvZGl2PiANCjwvYm9keT4NCg0KPC9odG1sPg==\"\n" +
                                                    "  },\n" +
                                                    "  \"status\": \"INACTIVE\",\n" +
                                                    "  \"id\": \"629887532caac01e1d9d1b2b\",\n" +
                                                    "  \"type\": \"MINI STATEMENT\",\n" +
                                                    "  \"tag\": \"INVOICE\"\n" +
                                                    " \n" +
                                                    "}")
                            })}),
            @ApiResponse(responseCode = "400", description = "Please enter valid template details & content",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Invalid Template",
                                            value = "{\n" +
                                                    "  \"status\": \"BAD_REQUEST\",\n" +
                                                    "  \"timestamp\": \"23-05-2022 02:14:13\",\n" +
                                                    "  \"message\": \"Please Enter Valid Template!\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": [\n" +
                                                    "    {\n" +
                                                    "      \"object\": \"template\",\n" +
                                                    "      \"field\": \"name\",\n" +
                                                    "      \"rejectedValue\": \"NotBlank\",\n" +
                                                    "      \"message\": \"Template name is mandatory\"\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"object\": \"template\",\n" +
                                                    "      \"field\": \"appId\",\n" +
                                                    "      \"rejectedValue\": \"NotBlank\",\n" +
                                                    "      \"message\": \"Application id is mandatory\"\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
                                                    "}"
                                    )
                            })}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples ={
                                    @ExampleObject(
                                            name = "Unauthorized",
                                            value = "{\n" +
                                                    "  \"status\": \"UNAUTHORIZED\",\n" +
                                                    "  \"timestamp\": \"23-05-2022 02:03:26\",\n" +
                                                    "  \"message\": \"UNAUTHORIZED\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": null\n" +
                                                    "}"
                                    )
                            }
                    )}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples ={
                                    @ExampleObject(
                                            name = "Internal server error",
                                            value = "{\n" +
                                                    "  \"status\": \"INTERNAL_SERVER_ERROR\",\n" +
                                                    "  \"timestamp\": \"23-05-2022 01:45:48\",\n" +
                                                    "  \"message\": \"Something Went Wrong\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": null\n" +
                                                    "}"
                                    )
                            }
                    )})
    })
    public ResponseEntity<Template> updateTemplate(@Valid @RequestBody in.nbt.document.dto.Template template) {
        Template templateResponse = defaultTemplateService.updateTemplate(template);
        return ResponseEntity.status(HttpStatus.OK)
                .body(templateResponse);
    }

    @Tag(name = "Templates")
    @Operation(
            operationId = "Template-list",
            description = "Get template list based on aapId and status."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Templates",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Template.class),
                            examples = {
                                    @ExampleObject(name = "Get Template List",
                                            value = "{\n" +
                                                    "  \"templates\": [\n" +
                                                    "    {\n" +
                                                    "      \"appId\": \"1212\",\n" +
                                                    "      \"name\": \"Test2\",\n" +
                                                    "      \"templateContent\": {\n" +
                                                    "        \"contentType\": \"BASE64\",\n" +
                                                    "        \"data\": \"PCFET0NUWVBFIGh0bWwNCglQVUJMSUMgIi0vL1czQy8vRFREIFhIVE1MIDEuMCBUcmFuc2l0aW9uYWwvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvVFIveGh0bWwxL0RURC94aHRtbDEtdHJhbnNpdGlvbmFsLmR0ZCI+DQo8aHRtbCB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94aHRtbCINCgl4bWxuczp0aD0iaHR0cDovL3d3dy50aHltZWxlYWYub3JnIg0KCXhtbG5zOmxheW91dD0iaHR0cDovL3d3dy51bHRyYXEubmV0Lm56L3RoeW1lbGVhZi9sYXlvdXQiPg0KDQo8aGVhZD4NCjx0aXRsZT5UcmFuc2FjdGlvbiBSZWNlaXB0PC90aXRsZT4NCjxsaW5rIGhyZWY9J2h0dHA6Ly9mb250cy5nb29nbGVhcGlzLmNvbS9jc3M/ZmFtaWx5PVJvYm90bycNCglyZWw9J3N0eWxlc2hlZXQnIHR5cGU9J3RleHQvY3NzJyAvPg0KDQo8c3R5bGU+DQp0YWJsZSB7DQoJcGFkZGluZzogM3B4IDZweCAzcHggNnB4ICFpbXBvcnRhbnQ7DQp9DQoNCmJvZHkgew0KCWZvbnQtZmFtaWx5OiAnQ2FsaWJyaScsIHNhbnMtc2VyaWY7DQp9DQoNCnRyIHsNCglwYWRkaW5nOiA1cHggMHB4IDVweCAwcHggIWltcG9ydGFudDsNCgltYXJnaW46IDVweCAwcHggNXB4IDBweCAhaW1wb3J0YW50Ow0KCS8qb3V0bGluZTogdGhpbiBzb2xpZDsqLw0KfQ0KDQp0ZCB7DQoJcGFkZGluZzogMHB4IDBweCAwcHggMHB4ICFpbXBvcnRhbnQ7DQoJbWFyZ2luOiAwcHggMHB4IDBweCAwcHggIWltcG9ydGFudDsNCgkvKmJvcmRlci1yaWdodDogMXB4IHNvbGlkIGJsYWNrOyovDQp9DQoNCmgyIHsNCglwYWRkaW5nOiA1cHggMHB4IDVweCAwcHggIWltcG9ydGFudDsNCgltYXJnaW46IDVweCAwcHggNXB4IDBweCAhaW1wb3J0YW50Ow0KfQ0KDQpoNCB7DQoJcGFkZGluZzogNXB4IDJweCAycHggNXB4ICFpbXBvcnRhbnQ7DQoJbWFyZ2luOiA1cHggMnB4IDJweCA1cHggIWltcG9ydGFudDsNCgljb2xvcjogIzMzMzMzMyAhaW1wb3J0YW50Ow0KCWZvbnQtc2l6ZTogMThweCAhaW1wb3J0YW50Ow0KfQ0KDQpzdHJvbmcgew0KCXBhZGRpbmc6IDVweCAwcHggMTBweCA1cHggIWltcG9ydGFudDsNCgltYXJnaW46IDVweCAwcHggMnB4IDVweCAhaW1wb3J0YW50Ow0KCWNvbG9yOiAjMzMzMzMzICFpbXBvcnRhbnQ7DQoJZm9udC1zaXplOiAxNnB4ICFpbXBvcnRhbnQ7DQp9DQoNCnAgew0KCXBhZGRpbmc6IDJweCA2cHggMnB4IDZweCAhaW1wb3J0YW50Ow0KCW1hcmdpbjogMnB4IDZweCAycHggNnB4ICFpbXBvcnRhbnQ7DQp9DQoNCmhyIHsNCglib3JkZXI6IDAuNXB4IHNvbGlkICM4RTkwOTA7DQoJbWFyZ2luOiA1cHggMHB4IDVweCAwcHggIWltcG9ydGFudDsNCn0NCg0KLnVzZXItaW1hZ2Ugew0KCXdpZHRoOiAxMDBweCAhaW1wb3J0YW50Ow0KCWhlaWdodDogMTAwcHggIWltcG9ydGFudDsNCglib3JkZXI6IDJweCBzb2xpZCAjQkZDMkM0ICFpbXBvcnRhbnQ7DQoJYm9yZGVyLWNvbGxhcHNlOiBzZXBhcmF0ZSAhaW1wb3J0YW50Ow0KCXBlcnNwZWN0aXZlOiAxcHggIWltcG9ydGFudDsNCglvdmVyZmxvdzogaGlkZGVuICFpbXBvcnRhbnQ7DQp9DQo8L3N0eWxlPg0KPC9oZWFkPg0KDQo8Ym9keSBzdHlsZT0id2lkdGg6IDUwJTsiPg0KCTxkaXY+DQoNCgkJPHRhYmxlIHdpZHRoPSIxMDAlIj4NCgkJCTx0Ym9keT4NCgkJCQk8dHI+DQoJCQkJCTx0ZCB3aWR0aD0iMjAlIj48aW1nIHNyYz0iaHR0cHM6Ly9wYXluZWFyYnkuaW4vd3AtY29udGVudC90aGVtZXMvcGF5bmVhcmJ5L2Fzc2V0cy9pbWFnZXMvbG9nby5wbmciIHdpZHRoPSI5MCINCgkJCQkJCWhlaWdodD0iMzUiIHN0eWxlPSJtYXJnaW4tbGVmdDogMjBweDsiIGFsdD0iIj48L3RkPg0KCQkJCQk8dGQgd2lkdGg9IjgwJSI+PGgyPlRyYW5zYWN0aW9uIFJlY2VpcHQ8L2gyPjwvdGQ+DQoJCQkJPC90cj4NCgkJCTwvdGJvZHk+DQoJCTwvdGFibGU+DQoJCTxici8+DQoJPC9kaXY+DQoJDQoJIDxkaXY+DQoNCgkJCQk8dGFibGUgd2lkdGg9IjEwMCUiPg0KCQkJCQk8dGJvZHk+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIGNvbHNwYW49IjIiPg0KCQkJCQkJCQk8aDQ+Q3VzdG9tZXIgRGV0YWlsczo8L2g0Pg0KCQkJCQkJCTwvdGQ+DQoNCgkJCQkJCTwvdHI+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cD5Nb2JpbGUgTnVtYmVyIChTTVMpPC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHtjdXN0b21lck1vYmlsZX0iPjwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJPC90cj4NCgkJCQkJCTx0cj4NCgkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCTxwPkN1c3RvbWVyIE5hbWU8L3A+DQoJCQkJCQkJPC90ZD4NCgkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCTxwIHRoOnV0ZXh0PSIke2N1c3RvbWVyTmFtZX0iPjwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJPC90cj4NCgkJCQkJPC90Ym9keT4NCgkJCQk8L3RhYmxlPg0KCQkJCTxici8+DQoJCQk8L2Rpdj4gPGJyLz4NCgkJCQk8ZGl2Pg0KCQkJCQk8dGFibGUgd2lkdGg9IjEwMCUiPg0KCQkJCQkJPHRib2R5Pg0KCQkJCQkJCTx0cj4NCgkJCQkJCQkJPHRkIGNvbHNwYW49IjIiPg0KCQkJCQkJCQkJPGg0PlRyYW5zYWN0aW9uIERldGFpbHM6PC9oND4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQk8L3RyPg0KCQkJCQkJCTx0cj4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHA+VHJhbnNhY3Rpb24gU3RhdHVzPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHt0cmFuc2FjdGlvblN0YXR1c30iPjwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQk8L3RyPg0KCQkJCQkJCTx0cj4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHA+VHJhbnNhY3Rpb24gSUQ8L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwIHRoOnV0ZXh0PSIke3RyYW5zYWN0aW9uSUR9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlRyYW5zYWN0aW9uIERhdGUgJiBUaW1lPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHt0aW1lU3RhbXB9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlBheW1lbnQgTWV0aG9kPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHtwYXltZW50TWV0aG9kfSI+PC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCTwvdHI+DQoJCQkJCQkJPHRyPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cD5UcmFuc2FjdGlvbiBNb2RlPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHt0cmFuc2FjdGlvbk1vZGV9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlRyYW5zYWN0aW9uIEFtb3VudDwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7dHJhbnNhY3Rpb25BbW91bnR9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPkNvbnZlbmllbmNlIEZlZTwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7Y29udmVuaWVuY2VGZWV9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlRvdGFsIEFtb3VudDwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7dG90YWxBbW91bnR9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCg0KCQkJCQkJPC90Ym9keT4NCgkJCQkJPC90YWJsZT4NCgkJCQk8L2Rpdj48YnIvPg0KCQkJCTxkaXY+DQoNCgkJCQk8dGFibGUgd2lkdGg9IjEwMCUiPg0KCQkJCQk8dGJvZHk+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIGNvbHNwYW49IjIiPg0KCQkJCQkJCQk8aDQ+QmVuaWZpY2lhcnkgRGV0YWlsczo8L2g0Pg0KCQkJCQkJCTwvdGQ+DQoNCgkJCQkJCTwvdHI+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cD5CZW5pZmljaWFyeSBOYW1lIDwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7YmVuaWZpY2lhcnlOYW1lfSI+PC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQk8L3RyPg0KCQkJCQkJPHRyPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHA+QmVuaWZpY2lhcnkgQmFuazwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7YmVuaWZpY2lhcnlCYW5rfSI+PC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQk8L3RyPg0KCQkJCQkJDQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cD5CZW5pZmljaWFyeSBBY2NvdW50IE51bWJlcjwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7YmVuaWZpY2lhcnlBY2NvdW50TnVtYmVyfSI+PC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQk8L3RyPg0KCQkJCQkJPHRyPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHA+QmFuayBJRlNDIENvZGU8L3A+DQoJCQkJCQkJPC90ZD4NCgkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCTxwIHRoOnV0ZXh0PSIke2JhbmtJRlNDQ29kZX0iPjwvcD4NCgkJCQkJCQk8L3RkPg0KDQoJCQkJCQk8L3RyPg0KCQkJCQk8L3Rib2R5Pg0KCQkJCTwvdGFibGU+DQoJCQkJPGJyLz4NCgkJCTwvZGl2PiANCjwvYm9keT4NCg0KPC9odG1sPg==\"\n" +
                                                    "      },\n" +
                                                    "      \"status\": \"ACTIVE\",\n" +
                                                    "      \"id\": \"629887532caac01e1d9d1b2b\",\n" +
                                                    "      \"type\": \"MINI STATEMENT\",\n" +
                                                    "      \"tag\": \"INVOICE\",\n" +
                                                    "      \"createdDate\": \"2022-06-02 15:18:03\",\n" +
                                                    "      \"lastModifiedDate\": \"2022-06-02 15:33:28\"\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
                                                    "}")
                            })}),
            @ApiResponse(responseCode = "400", description = "Please enter valid appId & status",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Invalid request",
                                            value = "{\n" +
                                                    "  \"status\": \"BAD_REQUEST\",\n" +
                                                    "  \"timestamp\": \"23-05-2022 02:43:17\",\n" +
                                                    "  \"message\": \"AppId id must not be null!\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": null\n" +
                                                    "}"
                                    )
                            })}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples ={
                                    @ExampleObject(
                                            name = "Unauthorized",
                                            value = "{\n" +
                                                    "  \"status\": \"UNAUTHORIZED\",\n" +
                                                    "  \"timestamp\": \"23-05-2022 02:03:26\",\n" +
                                                    "  \"message\": \"UNAUTHORIZED\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": null\n" +
                                                    "}"
                                    )
                            }
                    )}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples ={
                                    @ExampleObject(
                                            name = "Internal server error",
                                            value = "{\n" +
                                                    "  \"status\": \"INTERNAL_SERVER_ERROR\",\n" +
                                                    "  \"timestamp\": \"23-05-2022 01:45:48\",\n" +
                                                    "  \"message\": \"Something Went Wrong\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": null\n" +
                                                    "}"
                                    )
                            }
                    )})
    })
    @GetMapping
    public ResponseEntity<TemplateResponse> getTemplates(@RequestParam(required = true) String appId,
                                                         @RequestParam(required = false) Status status,
                                                         @RequestParam(required = false) String type) {
        log.info("Get Templates Request Received for app id : {}");
        TemplateResponse templateResponse = defaultTemplateService.getTemplates(appId, status,type);
        return ResponseEntity.status(HttpStatus.OK)
                .body(templateResponse);
    }

    @Tag(name = "Templates")
    @Operation(
            operationId = "Template",
            description = "Get template based on aapId and templateId."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Templates",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Template.class),
                            examples = {
                                    @ExampleObject(name = "Get Template by templateId and appId",
                                            value = "{\n" +
                                                    "  \"appId\": \"1212\",\n" +
                                                    "  \"name\": \"Test2\",\n" +
                                                    "  \"templateContent\": {\n" +
                                                    "    \"contentType\": \"BASE64\",\n" +
                                                    "    \"data\": \"PCFET0NUWVBFIGh0bWwNCglQVUJMSUMgIi0vL1czQy8vRFREIFhIVE1MIDEuMCBUcmFuc2l0aW9uYWwvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvVFIveGh0bWwxL0RURC94aHRtbDEtdHJhbnNpdGlvbmFsLmR0ZCI+DQo8aHRtbCB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94aHRtbCINCgl4bWxuczp0aD0iaHR0cDovL3d3dy50aHltZWxlYWYub3JnIg0KCXhtbG5zOmxheW91dD0iaHR0cDovL3d3dy51bHRyYXEubmV0Lm56L3RoeW1lbGVhZi9sYXlvdXQiPg0KDQo8aGVhZD4NCjx0aXRsZT5UcmFuc2FjdGlvbiBSZWNlaXB0PC90aXRsZT4NCjxsaW5rIGhyZWY9J2h0dHA6Ly9mb250cy5nb29nbGVhcGlzLmNvbS9jc3M/ZmFtaWx5PVJvYm90bycNCglyZWw9J3N0eWxlc2hlZXQnIHR5cGU9J3RleHQvY3NzJyAvPg0KDQo8c3R5bGU+DQp0YWJsZSB7DQoJcGFkZGluZzogM3B4IDZweCAzcHggNnB4ICFpbXBvcnRhbnQ7DQp9DQoNCmJvZHkgew0KCWZvbnQtZmFtaWx5OiAnQ2FsaWJyaScsIHNhbnMtc2VyaWY7DQp9DQoNCnRyIHsNCglwYWRkaW5nOiA1cHggMHB4IDVweCAwcHggIWltcG9ydGFudDsNCgltYXJnaW46IDVweCAwcHggNXB4IDBweCAhaW1wb3J0YW50Ow0KCS8qb3V0bGluZTogdGhpbiBzb2xpZDsqLw0KfQ0KDQp0ZCB7DQoJcGFkZGluZzogMHB4IDBweCAwcHggMHB4ICFpbXBvcnRhbnQ7DQoJbWFyZ2luOiAwcHggMHB4IDBweCAwcHggIWltcG9ydGFudDsNCgkvKmJvcmRlci1yaWdodDogMXB4IHNvbGlkIGJsYWNrOyovDQp9DQoNCmgyIHsNCglwYWRkaW5nOiA1cHggMHB4IDVweCAwcHggIWltcG9ydGFudDsNCgltYXJnaW46IDVweCAwcHggNXB4IDBweCAhaW1wb3J0YW50Ow0KfQ0KDQpoNCB7DQoJcGFkZGluZzogNXB4IDJweCAycHggNXB4ICFpbXBvcnRhbnQ7DQoJbWFyZ2luOiA1cHggMnB4IDJweCA1cHggIWltcG9ydGFudDsNCgljb2xvcjogIzMzMzMzMyAhaW1wb3J0YW50Ow0KCWZvbnQtc2l6ZTogMThweCAhaW1wb3J0YW50Ow0KfQ0KDQpzdHJvbmcgew0KCXBhZGRpbmc6IDVweCAwcHggMTBweCA1cHggIWltcG9ydGFudDsNCgltYXJnaW46IDVweCAwcHggMnB4IDVweCAhaW1wb3J0YW50Ow0KCWNvbG9yOiAjMzMzMzMzICFpbXBvcnRhbnQ7DQoJZm9udC1zaXplOiAxNnB4ICFpbXBvcnRhbnQ7DQp9DQoNCnAgew0KCXBhZGRpbmc6IDJweCA2cHggMnB4IDZweCAhaW1wb3J0YW50Ow0KCW1hcmdpbjogMnB4IDZweCAycHggNnB4ICFpbXBvcnRhbnQ7DQp9DQoNCmhyIHsNCglib3JkZXI6IDAuNXB4IHNvbGlkICM4RTkwOTA7DQoJbWFyZ2luOiA1cHggMHB4IDVweCAwcHggIWltcG9ydGFudDsNCn0NCg0KLnVzZXItaW1hZ2Ugew0KCXdpZHRoOiAxMDBweCAhaW1wb3J0YW50Ow0KCWhlaWdodDogMTAwcHggIWltcG9ydGFudDsNCglib3JkZXI6IDJweCBzb2xpZCAjQkZDMkM0ICFpbXBvcnRhbnQ7DQoJYm9yZGVyLWNvbGxhcHNlOiBzZXBhcmF0ZSAhaW1wb3J0YW50Ow0KCXBlcnNwZWN0aXZlOiAxcHggIWltcG9ydGFudDsNCglvdmVyZmxvdzogaGlkZGVuICFpbXBvcnRhbnQ7DQp9DQo8L3N0eWxlPg0KPC9oZWFkPg0KDQo8Ym9keSBzdHlsZT0id2lkdGg6IDUwJTsiPg0KCTxkaXY+DQoNCgkJPHRhYmxlIHdpZHRoPSIxMDAlIj4NCgkJCTx0Ym9keT4NCgkJCQk8dHI+DQoJCQkJCTx0ZCB3aWR0aD0iMjAlIj48aW1nIHNyYz0iaHR0cHM6Ly9wYXluZWFyYnkuaW4vd3AtY29udGVudC90aGVtZXMvcGF5bmVhcmJ5L2Fzc2V0cy9pbWFnZXMvbG9nby5wbmciIHdpZHRoPSI5MCINCgkJCQkJCWhlaWdodD0iMzUiIHN0eWxlPSJtYXJnaW4tbGVmdDogMjBweDsiIGFsdD0iIj48L3RkPg0KCQkJCQk8dGQgd2lkdGg9IjgwJSI+PGgyPlRyYW5zYWN0aW9uIFJlY2VpcHQ8L2gyPjwvdGQ+DQoJCQkJPC90cj4NCgkJCTwvdGJvZHk+DQoJCTwvdGFibGU+DQoJCTxici8+DQoJPC9kaXY+DQoJDQoJIDxkaXY+DQoNCgkJCQk8dGFibGUgd2lkdGg9IjEwMCUiPg0KCQkJCQk8dGJvZHk+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIGNvbHNwYW49IjIiPg0KCQkJCQkJCQk8aDQ+Q3VzdG9tZXIgRGV0YWlsczo8L2g0Pg0KCQkJCQkJCTwvdGQ+DQoNCgkJCQkJCTwvdHI+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cD5Nb2JpbGUgTnVtYmVyIChTTVMpPC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHtjdXN0b21lck1vYmlsZX0iPjwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJPC90cj4NCgkJCQkJCTx0cj4NCgkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCTxwPkN1c3RvbWVyIE5hbWU8L3A+DQoJCQkJCQkJPC90ZD4NCgkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCTxwIHRoOnV0ZXh0PSIke2N1c3RvbWVyTmFtZX0iPjwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJPC90cj4NCgkJCQkJPC90Ym9keT4NCgkJCQk8L3RhYmxlPg0KCQkJCTxici8+DQoJCQk8L2Rpdj4gPGJyLz4NCgkJCQk8ZGl2Pg0KCQkJCQk8dGFibGUgd2lkdGg9IjEwMCUiPg0KCQkJCQkJPHRib2R5Pg0KCQkJCQkJCTx0cj4NCgkJCQkJCQkJPHRkIGNvbHNwYW49IjIiPg0KCQkJCQkJCQkJPGg0PlRyYW5zYWN0aW9uIERldGFpbHM6PC9oND4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQk8L3RyPg0KCQkJCQkJCTx0cj4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHA+VHJhbnNhY3Rpb24gU3RhdHVzPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHt0cmFuc2FjdGlvblN0YXR1c30iPjwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQk8L3RyPg0KCQkJCQkJCTx0cj4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHA+VHJhbnNhY3Rpb24gSUQ8L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwIHRoOnV0ZXh0PSIke3RyYW5zYWN0aW9uSUR9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlRyYW5zYWN0aW9uIERhdGUgJiBUaW1lPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHt0aW1lU3RhbXB9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlBheW1lbnQgTWV0aG9kPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHtwYXltZW50TWV0aG9kfSI+PC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCTwvdHI+DQoJCQkJCQkJPHRyPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cD5UcmFuc2FjdGlvbiBNb2RlPC9wPg0KCQkJCQkJCQk8L3RkPg0KCQkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCQk8cCB0aDp1dGV4dD0iJHt0cmFuc2FjdGlvbk1vZGV9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlRyYW5zYWN0aW9uIEFtb3VudDwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7dHJhbnNhY3Rpb25BbW91bnR9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPkNvbnZlbmllbmNlIEZlZTwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7Y29udmVuaWVuY2VGZWV9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCgkJCQkJCQk8dHI+DQoJCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJCTxwPlRvdGFsIEFtb3VudDwvcD4NCgkJCQkJCQkJPC90ZD4NCgkJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7dG90YWxBbW91bnR9Ij48L3A+DQoJCQkJCQkJCTwvdGQ+DQoJCQkJCQkJPC90cj4NCg0KCQkJCQkJPC90Ym9keT4NCgkJCQkJPC90YWJsZT4NCgkJCQk8L2Rpdj48YnIvPg0KCQkJCTxkaXY+DQoNCgkJCQk8dGFibGUgd2lkdGg9IjEwMCUiPg0KCQkJCQk8dGJvZHk+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIGNvbHNwYW49IjIiPg0KCQkJCQkJCQk8aDQ+QmVuaWZpY2lhcnkgRGV0YWlsczo8L2g0Pg0KCQkJCQkJCTwvdGQ+DQoNCgkJCQkJCTwvdHI+DQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cD5CZW5pZmljaWFyeSBOYW1lIDwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7YmVuaWZpY2lhcnlOYW1lfSI+PC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQk8L3RyPg0KCQkJCQkJPHRyPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHA+QmVuaWZpY2lhcnkgQmFuazwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7YmVuaWZpY2lhcnlCYW5rfSI+PC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQk8L3RyPg0KCQkJCQkJDQoJCQkJCQk8dHI+DQoJCQkJCQkJPHRkIHdpZHRoPSI1MCUiPg0KCQkJCQkJCQk8cD5CZW5pZmljaWFyeSBBY2NvdW50IE51bWJlcjwvcD4NCgkJCQkJCQk8L3RkPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHAgdGg6dXRleHQ9IiR7YmVuaWZpY2lhcnlBY2NvdW50TnVtYmVyfSI+PC9wPg0KCQkJCQkJCTwvdGQ+DQoJCQkJCQk8L3RyPg0KCQkJCQkJPHRyPg0KCQkJCQkJCTx0ZCB3aWR0aD0iNTAlIj4NCgkJCQkJCQkJPHA+QmFuayBJRlNDIENvZGU8L3A+DQoJCQkJCQkJPC90ZD4NCgkJCQkJCQk8dGQgd2lkdGg9IjUwJSI+DQoJCQkJCQkJCTxwIHRoOnV0ZXh0PSIke2JhbmtJRlNDQ29kZX0iPjwvcD4NCgkJCQkJCQk8L3RkPg0KDQoJCQkJCQk8L3RyPg0KCQkJCQk8L3Rib2R5Pg0KCQkJCTwvdGFibGU+DQoJCQkJPGJyLz4NCgkJCTwvZGl2PiANCjwvYm9keT4NCg0KPC9odG1sPg==\"\n" +
                                                    "  },\n" +
                                                    "  \"status\": \"ACTIVE\",\n" +
                                                    "  \"id\": \"629887532caac01e1d9d1b2b\",\n" +
                                                    "  \"type\": \"MINI STATEMENT\",\n" +
                                                    "  \"tag\": \"INVOICE\",\n" +
                                                    "  \"createdDate\": \"2022-06-02 15:18:03\",\n" +
                                                    "  \"lastModifiedDate\": \"2022-06-02 15:33:28\"\n" +
                                                    "}")
                            })}),
            @ApiResponse(responseCode = "400", description = "Please enter valid templateId & AppId",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Invalid request",
                                            value = "{\n" +
                                                    "  \"status\": \"NOT_FOUND\",\n" +
                                                    "  \"timestamp\": \"23-05-2022 03:07:01\",\n" +
                                                    "  \"message\": \"Template not present\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": null\n" +
                                                    "}"
                                    )
                            })}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples ={
                                    @ExampleObject(
                                            name = "Unauthorized",
                                            value = "{\n" +
                                                    "  \"status\": \"UNAUTHORIZED\",\n" +
                                                    "  \"timestamp\": \"23-05-2022 02:03:26\",\n" +
                                                    "  \"message\": \"UNAUTHORIZED\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": null\n" +
                                                    "}"
                                    )
                            }
                    )}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples ={
                                    @ExampleObject(
                                            name = "Internal server error",
                                            value = "{\n" +
                                                    "  \"status\": \"INTERNAL_SERVER_ERROR\",\n" +
                                                    "  \"timestamp\": \"23-05-2022 01:45:48\",\n" +
                                                    "  \"message\": \"Something Went Wrong\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": null\n" +
                                                    "}"
                                    )
                            }
                    )})
    })
    @GetMapping
    @RequestMapping("/{templateId}")
    public ResponseEntity<Template> getTemplate(@PathVariable(required = true) String templateId
            , @RequestParam(required = true) String appId) {
        log.info("Get Template Request Received");
        Template template = defaultTemplateService.getTemplateByIdAndAppId(templateId, appId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(template);
    }
}
