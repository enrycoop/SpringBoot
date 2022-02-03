package com.example.SpringBootTest.actuator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id= "features")
public class FeatureEndpoint {

    private final Map<String,Feature> featureMap = new ConcurrentHashMap<>();

    public FeatureEndpoint(){
        featureMap.put("Student", new Feature(true));
        featureMap.put("User", new Feature(false));
        featureMap.put("Auth", new Feature(false));
    }

    @ReadOperation
    public Map<String,Feature> features() {
        return featureMap;
    }

    @ReadOperation
    public Feature feature(@Selector String featureName){
        return featureMap.get(featureName);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Feature {
        private boolean isEnabled;
    }
}
