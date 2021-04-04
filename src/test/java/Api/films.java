package Api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.peer.SystemTrayPeer;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class films {

    URIBuilder uriBuilder = new URIBuilder();
    HttpClient httpClient;
    HttpGet httpGet;
    HttpResponse response;
    @Before
    public void beforeMethod() throws URISyntaxException {
        httpClient = HttpClientBuilder.create().build();
       uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("swapi.dev");
    }
    @Test
    public void testFilms() throws IOException, URISyntaxException {

        //https://swapi.dev/api/films/
        //1.launch client
        //2. Find/provide an endpoint/URL
        uriBuilder.setPath("api/films");
        //3. define a HTTP request (action)
        //4. send/execute a request
        httpGet = new HttpGet(uriBuilder.build());
        response = httpClient.execute(httpGet);

        Assert.assertEquals(200,response.getStatusLine().getStatusCode());

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> responseMap=mapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {});
       List< Map<String,Object>>results = (List<Map<String, Object>>)responseMap.get("results");
        Map<String,Object> title = results.get(0);
        System.out.println("title");

        Assert.assertEquals("A New Hope",title.get("title"));

    }
}
