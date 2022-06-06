package com.matching.engine.service;

import com.matching.engine.config.Configs;
import com.matching.engine.dto.WorkerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class WorkerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerService.class);

    @Autowired
    private Configs configs;

    private RestTemplate restTemplate = new RestTemplate();

    public Set<WorkerDTO> getWorkers() {

        try {
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);

            ResponseEntity<WorkerDTO[]> workersResponse = restTemplate.getForEntity(configs.getWorkerResourceUrl(), WorkerDTO[].class);

             if (HttpStatus.OK != workersResponse.getStatusCode()) {
                LOGGER.error("Failed to get proper response from Worker Service. StatusCode={}", workersResponse.getStatusCode());
                return null;
            }

            Set<WorkerDTO> workers = Arrays.stream(Objects.requireNonNull(workersResponse.getBody())).collect(Collectors.toSet());
            LOGGER.info("Workers={}", workers);
            return workers;
        }catch (Exception e){
            LOGGER.error("Exception while trying to get Workers list from WorkerService",e);
        }
        return null;
    }
}
