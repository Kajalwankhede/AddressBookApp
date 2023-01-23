package com.example.addressbookapp.Exception;
import com.example.addressbookapp.DTO.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ControllerAdvice
@Slf4j
public class AddressBookExceptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> errorList = exception.getBindingResult().getFieldErrors();
        errorList.forEach(objErr ->
                errorMap.put(objErr.getField(), objErr.getDefaultMessage()));
        ResponseDTO responseDTO = new ResponseDTO("Method parameters invalid while processing Http Method Request", errorMap);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
}
 @ExceptionHandler(AddressBookException.class)
    public ResponseEntity<ResponseDTO> handleEmployeePayrollNotFoundException(AddressBookException exception) {
        ResponseDTO responseDTO = new ResponseDTO("Exception while processing Http Method Request", exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }
}