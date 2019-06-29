package org.addycaddy.client.dto;

import java.util.List;

public interface AddyCaddyClient {

    String create(ContactPointDto contactPointDto) throws Exception;

    String create(ContactPointDto[] contactPointDtos) throws Exception;

    String create(List<ContactPointDto> contactPointDtos) throws Exception;

    ContactPointDto[] findByCustomerId(String customerId) throws Exception;

    ContactPointDto[] search(String searchBy, String searchKey) throws Exception;

    String update(ContactPointDto contactPointDto) throws Exception;

    String update(ContactPointDto[] contactPointDtos) throws Exception;
}
