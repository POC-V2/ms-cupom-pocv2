package com.pocms.cupons.api;

import com.pocms.cupons.model.Cupom;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@OpenAPIDefinition(
        info = @Info(
                title = "API de Cupons",
                description = "Essa API provê endpoints para manipular os dados de cupons no banco de dados",
                version = "1.0",
                contact = @Contact(
                        name = "Banco PAN digital"
                )
        )
)
public interface CupomApi {

    @Operation(summary = "Retorna o valor de desconto filtrando pelo seu código de identificação")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Valor de desconto",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = "100")
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Produto não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = """
                                                    {
                                                         "timestamp": "2021-02-24T13:18:50.430+00:00",
                                                         "status": "XXX_100",
                                                         "userMessage": "Cupom não foi encontrado: ID[XXX]",
                                                         "developerMessage": "Cupom não foi encontrado: ID[XXX]"
                                                     }"""
                                    )
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = """
                                                    {
                                                         "timestamp": "2021-02-24T13:18:50.430+00:00",
                                                         "status": "XXX_300",
                                                         "userMessage": "Mensagem de erro inesperado",
                                                         "developerMessage": "Mensagem de erro inesperado"
                                                     }"""
                                    )
                            }
                    )
            )
    })
    BigDecimal one(@NonNull @PathVariable("id") String id);

    @Operation(summary = "Cria um novo cupom a partir do valor de desconto")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Retorna o objeto cupom que foi persistido no banco de dados",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = """
                                            {
                                              "id": "string",
                                              "desconto": 0
                                            }
                                            """)
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetro inválido",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = """
                                                    {
                                                         "timestamp": "2021-02-24T13:21:37.903+00:00",
                                                         "status": "XXX_200",
                                                         "userMessage": "Character x is neither a decimal digit number, decimal point, nor \\"e\\" notation exponential mark.",
                                                         "developerMessage": "Failed to convert value of type 'java.lang.String' to required type 'java.math.BigDecimal'; nested exception is java.lang.NumberFormatException: Character x is neither a decimal digit number, decimal point, nor \\"e\\" notation exponential mark."
                                                     }"""
                                    )
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = """
                                                    {
                                                         "timestamp": "2021-02-19T18:43:29.585+00:00",
                                                         "status": "XXX_300",
                                                         "userMessage": "Mensagem de erro inesperado",
                                                         "developerMessage": "Mensagem de erro inesperado"
                                                     }"""
                                    )
                            }
                    )
            )
    })
    Cupom newCupom(@NonNull @RequestParam("desconto") BigDecimal desconto);
}
