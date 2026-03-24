package rentalcars.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice(basePackages = "rentalcars.web.rest")
@RequiredArgsConstructor
@Slf4j
public class vodAdvice {

    private final RentalCarsValidator rentalCarsValidator;
    private final CarValidator carValidator;

    @InitBinder("rentalCar")
    public void initBinderForRentalCar(WebDataBinder binder) {
        binder.addValidators(rentalCarsValidator);
    }

    @InitBinder("carDto")
    public void initBinderForCar(WebDataBinder binder) {
        binder.addValidators(carValidator);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("illegal argument provided", e);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("exception", e);
        return ResponseEntity.status(HttpStatus.LOOP_DETECTED).body(e.getMessage());
    }
}