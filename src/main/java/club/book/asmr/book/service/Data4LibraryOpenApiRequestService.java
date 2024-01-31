package club.book.asmr.book.service;

import club.book.asmr.common.exception.CustomException;
import club.book.asmr.common.exception.ErrorStatus;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class Data4LibraryOpenApiRequestService {
    private final RestTemplate restTemplate;
    @Value("${app.library.url}")
    private String LIBRARY_URL;
    @Value("${app.library.key}")
    private String LIBRARY_KEY;

    public List<String> getBestSellers() {
        ResponseEntity<String> responseEntity = getLibraryData(LIBRARY_KEY,
                "2023-01-01",
                "2024-01-29",
                20,
                1000);
        String response = responseEntity.getBody();

        return parseXmlToIsbnList(response);
    }

    private ResponseEntity<String> getLibraryData(String authKey, String startDt, String endDt, int age, int pageSize) {
        String url = UriComponentsBuilder.fromHttpUrl(LIBRARY_URL)
                .queryParam("authKey", authKey)
                .queryParam("startDt", startDt)
                .queryParam("endDt", endDt)
                .queryParam("age", age)
                .queryParam("pageSize", pageSize)
                .toUriString();

        return restTemplate.getForEntity(url, String.class);
    }

    private List<String> parseXmlToIsbnList(String xmlResponse) {
        List<String> isbnList = new ArrayList<>();

        try {
            // XML을 JSON으로 변환
            JSONObject jsonResponse = org.json.XML.toJSONObject(xmlResponse);

            // isbn13 추출
            JSONArray docs = jsonResponse.getJSONObject("response").getJSONObject("docs").getJSONArray("doc");
            for (int i = 0; i < docs.length(); i++) {
                String isbn13 = docs.getJSONObject(i).getString("isbn13");
                isbnList.add(isbn13);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ErrorStatus.CANNOT_GET_DATA_FROM_ALADIN);
        }
        log.info("data size: {}", isbnList.size());
        return isbnList;
    }
}
