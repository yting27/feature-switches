package com.test.featureswitches.controller;

import com.test.featureswitches.entity.Feature;
import com.test.featureswitches.service.FeatureService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;


@RestController
@RequestMapping(value="/feature", produces=MediaType.APPLICATION_JSON_VALUE)
public class FeatureController {

	@Autowired
	FeatureService featureService;
	
	/**
	 * Get feature access data based on {@code featureName} and {@code email} parameters.
	 * @param featureName feature name to query
	 * @param email email of user to query
	 * @return JSON response with {@code canAccess} key taken from {@code enable} value.
	 */
    @GetMapping
    public ResponseEntity<Object> findFeatureAccess(@RequestParam String featureName, @RequestParam String email) {
    	Feature feature = featureService.findByFeatureEmail(featureName, email);
    	
    	if (feature != null) {
        	Map<String,Object> map = new HashMap<String,Object>();
            map.put("canAccess", feature.getEnable());
            
            return ResponseEntity.ok(map);
    	} else {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    /**
     * 
     * @param newFeature the new feature to be added, or the existing feature to be updated.
     * @return response with status 200 for successful database operation; status 304 for any failure.
     */
    @PostMapping
    public ResponseEntity<Object> updateOrAddFeatureAccess(@RequestBody @Valid Feature newFeature) {
    	try {
        	Feature dbFeature = featureService.findByFeatureEmail(newFeature.getFeatureName(), newFeature.getEmail());
        	
        	if (dbFeature != null) {
        		dbFeature.setEnable(newFeature.getEnable());
    	        featureService.save(dbFeature);
        	} else {
        		featureService.save(newFeature);
        	}
        	
        	return ResponseEntity.ok(null);
    	} catch (Exception e) {
    		return ResponseEntity.status(304).body(null);
    	}
    }

}

