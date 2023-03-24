package com.sandeep.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("sandeep suvarna");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		Name name = new Name("niriksha", "suvarna");
		return new PersonV2(name);
	}
	
	@GetMapping(path="/person", params="version=1")
	public PersonV1 getPersonV1RequestParam() {
		return new PersonV1("sandeep suvarna");
	}
}
