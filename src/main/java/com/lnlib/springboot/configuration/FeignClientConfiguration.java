package com.lnlib.springboot.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Need to adjust the config because of the remote client
 *
 * <ul>
 * <li> the remote model is not strict:
 *  <ul>
 *   <li> https://api.instantwebtools.net/v1/passenger?page=0&size=3
 *   <li> the airline field is an array or a single object
 * </ul>
 * <li> the remote model has a '__v' field but not in our model : we ignore it
 * </ul>
 */
@Configuration
public class FeignClientConfiguration
{
    @Bean
    public Decoder feignDecoder()
    {
        ObjectMapper mapper = new ObjectMapper()
                // remote model has a field 'airline' as an array or simple object
                .configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true)
                // remote model has a property '__v' but not in our model
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new ResponseEntityDecoder(
                new SpringDecoder(() -> new HttpMessageConverters(
                        new MappingJackson2HttpMessageConverter(mapper))));
    }
}
