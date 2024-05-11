package com.test.featureswitches.controller;

import com.test.featureswitches.entity.Feature;
import com.test.featureswitches.entity.RestResponse;
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

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        	Map<String,Object> data = new HashMap<String,Object>();
        	data.put("canAccess", feature.getEnable());
            
            RestResponse response = new RestResponse(HttpStatus.OK, false, data, null);
            return ResponseEntity.ok(response);
    	} else {
    		RestResponse response = new RestResponse(HttpStatus.NOT_FOUND, true, null, "Record not found");
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
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
        	
        	
        	Map<String,Object> data = new HashMap<String,Object>();
        	if (dbFeature != null) {
        		dbFeature.setEnable(newFeature.getEnable());
        		Feature savedFeature = featureService.save(dbFeature);
    	        
        		data.put("message", "Successfully updated existing feature access (ID: " + savedFeature.getID() + ")");
        	} else {
        		Feature savedFeature = featureService.save(newFeature);
        		
        		data.put("message", "Successfully added new feature access (ID: " + savedFeature.getID() + ")");
        	}
            
        	RestResponse response = new RestResponse(HttpStatus.OK, false, data, null);
        	return ResponseEntity.ok(response);
        	
    	} catch (Exception e) {
    		RestResponse response = new RestResponse(HttpStatus.NOT_MODIFIED, true, null, "No change in database");
    		return ResponseEntity.status(304).body(response);
    	}
    }

}

