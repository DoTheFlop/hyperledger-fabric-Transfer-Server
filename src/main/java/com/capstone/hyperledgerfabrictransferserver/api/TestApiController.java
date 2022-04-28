package com.capstone.hyperledgerfabrictransferserver.api;

import com.capstone.hyperledgerfabrictransferserver.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestApiController {

    private final TestService testService;

    @GetMapping("/initledger")
    public String init() throws Exception {
        return testService.initLedger();
    }

    @GetMapping("/asset")
    public String getAllAsset() throws Exception {
        return testService.getAllAsset();
    }

    @PostMapping("/asset")
    public String createAsset() throws Exception {
        return testService.createAsset();
    }

    @PostMapping("/coin")
    public String createCoin(
            @RequestParam String coinName
    ) throws Exception {
        return testService.createCoin(coinName);
    }

}
