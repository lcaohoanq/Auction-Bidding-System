package com.lcaohoanq.auction.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    @NotBlank(message = "Product name is required")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("base_price")
    @NotNull(message = "Base price is required")
    @Min(value = 0, message = "Base price must be greater than or equal to 0")
    private Double basePrice;
}