package com.example.demo;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> employees = Arrays.asList(
            ImmutableMap.of("id", "employee-100",
                    "name", "Jane Doe",
                    "messages", "You're doing great"),
            // A
            ImmutableMap.of("id", "employee-2",
                    "name", "Moby Dick",
                    "messages", "Almost there!"),
            // B
            ImmutableMap.of("id", "employee-102",
                    "name", "Leroy Syndrome",
                    "messages", "Hello")
    );


    public DataFetcher getEmployeeByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            // D
            String employeeID = dataFetchingEnvironment.getArgument("id");
            return employees
                    .stream()
                    // C
                    .filter(employee -> employee.get("id").equals(employeeID))
                    .findFirst()
                    .orElse(null);
        };
    }
}