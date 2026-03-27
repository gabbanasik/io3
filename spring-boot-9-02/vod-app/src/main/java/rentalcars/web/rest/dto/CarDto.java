package rentalcars.web.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarDto {

    @NotBlank(message = "{car.brand.notblank}")
    private String brand;

    @NotBlank
    private String model;

    @Min(value = 1900)
    private int productionYear;

    @Min(value = 0)
    private float dailyRate;

    @NotNull
    private Integer clientId;
}