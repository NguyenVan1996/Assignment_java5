package anhvanmobile.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

//anonatation very email
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = Constants.PATTERN, flags = Pattern.Flag.CASE_INSENSITIVE)
public @interface Email {
	String message() default "Thư điện tử không đúng định dạng!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

interface Constants {
	static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
	static final String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)+";
	static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

	static final String PATTERN = "^" + ATOM + "+(\\." + ATOM + "+)*@" + DOMAIN + "|" + IP_DOMAIN + ")$";
}