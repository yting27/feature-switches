package com.test.featureswitches.repository;

import com.test.featureswitches.entity.Feature; 

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface FeatureRepository extends CrudRepository<Feature, Long> {
	/**
	 * Find feature objects based on {@code featureName} and {@code email}.
	 * @param featureName feature name to query
	 * @param email email of user to query
	 * @return List of related feature data
	 */
	List<Feature> findByFeatureNameAndEmail(String featureName, String email);
}

