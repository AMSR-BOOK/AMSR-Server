package club.book.asmr.book.service;

import club.book.asmr.book.data.AladinBookAttribute;
import club.book.asmr.book.entity.Book;
import club.book.asmr.common.exception.CustomException;
import club.book.asmr.common.exception.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class AladinOpenApiRequestService {
    private final RestTemplate restTemplate;
    @Value("${app.aladin.url}")
    private String ALADIN_URL;
    @Value("${app.aladin.key}")
    private String ALADIN_TTB_KEY;

    public Book getBookInfoByIsbn(String isbn) {
        try {
            AladinBookAttribute aladinBookAttribute = requestBookAttribute(isbn);
            return aladinBookAttribute.mapToEntity();
        } catch (Exception e) {
            throw new CustomException(ErrorStatus.CANNOT_GET_DATA_FROM_ALADIN);
        }
    }

    private AladinBookAttribute requestBookAttribute(String isbn) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(ALADIN_URL)
                .queryParam("ttbkey", ALADIN_TTB_KEY)
                .queryParam("itemIdType", "ISBN13")
                .queryParam("ItemId", isbn)
                .queryParam("output", "js")
                .queryParam("Version", "20131101")
                .queryParam("Cover", "Big")
                .queryParam("OptResult", "packing");

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            try {
                JSONObject json = new JSONObject(responseEntity.getBody());
                return AladinBookAttribute.setAttributes(json);
            } catch (Exception e) {
                throw new RuntimeException("Error mapping JSON response to AladinBookAttribute: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Error fetching book information from Aladin API. Status code: " +
                    responseEntity.getStatusCode());
        }
    }
}
