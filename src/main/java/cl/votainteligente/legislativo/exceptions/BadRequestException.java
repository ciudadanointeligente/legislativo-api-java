package cl.votainteligente.legislativo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1174235463806077058L;

}
