package com.test.restfullbackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

@RestController
@RefreshScope
public class ExchangeController {
    private final static List<String> CURLIST = new ArrayList(Arrays.asList("EUR", "RUB", "USD"));
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeController.class);

    @Autowired
    private DiscoveryClient discoveryClient;


    @Value("${course.eur-rub: 80}")
    private String eurRub;

    @Value("${course.rub-eur: 0.0125}")
    private String rubEur;

    @Value("${course.eur-usd: 1.2}")
    private String eurUsd;

    @Value("${course.usd-eur: 0.83}")
    private String usdEur;

    @Value("${course.usd-rub: 70}")
    private String usdRub;

    @Value("${course.rub-usd: 0.014}")
    private String rubUsd;


    /**
     * controller returns currency course
     */
    @GetMapping(value = "/")
    public ResponseEntity<String> getCourse(@RequestParam String fromCur, @RequestParam String toCur) {
        String course = "";
        if (CURLIST.contains(fromCur) && CURLIST.contains(toCur)) {
            if (fromCur.equals("EUR") && toCur.equals("USD")) {
                course =  eurUsd;
            } else if (fromCur.equals("USD") && toCur.equals("EUR")) {
                course =  usdEur;
            } else if (fromCur.equals("EUR") && toCur.equals("RUB")) {
                course =  eurRub;
            } else if (fromCur.equals("RUB") && toCur.equals("EUR")) {
                course =  rubEur;
            } else if (fromCur.equals("USD") && toCur.equals("RUB")) {
                course =  usdRub;
            } else if (fromCur.equals("RUB") && toCur.equals("USD")) {
                course =  rubUsd;
            }
            LOG.info("Answer: " + fromCur + "/" + toCur + ": " + course);
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     *  controller shows all registered Eureka clients
     **/

    @GetMapping(value = "/show_ids")
    public String showFirstService() {
        List<String> serviceIds = this.discoveryClient.getServices();
        if (serviceIds == null || serviceIds.isEmpty()) {
            return "No services found!";
        }
        String result = "<h3>Service Ids:</h3>";
        for (String serviceId : serviceIds) {
            result += "<br><a href='showService?serviceId=" + serviceId + "'>" + serviceId + "</a>";
        }
        return result;
    }

}
