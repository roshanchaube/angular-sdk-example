package org.ehrbase.angularsdkexample.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.openehrclient.defaultrestclient.TemporalAccessorDeSerializer;
import org.ehrbase.angularsdkexample.deserialization.AnatomischeLokalisationDiagnostischeSicherheitChoiceDeserializer;
import org.ehrbase.angularsdkexample.deserialization.AtiopathogeneseChoiceDeserializer;
import org.ehrbase.angularsdkexample.deserialization.AtiopathogeneseSchweregradChoiceDeserializer;
import org.ehrbase.angularsdkexample.deserialization.PartyProxyDeserializer;
import org.ehrbase.angularsdkexample.opt.diagnosecomposition.definition.AnatomischeLokalisationDiagnostischeSicherheitChoice;
import org.ehrbase.angularsdkexample.opt.diagnosecomposition.definition.AtiopathogeneseChoice;
import org.ehrbase.angularsdkexample.opt.diagnosecomposition.definition.AtiopathogeneseSchweregradChoice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.temporal.TemporalAccessor;

@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        builder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        builder.featuresToEnable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        builder.modules(new JavaTimeModule());

        builder.deserializerByType(TemporalAccessor.class, new TemporalAccessorDeSerializer());
        builder.deserializerByType(PartyProxy.class, new PartyProxyDeserializer());

        // deserializer for *Choice classes
        builder.deserializerByType(AtiopathogeneseChoice.class, new AtiopathogeneseChoiceDeserializer());
        builder.deserializerByType(AtiopathogeneseSchweregradChoice.class, new AtiopathogeneseSchweregradChoiceDeserializer());
        builder.deserializerByType(AnatomischeLokalisationDiagnostischeSicherheitChoice.class,
                new AnatomischeLokalisationDiagnostischeSicherheitChoiceDeserializer());

        return builder.build();
    }
}
