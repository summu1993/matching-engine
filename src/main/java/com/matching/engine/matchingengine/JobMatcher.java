package com.matching.engine.matchingengine;

import com.matching.engine.dto.JobDTO;
import com.matching.engine.dto.WorkerDTO;

import java.util.List;
import java.util.Set;

public interface JobMatcher {

    /**
       Interface for getting matching jobs based on location or Driving Licence Requirement or skill sets
     **/
    public List<JobDTO> getMatchingJobs(Set<JobDTO> jobs, WorkerDTO worker);

}
