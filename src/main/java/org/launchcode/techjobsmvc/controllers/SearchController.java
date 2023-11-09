package org.launchcode.techjobsmvc.controllers;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

@PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        // declare Array List to hold Job objects
        ArrayList<Job> jobs;
            //when empty it equals all jobs so fetch all jobs and put them in jobs list
       if (searchTerm == null || searchTerm.isEmpty() || "all".equalsIgnoreCase(searchTerm)) {
           jobs = JobData.findAll();
           //if not all jobs then use searchType and searchTerm to get the correct jobs to add to jobs list
       } else {
           jobs = JobData.findByColumnAndValue(searchType, searchTerm);

       }
       // add jobs to model so you can view it
        model.addAttribute("jobs", jobs);

       //add the columnChoices to model so you can view/ filter searches
        model.addAttribute("columns", ListController.columnChoices);

        //connect to search.html, views displays search results
        return "search";
}


    }


