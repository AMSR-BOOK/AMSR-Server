package club.book.asmr.book.data;

import club.book.asmr.book.entity.Book;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

@Data
public class AladinBookAttribute {
    // item <
    @JsonProperty("item.title")
    private String title;
    @JsonProperty("item.author")
    private String author;
    @JsonProperty("item.pubDate")
    private String pubDate;
    @JsonProperty("item.description")
    private String description;
    @JsonProperty("item.isbn13")
    private String isbn13;
    @JsonProperty("item.priceStandard")
    private Integer priceStandard;
    @JsonProperty("item.cover")
    private String cover;
    @JsonProperty("item.categoryName")
    private String categoryName;
    @JsonProperty("item.publisher")
    private String publisher;
    // item < subInfo
    @JsonProperty("item.subInfo.subTitle")
    private String subTitle;
    @JsonProperty("item.subInfo.itemPage")
    private Integer itemPage;
    // item < subInfo < packing
    @JsonProperty("item.subInfo.packing.sizeDepth")
    private Integer sizeDepth;
    @JsonProperty("item.subInfo.packing.sizeWidth")
    private Integer sizeWidth;
    @JsonProperty("item.subInfo.packing.sizeHeight")
    private Integer sizeHeight;
    // custom attribute
    private String sideCover;

    public static AladinBookAttribute setAttributes(JSONObject json) throws JSONException {
        JSONObject item = json.getJSONArray("item").getJSONObject(0);
        JSONObject subInfo = item.getJSONObject("subInfo");
        JSONObject packing = subInfo.getJSONObject("packing");

        AladinBookAttribute bookAttribute = new AladinBookAttribute();
        bookAttribute.setTitle(item.getString("title"));
        bookAttribute.setAuthor(item.getString("author"));
        bookAttribute.setPubDate(item.getString("pubDate"));
        bookAttribute.setDescription(item.getString("description"));
        bookAttribute.setIsbn13(item.getString("isbn13"));
        bookAttribute.setPriceStandard(item.getInt("priceStandard"));
        bookAttribute.setCover(item.getString("cover"));
        bookAttribute.setCategoryName(item.getString("categoryName"));
        bookAttribute.setPublisher(item.getString("publisher"));

        bookAttribute.setSubTitle(subInfo.getString("subTitle"));
        bookAttribute.setItemPage(subInfo.getInt("itemPage"));

        bookAttribute.setSizeDepth(packing.getInt("sizeDepth"));
        bookAttribute.setSizeWidth(packing.getInt("sizeWidth"));
        bookAttribute.setSizeHeight(packing.getInt("sizeHeight"));

        bookAttribute.setSideCover(bookAttribute.convertImageUrl(bookAttribute.getCover()));

        return bookAttribute;
    }

    private String convertImageUrl(String cover) {
        return cover
                .replaceAll("/(cover\\d*)/", "/spineshelf/")
                .replaceAll("_\\d\\.jpg", "_d.jpg");
    }

    public Book mapToEntity(AladinBookAttribute this) {
        return Book.builder()
                .isbn(this.getIsbn13())
                .title(this.getTitle())
                .itemPage(this.getItemPage())
                .description(this.getDescription())
                .frontCoverImageUrl(this.getCover())
                .sideCoverImageUrl(this.getSideCover())
                .sizeDepth(this.getSizeDepth())
                .sizeWidth(this.getSizeWidth())
                .sizeHeight(this.getSizeHeight())
                .publishedDate(parseDateString(this.getPubDate()))
                .author(this.getAuthor())
                .publisher(this.getPublisher())
                .build();
    }

    private LocalDate parseDateString(String dateString) {
        // Assuming pubDate format is "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }
}
