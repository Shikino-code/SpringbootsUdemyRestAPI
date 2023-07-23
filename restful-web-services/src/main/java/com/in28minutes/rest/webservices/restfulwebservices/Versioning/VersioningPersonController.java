package com.in28minutes.rest.webservices.restfulwebservices.Versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    //    URI Versioning
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionPerson() {
        return new PersonV1("Bob Chalie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionPerson() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //    Request Parameter versioning
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonOnRequestParameter() {
        return new PersonV1("Bob Charie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonOnRequestParameter() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //    (Custom) headers versioning
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonOnRequestHeader() {
        return new PersonV1("Bob Charie");
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonOnRequestHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //    Media type versioning (A.K.A. "content negotiation" or "accept header")
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
