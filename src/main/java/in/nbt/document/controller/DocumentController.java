package in.nbt.document.controller;

import in.nbt.document.dto.enums.DocType;
import in.nbt.document.dto.DocumentDetails;
import in.nbt.document.dto.responses.ApiError;
import in.nbt.document.dto.responses.DocumentResponse;
import in.nbt.document.dto.responses.Template;
import in.nbt.document.service.DocumentService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    protected static final Logger log = LogManager.getLogger(DocumentController.class);

    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    @Tag(name = "Document", description = "Generate Document")
    @Operation(
            operationId = "generate-document",
            description = "Generate Document " +
                    "documentContent must be sent in the request body."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Document Generated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Template.class),
                            examples = {
                                    @ExampleObject(name = "Document generated",
                                            value = "{\n" +
                                                    "  \"documentContent\": [\n" +
                                                    "    {\n" +
                                                    "      \"key\": \"customerMobile\",\n" +
                                                    "      \"value\": \"9044126630\"\n" +
                                                    "    },\n" +
                                                    "{\n" +
                                                    "      \"key\": \"customerName\",\n" +
                                                    "      \"value\": \"Avnish\"\n" +
                                                    "    },\n" +
                                                    "{\n" +
                                                    "      \"key\": \"transactionStatus\",\n" +
                                                    "      \"value\": \"Done\"\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
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
                                                    "  \"timestamp\": \"23-05-2022 05:27:01\",\n" +
                                                    "  \"message\": \"AppId id must not be null!\",\n" +
                                                    "  \"debugMessage\": null,\n" +
                                                    "  \"subErrors\": null\n" +
                                                    "}")
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
    public ResponseEntity<DocumentResponse> generateDocument(@RequestParam(required = true) String templateId,
                                                             @RequestParam(required = true) String appId,
                                                             @RequestParam(required = false, defaultValue = "PDF") DocType docType,
                                                             @RequestBody DocumentDetails documentDetails) {
        log.info("Generate Document Request Received ");
        DocumentResponse documentResponse = documentService.generateDocument(templateId,appId, docType, documentDetails);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(documentResponse);
    }
}
