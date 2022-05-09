package cf.grcq.framework.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String name();
    String permission() default "";
    String description() default "";
    String[] aliases() default {};

    boolean hidden() default false;
    boolean async() default false;

}
