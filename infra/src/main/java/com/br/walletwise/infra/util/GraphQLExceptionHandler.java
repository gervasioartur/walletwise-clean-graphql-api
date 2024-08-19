package com.br.walletwise.infra.util;

import com.br.walletwise.core.exception.*;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        GraphQLError error;

        if (ex instanceof DomainException)
            error = GraphqlErrorBuilder.newError()
                    .message("DomainException: " + ex.getMessage())
                    .errorType(ErrorType.BAD_REQUEST)
                    .build();

        else if (ex instanceof ConflictException)
            error = GraphqlErrorBuilder.newError()
                    .message("ConflictException: " + ex.getMessage())
                    .errorType(ErrorType.BAD_REQUEST)
                    .build();

        else if (ex instanceof BusinessException)
            error = GraphqlErrorBuilder.newError()
                    .message("BusinessException: " + ex.getMessage())
                    .errorType(ErrorType.BAD_REQUEST)
                    .build();

        else if (ex instanceof UnauthorizedException
                || ex instanceof AuthorizationDeniedException
                || ex instanceof UsernameNotFoundException)

            error = GraphqlErrorBuilder.newError()
                    .message("UnauthorizedException: " + ex.getMessage())
                    .errorType(ErrorType.UNAUTHORIZED)
                    .build();

        else if (ex instanceof NotFoundException)
            error = GraphqlErrorBuilder.newError()
                    .message("NotFoundException : " + ex.getMessage())
                    .errorType(ErrorType.NOT_FOUND)
                    .build();
        else
            error = GraphqlErrorBuilder.newError()
                    .message("InternalServerError: An unexpected error occurred. " +
                            "\n Cause : " + ex.getCause())
                    .errorType(ErrorType.INTERNAL_ERROR)
                    .build();

        return error;
    }
}