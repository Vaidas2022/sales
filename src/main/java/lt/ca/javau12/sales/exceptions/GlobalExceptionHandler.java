package lt.ca.javau12.sales.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
		Map<String, Object> error = new LinkedHashMap<>();
		error.put("error", ex.getMessage()) ;
		error.put("timestamp", LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
	    Map<String, Object> body = new LinkedHashMap<>();
	    body.put("error", "Validation failed");
	    body.put("timestamp", LocalDateTime.now());

	    List<String> details = ex.getBindingResult()
	        .getFieldErrors()
	        .stream()
	        .map(err -> err.getField() + ": " + err.getDefaultMessage())
	        .toList();

	    body.put("details", details);

	    return ResponseEntity.badRequest().body(body);
	}

}
