package com.test.featureswitches.service;

import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.test.featureswitches.entity.Feature;
import com.test.featureswitches.repository.FeatureRepository;



@Service
public class FeatureServiceImpl implements FeatureService {
	@Autowired
	FeatureRepository featureRepo;
	
	@Override
	public Feature save(Feature newFeature) {
		return featureRepo.save(newFeature);
	}
	
	@Override
	public Feature findByFeatureEmail(String featureName, String email) {
		List<Feature> features = featureRepo.findByFeatureNameAndEmail(featureName, email);
		
		return features.size() > 0 ? features.get(0) : null;
	}
}
