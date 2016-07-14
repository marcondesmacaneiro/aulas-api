package br.com.example.view;

import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.util.StringUtils.collectionToDelimitedString;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
class ConstrainedFields {

    private final ConstraintDescriptions constraintDescriptions;

    ConstrainedFields(Class<?> input) {
        constraintDescriptions = new ConstraintDescriptions(input);
    }

    FieldDescriptor withPath(String path) {
        return fieldWithPath(path)
                .attributes(
                        key("constraints")
                                .value(collectionToDelimitedString(constraintDescriptions.descriptionsForProperty(path), ", "))
                );
    }
}
