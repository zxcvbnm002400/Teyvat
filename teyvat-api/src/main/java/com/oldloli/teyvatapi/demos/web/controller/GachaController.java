package com.oldloli.teyvatapi.demos.web.controller;

import com.oldloli.teyvatapi.demos.web.service.GachaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
public class GachaController {

    @Resource
    private GachaService gachaService;

    // http://127.0.0.1:8080/gacha?times=1000000&num=200&target=3&cnt=57&isGuarantee=false
    @RequestMapping("/gacha")
    @ResponseBody
    public double gacha(@RequestParam(name = "times") int times, @RequestParam(name = "num") int num,
                        @RequestParam(name = "target") int target, @RequestParam(name = "cnt") int cnt,
                        @RequestParam(name = "isGuarantee") boolean isGuarantee) {
        return gachaService.gacha(times, num, target, cnt, isGuarantee);
    }
}
