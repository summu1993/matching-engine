package com.matching.engine.service;

import com.matching.engine.config.Configs;
import com.matching.engine.dto.JobDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Integration class for the Job Service API.
 */
@Component
public class JobService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    @Autowired
    private Configs configs;

    private final RestTemplate restTemplate = new RestTemplate();

    public Set<JobDTO> getJobs() {
        try {

            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);
            ResponseEntity<JobDTO[]> jobResponse = restTemplate.getForEntity(configs.getJobResourceUrl(), JobDTO[].class);

            if (HttpStatus.OK != jobResponse.getStatusCode()) {
                LOGGER.error("Failed to get proper response from Jobs Service. StatusCode={}", jobResponse.getStatusCode());
                return null;
            }
            Set<JobDTO> jobs = Arrays.stream(Objects.requireNonNull(jobResponse.getBody())).collect(Collectors.toSet());
            LOGGER.info("Jobs={}", jobs);
            return jobs;
        } catch (Exception e) {
            LOGGER.error("Exception while getting Jobs from JobService",e);
        }
        return null;
    }
}
