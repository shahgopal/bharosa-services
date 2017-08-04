package com.bharosa.api;

/**
 * Created by gshah on 7/31/17.
 */
import java.util.concurrent.atomic.AtomicLong;

import com.bharosa.paytm.PaytmUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bharosa.model.Campaign;
import org.springframework.web.bind.annotation.RequestMethod;
@RestController

public class CampaignController {
    private static final String template = "BC, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "payment", method = RequestMethod.GET, produces = "application/json")
//    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
                public String greeting() {

//        return new Campaign(counter.incrementAndGet(),
//                String.format(template, name));
        PaytmUtil paytmUtil = new PaytmUtil();
        return paytmUtil.generatePayLoad();
    }
}
