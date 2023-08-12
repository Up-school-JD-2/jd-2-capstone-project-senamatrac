package io.upschool.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    @JsonProperty("Success")
    private boolean isSuccess;
    @JsonProperty("Status")
    private int status;
    @Builder.Default
    @JsonProperty("Current Timestamp")
    private LocalDateTime currentTimestamp = LocalDateTime.now();
    @JsonProperty("Data")
    private T responseBody;
    @JsonProperty("Error")
    private Map<String, T> errorBody;
}
