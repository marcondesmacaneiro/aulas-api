package br.com.example.view;

import br.com.example.Application;
import br.com.example.domain.model.*;
import br.com.example.domain.repository.*;
import br.com.example.domain.vo.Phone;
import br.com.example.view.PersonController.PersonPatchInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.RequestDispatcher;
import java.util.HashMap;
import java.util.Map;

import static br.com.example.domain.exception.EntityNotFoundException.entityNotFoundException;
import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static br.com.example.view.PersonController.extractPersonId;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApiGuideDocumentation {

    @Rule
    public final RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    private RestDocumentationResultHandler documentation;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PersonService personService;

    @Before
    public void setUp() {
        documentation = document("{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));

        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(print())
                .alwaysDo(documentation)
                .build();
    }

    @Test
    public void headersExample() throws Exception {
        documentation.snippets(responseHeaders(
                headerWithName("Content-Type").description("The Content-Type of the payload, e.g. `application/hal+json`")));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void errorExample() throws Exception {
        documentation.snippets(responseFields(
                fieldWithPath("error").description("The HTTP error that occurred, e.g. `Bad Request`"),
                fieldWithPath("message").description("A description of the cause of the error"),
                fieldWithPath("path").description("The path to which the request was made"),
                fieldWithPath("status").description("The HTTP status code, e.g. `400`"),
                fieldWithPath("timestamp").description("The time, in milliseconds, at which the error occurred")));

        mockMvc.perform(get("/error")
                        .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400)
                        .requestAttr(RequestDispatcher.ERROR_REQUEST_URI, "/person/123")
                        .requestAttr(RequestDispatcher.ERROR_MESSAGE, "The person 'http://localhost:8080/person/123' does not exist"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("error", is("Bad Request")))
                .andExpect(jsonPath("timestamp", is(notNullValue())))
                .andExpect(jsonPath("status", is(400
                )))
                .andExpect(jsonPath("path", is(notNullValue())));
    }

    @Test
    public void indexExample() throws Exception {
        documentation.snippets(
                links(
                        linkWithRel("persons").description("The <<resources-persons,Persons resource>>")),
                responseFields(
                        fieldWithPath("_links").description("<<resources-index-links,Links>> to other resources")));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void personsListExample() throws Exception {
        documentation.snippets(
                responseFields(
                        fieldWithPath("page.size")
                                .description("Page size")
                                .type(JsonFieldType.NUMBER),
                        fieldWithPath("page.number")
                                .description("Page Number")
                                .type(JsonFieldType.NUMBER),
                        fieldWithPath("page.totalPages")
                                .description("Total pages of person")
                                .type(JsonFieldType.NUMBER),
                        fieldWithPath("page.totalElements")
                                .description("Total of person")
                                .type(JsonFieldType.NUMBER),
                        fieldWithPath("_embedded.persons")
                                .description("An array of <<resources-person, Person resources>>")
                                .type(JsonFieldType.ARRAY),
                        fieldWithPath("_links.self.href")
                                .description("This <<resources-persons, Persons>>")));

        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }

    @Test
    public void personsCreateExample() throws Exception {
        ConstrainedFields fields = new ConstrainedFields(Person.class);

        documentation.snippets(
                requestFields(
                        fields.withPath("name")
                                .description("The name of the person")
                                .type(JsonFieldType.STRING),
                        fields.withPath("phone")
                                .description("The phone number of the person")
                                .type(JsonFieldType.STRING),
                        fields.withPath("mail")
                                .description("The mail address of the person")
                                .type(JsonFieldType.STRING)
                                .optional()));

        Map<String, Object> person = new HashMap<>();
        person.put("name", "Person Name");
        person.put("phone", Phone.of("554700000000"));

        String location = mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader("Location");

        personService.delete(extractPersonId(location));
    }

    @Test
    public void personGetExample() throws Exception {
        Person person = personService.findOne(1L).orElseThrow(entityNotFoundException("Person with id 1 not found!"));

        documentation.snippets(
                links(
                        linkWithRel("self").description("This <<resources-person, Person>>")),
                responseFields(
                        fieldWithPath("name")
                                .description("The name of the person")
                                .type(JsonFieldType.STRING),
                        fieldWithPath("phone")
                                .description("The phone number address of the person")
                                .type(JsonFieldType.STRING),
                        fieldWithPath("mail")
                                .description("The mail address of the person")
                                .type(JsonFieldType.STRING)
                                .optional(),
                        fieldWithPath("_links")
                                .description("<<resources-person-links,Links>> to other resources")
                                .type(JsonFieldType.OBJECT)));

        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(person.getName())))
                .andExpect(jsonPath("phone", is(person.getPhone().toString())))
                .andExpect(jsonPath("_links.self.href", endsWith("/person/1")));

    }

    @Test
    public void personUpdateExample() throws Exception {
        ConstrainedFields fields = new ConstrainedFields(PersonPatchInput.class);

        documentation.snippets(
                requestFields(
                        fields.withPath("name")
                                .description("The name of the person")
                                .type(JsonFieldType.STRING)
                                .optional(),
                        fields.withPath("mail")
                                .description("The mail address of the person")
                                .type(JsonFieldType.STRING)
                                .optional(),
                        fields.withPath("phone")
                                .description("The phone number of the person")
                                .type(JsonFieldType.STRING)
                                .optional()));

        Person person = personService.findOne(1L).orElseThrow(entityNotFoundException("Person with id 1 not found!"));

        Map<String, Object> personUpdate = new HashMap<>();
        personUpdate.put("name", "Person");

        mockMvc.perform(patch("/person/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personUpdate)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void personDeleteExample() throws Exception {
        Person person = personService.save(Person.of("Some Person", Phone.of("+55 47 1111-1111")));

        mockMvc.perform(delete("/person/{id}", person.getId()))
                .andExpect(status().isNoContent());
    }
}
