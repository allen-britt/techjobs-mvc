package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results


    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {


        if (searchType.equals("all")) {
            // Run the query to retrieve jobs by all fields' terms
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);

            // Title of the search results
            model.addAttribute("title", "All Jobs");

            // For jobs listing on the model
            model.addAttribute("jobs", jobs);

            return "search";
        }
        else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);

            model.addAttribute("title", "All " + ListController.columnChoices.get(searchType) + " Values");

            model.addAttribute("jobs", jobs);
            return "search";
        }
    }
}

