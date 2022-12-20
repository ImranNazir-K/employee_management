package com.ideas2it.employeemanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Class to handle Excpetions.
 *
 * @author IMRAN NAZIR K
 *
 * @version 6.0
 */
@RestControllerAdvice
public class EMSExceptionHandler {

	/**
	 * Class handles the Employee Management System Exceptions.
	 * 
	 * @param exception that was caught.
	 * 
	 * @return exception as ErrorDTO object.
	 */
    @ExceptionHandler(value = EMSException.class)
	public ResponseEntity<Object> EMSException(EMSException exception) {
		return new ResponseEntity<Object>(exception.getExceptionMessage(),
		        exception.getHttpStatus());
	}
    
    /**
     * Class handles the validation exception.
     * 
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> MethodArgumentNotValidException
            (MethodArgumentNotValidException exception) {
        return new ResponseEntity<Object>(exception.getBindingResult()
                .getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }
}
