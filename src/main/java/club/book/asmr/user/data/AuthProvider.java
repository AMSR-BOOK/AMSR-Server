package club.book.asmr.user.data;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthProvider {
    GOOGLE("google"),
    KAKAO("kakao"),
    NAVER("naver"),
    EMPTY("");

    private final String provider;

    public static AuthProvider findByCode(String code) {
        return Arrays.stream(AuthProvider.values())
                .filter(provider -> provider.getProvider().equals(code))
                .findFirst()
                .orElse(EMPTY);
    }
}