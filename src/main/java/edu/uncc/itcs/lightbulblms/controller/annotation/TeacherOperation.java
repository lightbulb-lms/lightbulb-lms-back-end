package edu.uncc.itcs.lightbulblms.controller.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@PreAuthorize("hasAnyAuthority('SCOPE_teacher', 'SCOPE_admin')")
public @interface TeacherOperation {
}
