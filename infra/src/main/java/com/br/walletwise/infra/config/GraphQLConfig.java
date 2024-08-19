package com.br.walletwise.infra.config;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Configuration
public class GraphQLConfig {

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        GraphQLScalarType scalarType = dateScalar();
        return wiringBuilder -> wiringBuilder.scalar(scalarType);
    }

    @Bean
    public GraphQLScalarType dateScalar() {
        return GraphQLScalarType.newScalar()
                .name("LocalDate")
                .description("A date in the format yyyy-MM-dd")
                .coercing(new Coercing<LocalDate, String>() {
                    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    @Override
                    public String serialize(Object dataFetcherResult) {
                        if (dataFetcherResult instanceof LocalDate) {
                            return ((LocalDate) dataFetcherResult).format(formatter);
                        }
                        throw new IllegalArgumentException("Expected a LocalDate object.");
                    }

                    @Override
                    public LocalDate parseValue(Object input) {
                        if (input instanceof String) {
                            try {
                                return LocalDate.parse((String) input, formatter);
                            } catch (DateTimeParseException e) {
                                throw new IllegalArgumentException("Invalid date format, expected yyyy-MM-dd.");
                            }
                        }
                        throw new IllegalArgumentException("Expected a String.");
                    }

                    @Override
                    public LocalDate parseLiteral(Object input) {
                        if (input instanceof graphql.language.StringValue) {
                            return parseValue(((graphql.language.StringValue) input).getValue());
                        }
                        throw new IllegalArgumentException("Expected a StringValue.");
                    }
                })
                .build();
    }
}
