package com.vasilchenko.web.controller;

import com.vasilchenko.web.filter.MetricFilter;
import com.vasilchenko.web.metric.MetricClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.vasilchenko.web.filter.MetricFilterFunctions.*;

/**
 * @version 1.0
 * @autor Viacheslav Vasilchenko
 */

@Controller
public class MetricController {

    private static String filterURIPath = "";
    private static String filterURIDataByWord = "";
    private static long filterURIResponseTime = 1000;


    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public String monitor(Model model) {
        model.addAttribute("monitorList", MetricFilter.metricClassList);
        model.addAttribute("filterURIPath", filterURIPath);
        model.addAttribute("filterURIDataByWord", filterURIDataByWord);
        model.addAttribute("filterURIResponseTime", filterURIResponseTime);
        return "monitor";
    }

    @RequestMapping(value = "/monitor/filter", method = RequestMethod.POST)
    public String filterURIPath(@RequestParam("filterURIPath") String filterURIPathHTML,
                                @RequestParam("filterURIDataByWord") String filterURIDataByWordHTML,
                                @RequestParam("filterURIResponseTime") String filterURIResponseTimeHTML,
                                Model model) {
        List<MetricClass> currentList = MetricFilter.metricClassList;

        filterURIPath = filterURIPathHTML;
        if (!filterURIPathHTML.equals("")) currentList = UrlFilter(currentList, filterURIPath);

        filterURIDataByWord = filterURIDataByWordHTML;
        if (!filterURIDataByWordHTML.equals(""))
            currentList = ResponseWordFilter(currentList, filterURIDataByWord);

        filterURIResponseTime = parseLong(filterURIResponseTimeHTML, filterURIResponseTime);
        currentList = ResponseTimeFilter(currentList, filterURIResponseTime);

        model.addAttribute("monitorList", currentList);
        model.addAttribute("filterURIPath", filterURIPath);
        model.addAttribute("filterURIDataByWord", filterURIDataByWord);
        model.addAttribute("filterURIResponseTime", filterURIResponseTime);
        return "monitor";
    }
}
