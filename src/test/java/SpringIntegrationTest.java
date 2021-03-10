import com.os.orders.OrderBackendApp;
import io.cucumber.spring.CucumberContextConfiguration;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(classes = OrderBackendApp.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    private static final String JWT_HEADER = "{\"typ\":\"JWT\",\"alg\":\"HS256\"}";
    ResponseEntity<String> response = null;

    void executeGet(String url) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> claims = new HashMap<>();
        String token = doGenerateToken(claims, "user");
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        response = restTemplate.exchange(
            url, HttpMethod.GET, new HttpEntity<Object>(headers),
            String.class);
    }

    void executePost() throws IOException, JSONException {
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> claims = new HashMap<>();
        String token = doGenerateToken(claims, "user");
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonStr = "{\n" +
            "\t\"orderNo\":\"1\",\n" +
            "\t\"status\":\"CREATED\",\n" +
            "\t\"buyer\":\"buyer1\",\n" +
            "\t\"seller\":\"seller1\",\n" +
            "\t\"ordertotal\":\"200\",\n" +
            "\t\"orderLines\":[\n" +
            "\t\t{\n" +
            "\t\t\t\"orderLineNo\":\"1\",\n" +
            "\t\t\t\"status\":\"CREATED\",\n" +
            "\t\t\t\"orderedQty\":\"2\",\n" +
            "\t\t\t\"linePrice\":\"200\",\n" +
            "\t\t\t\"items\":[\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\"description\":\"Cadbury Dairy milk\",\n" +
            "\t\t\t\t\"itemId\":\"123456\",\n" +
            "\t\t\t\t\"unitOfMeasure\":\"EACH\",\n" +
            "\t\t\t\t\"itemprice\":\"100\",\n" +
            "\t\t\t\t\"weight\":\"2\",\n" +
            "\t\t\t\t\"weightUOM\":\"gms\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}\n";
        JSONObject json = new JSONObject(jsonStr);
        HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        response =  restTemplate.exchange(
            "http://localhost:8080/api/purchase-orders", HttpMethod.POST, entity, String.class);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        String secret = "ODZiNjcxMzdlYjBiZGNiZDFmOGE1MWQ4NDVhZDMxMmI5M2RiOTljZWYzOTNhMjZiOWU0MWYzYzAwMzQwZjgxZjg1ZTA1ZjZiNDE2NjQzZDU5NmU2NmJmZDRjODhjMjZhZmZiNDE3NjEwZTNmZjk2M2FlMzkxMjQxMGUzNTljMzY=";
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 86400 * 1000)).claim("auth", "role")
            .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
