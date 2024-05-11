package com.test.featureswitches.service;

import com.test.featureswitches.entity.Feature; 
import org.springframework.stereotype.Service;


@Service
public interface FeatureService {
	/**
	 * Add or update feature record in the database.
	 * @param newFeature feature to add or update.
	 * @return the same feature data saved.
	 */
	Feature save(Feature newFeature);
	
	/**
	 * Find feature by {@code featureName} and {@code email}.
	 * @param featureName feature name to query
	 * @param email email of user to query
	 * @return the first matching feature data
	 */
	Feature findByFeatureEmail(String featureName, String email);
}
