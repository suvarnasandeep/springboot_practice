package com.example.Udemysprinbootmicroservices.versioning;

public class PersonV2 {
	private PersonV1.Name name;

	public PersonV2() {
		super();
	}

	public PersonV2(PersonV1.Name name) {
		super();
		this.name = name;
	}

	public PersonV1.Name getName() {
		return name;
	}

	public void setName(PersonV1.Name name) {
		this.name = name;
	}

}
