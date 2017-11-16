package com.example.demo;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URL;

/**
 * Created by limingxin on 2017/7/26.
 */
@Slf4j
public class ApiGenerator {

    @Test
    public void genApi() throws Exception {
        URL remoteSwaggerFile = new URL("http://127.0.0.1:8080/api-docs");
        java.nio.file.Path outputDirectory = java.nio.file.Paths.get("api");

        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.CONFLUENCE_MARKUP)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .build();
        Swagger2MarkupConverter.from(remoteSwaggerFile)
                .withConfig(config).build().toFolder(outputDirectory);

    }
}
