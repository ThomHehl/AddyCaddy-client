package org.addycaddy.client.dto;

public interface AddyCaddyClient {

    ContactPointDto create(ContactPointDto contactPointDto) throws Exception;

    ContactPointDto[] findByCustomerId(String customerId) throws Exception;

    ContactPointDto update(ContactPointDto contactPointDto) throws Exception;
}
