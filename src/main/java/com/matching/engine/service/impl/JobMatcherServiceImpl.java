package com.matching.engine.service.impl;

import com.matching.engine.dto.JobDTO;
import com.matching.engine.dto.WorkerDTO;
import com.matching.engine.matchingengine.JobMatcher;
import com.matching.engine.service.JobMatcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.matching.engine.helper.LocationDistanceCalculator.distance;


/**
  Matches Jobs for a worker based on Location preference, Driving Licence Requirement and Skill Set
 */
@Component
public class JobMatcherServiceImpl implements JobMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatcherService.class);

    @Override
    public List<JobDTO> getMatchingJobs(Set<JobDTO> jobs, WorkerDTO worker) {

        LOGGER.info("Number of available jobs are {}", jobs.size());
        List<JobDTO> matchedJobs;

        /**
             Case1: Filtering based on Driving Licence Requirement.
             Case2: Filtering based on jobSearchAddress.maxJobDistance.
             Case3: Filtering based on at least one matching required certificate.
             Case4: Filtering based on skills.
         **/

        // Case1
        matchedJobs = jobs.stream().filter(p -> p.isDriverLicenseRequired() == worker.isHasDriversLicense()).collect(Collectors.toList());
        LOGGER.info("Number of matched jobs after Driving Licence Requirement are {}", matchedJobs.size());


        // Case2
        matchedJobs = matchedJobs.stream().filter(job -> distance(
                worker.getJobSearchAddress().getLatitude(),
                job.getLocation().getLatitude(),
                worker.getJobSearchAddress().getLongitude(),
                job.getLocation().getLongitude(),
                worker.getJobSearchAddress().getUnit()) <= worker.getJobSearchAddress().getMaxJobDistance())
                .collect(Collectors.toList());

        LOGGER.info("Number of matched jobs after jobSearchAddress.maxJobDistance are {}", matchedJobs.size());

        // Case3
        matchedJobs = matchedJobs.stream().filter(job -> isWorkerHasRequiredCertificates(job.getRequiredCertificates(), worker.getCertificates())).collect(Collectors.toList());
        LOGGER.info("Number of matched jobs after Required Certificate check are {}", matchedJobs.size());

        // Case4
        matchedJobs = matchedJobs.stream().filter(job -> worker.getSkills().contains(job.getJobTitle())).collect(Collectors.toList());
        LOGGER.info("Number of matched jobs after skills check are {}", matchedJobs.size());

        return matchedJobs;
    }

    private boolean isWorkerHasRequiredCertificates(List requiredCertificates, List certificates){
        for(Object certificate :requiredCertificates){
            if(certificates.contains(certificate)) {
                LOGGER.info("Matched certificate={}",certificate);
                return true;
            }
        }
        return false;
    }
}
