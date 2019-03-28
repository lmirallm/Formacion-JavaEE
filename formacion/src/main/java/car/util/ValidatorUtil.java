package car.util;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtil {

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	public static <T> ArrayList<String> validate(T entity) {
		
		Set<ConstraintViolation<T>> violations = validator.validate(entity);
		ArrayList<String> violationMessages = new ArrayList<String>();
		for (ConstraintViolation<T> violation : violations) {
			violationMessages.add(violation.getMessage());
		}
		
		return violationMessages;
	}

}