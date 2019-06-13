package org.addycaddy.client.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class AddyCaddyClientImpl implements AddyCaddyClient {
    public static final int             DEFAULT_PORT = 9002;
    private static final String         PROTOCOL = "http";

    private String                      hostname;
    private ObjectMapper objectMapper = new ObjectMapper();
    private int                         port;

    public AddyCaddyClientImpl(String hostname) {
        this(hostname, DEFAULT_PORT);
    }

    public AddyCaddyClientImpl(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public String create(ContactPointDto contactPointDto) throws Exception {
        String result = postAndReturn(AddyCaddyConstants.PATH_CREATE, contactPointDto);
        return result;
    }

    private String postAndReturn(String path, ContactPointDto contactPointDto) throws Exception{
        URIBuilder builder = startUri(path);
        String json = objectMapper.writeValueAsString(contactPointDto);
        builder.setParameter(AddyCaddyConstants.KEY_CONTACT_POINT, json);
        URI uri = toUri(builder);
        HttpPost httpPost = new HttpPost(uri);
        String response = readResponse(httpPost);

        return response;
    }

    private URI toUri(URIBuilder builder) throws Exception {
        URI uri;
        try {
            uri = builder.build();
        } catch (URISyntaxException use) {
            String msg = "Error building URI";
            throw new Exception(msg, use);
        }

        return uri;
    }

    private String readResponse(HttpRequestBase request) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(request);
        } catch (IOException ioe) {
            String msg = "Error processing request " + request.toString();
            throw new Exception(msg, ioe);
        }

        StringBuilder sb = new StringBuilder();
        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                InputStreamReader reader = new InputStreamReader(response.getEntity().getContent());
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null ) {
                    sb.append(line);
                }
            }
            else {
                String msg = "Invalid response:" + response.getStatusLine().getStatusCode() + ", to:" + request.toString();
                throw new Exception(msg);
            }
        } catch (IOException ioe) {
            String msg = "Error reading request " + request.toString();
            throw new Exception(msg, ioe);
        } finally {
            try {
                response.close();
            } catch (IOException ioe) {
                String msg = "Error closing request " + request.toString();
                throw new Exception(msg, ioe);
            }
        }

        return sb.toString();
    }

    private URIBuilder startUri(String path) {
        URIBuilder builder = new URIBuilder()
                .setScheme(PROTOCOL)
                .setHost(hostname)
                .setPath(path)
                .setPort(port);

        return builder;
    }

    @Override
    public ContactPointDto[] findByCustomerId(String customerId) throws Exception {
        URIBuilder builder = startUri(AddyCaddyConstants.PATH_FIND_BY_CUST_ID);
        builder.setParameter(AddyCaddyConstants.KEY_CUSTOMER_ID, customerId);
        String response = getResponse(builder);

        ContactPointDto[] result = objectMapper.readValue(response, ContactPointDto[].class);
        return result;
    }

    private String getResponse(URIBuilder uriBuilder) throws Exception {
        URI uri = toUri(uriBuilder);
        HttpGet httpGet = new HttpGet(uri);
        String result = readResponse(httpGet);

        return result;
    }

    @Override
    public ContactPointDto[] search(String searchBy, String searchKey) throws Exception {
        URIBuilder builder = startUri(AddyCaddyConstants.PATH_SEARCH);
        builder.setParameter(AddyCaddyConstants.KEY_SEARCH_BY, searchBy);
        builder.setParameter(AddyCaddyConstants.KEY_SEARCH_KEY, searchKey);
        String response = getResponse(builder);

        ContactPointDto[] result = objectMapper.readValue(response, ContactPointDto[].class);
        return result;
    }

    @Override
    public String update(ContactPointDto contactPointDto) throws Exception {
        String result = postAndReturn(AddyCaddyConstants.PATH_UPDATE, contactPointDto);
        return result;
    }
}
