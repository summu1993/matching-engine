package com.matching.engine.service;

import com.matching.engine.config.Configs;
import com.matching.engine.dto.JobDTO;
import com.matching.engine.dto.WorkerDTO;
import com.matching.engine.matchingengine.JobMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class JobMatcherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatcherService.class);
    @Autowired
    JobMatcher jobMatcher;
    @Autowired
    private JobService jobService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private Configs configs;

    public List<JobDTO> getMatchedJobs(String workerId) {

        List<JobDTO> matchedJobs = new ArrayList<>();
        Set<WorkerDTO> workers = workerService.getWorkers();
        WorkerDTO worker = new WorkerDTO(workerId);
        if (workers == null || workers.isEmpty() || !workers.contains(worker)) {
            LOGGER.error("Given Worker {} not found in the system", workerId);
        } else {
            Set<JobDTO> jobs = jobService.getJobs();
            if (jobs == null || jobs.isEmpty()) {
                LOGGER.error("No Jobs found in the system");
                return matchedJobs;
            } else {
                matchedJobs = jobMatcher.getMatchingJobs(
                        jobs,
                        workers.stream().filter(w -> w.getUserId().equals(workerId)).findFirst().get());

                // Limiting the results based on MaxJobSearchResults criteria
                if(matchedJobs.size() > configs.getMaxJobSearchResults()){
                    matchedJobs.subList(configs.getMaxJobSearchResults(), matchedJobs.size()).clear();
                }
            }
        }
        return matchedJobs;
    }
}
