package definition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static definition.Multiplicity.SINGLE;


@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigField {
    String displayName() default "";

    String description() default "";

    boolean required() default false;

    Multiplicity multiplicity() default SINGLE;

    boolean expressionEnabled() default false;
}
