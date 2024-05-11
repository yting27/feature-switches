package com.test.featureswitches.entity;

import java.util.Objects;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
public class Feature {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "The 'featureName` field cannot be empty")
	private String featureName;

	@Email(message = "The 'email' field contains invalid format", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotEmpty(message = "The 'email' field cannot be empty")
	private String email;

	@NotNull(message = "The 'enable' field cannot be empty")
	private Boolean enable;

	Feature() {
	}

	Feature(String featureName, String email, Boolean enable) {
		this.featureName = featureName;
		this.email = email;
		this.enable = enable;
	}

	public Long getID() {
		return this.id;
	}

	public String getFeatureName() {
		return this.featureName;
	}

	public String getEmail() {
		return this.email;
	}

	public Boolean getEnable() {
		return this.enable;
	}

	public void setID(Long ID) {
		this.id = ID;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public boolean equals(Object ob) {
		if (this == ob)
			return true;

		else if (!(ob instanceof Feature))
			return false;

		Feature featureObj = (Feature) ob;

		return Objects.equals(this.featureName, featureObj.featureName) && Objects.equals(this.email, featureObj.email)
				&& this.enable == featureObj.enable;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.featureName, this.email, this.enable);
	}

	@Override
	public String toString() {
		return "Feature{" + "id=" + this.id + ", featureName=" + this.featureName + ", email='" + this.email + "'" + ", enable=" + this.enable + "}";
	}

}
