package br.com.fabrisio.guideme.util;

import br.com.fabrisio.guideme.configuration.PatternsRegex;
import br.com.fabrisio.guideme.configuration.ResponseValues;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TraceUtil {


    public static List<String> filterTrace(List<StackTraceElement> stackTraceElements) {
        var stack = new ArrayList<String>();
        for (StackTraceElement traceElement : stackTraceElements) {
            var containPackage = traceElement.getClassName().contains(ResponseValues.STACK_FOR_PACKAGE);
            var containDollarSing = GenericValidator.validate(traceElement.getClassName(), PatternsRegex.REGEX_DOLLAR_SIGN);

            if (containPackage && !containDollarSing) {
                stack.add(traceElement.toString());
            }
        }
        return stack;
    }

    public static Exception catchRootException(Throwable throwable) {
        while (throwable.getCause() != null && throwable.getCause() != throwable) {
            throwable = throwable.getCause();
        }
        return (Exception) throwable;
    }
}

