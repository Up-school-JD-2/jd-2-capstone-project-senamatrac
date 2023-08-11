package io.upschool.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private boolean isSuccess;
    private int status;
    @Builder.Default
    @JsonProperty("Current Timestamp")
    private LocalDateTime currentTimestamp = LocalDateTime.now();
    private Map<String, T> responseBody;
}
