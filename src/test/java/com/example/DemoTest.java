package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import java.util.List;

@MicronautTest
class DemoTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());

        Book book = new Book();
        book.setTitle("Hello");
        Author author = new Author();
        author.setName("Aristoteles");
        book.setAuthors(List.of(author));

        var xmlMapper = new XmlMapper();
        var builder = new StringBuilder();
        try {
            builder.append(xmlMapper.writeValueAsString(book));
            var xml = "<Book><title>Hello</title><authors><authors><name>Aristoteles</name></authors></authors></Book>";
            Assertions.assertEquals(xml, builder.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
