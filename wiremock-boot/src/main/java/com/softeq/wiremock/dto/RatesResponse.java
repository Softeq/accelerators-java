package com.softeq.wiremock.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class RatesResponse {

    private final String cityName;
    private final Integer currencyId;
    private final LocalDateTime date;
    private final String currencyAbbreviation;
    private final Integer currencyScale;
    private final String currencyName;
    private final BigDecimal currencyOfficialRate;
    private final RatesResponseStatus ratesResponseStatus;

    @JsonCreator
    public RatesResponse(@JsonProperty("City_Name") String cityName,
                         @JsonProperty("Cur_ID") int currencyId,
                         @JsonProperty("Date") LocalDateTime date,
                         @JsonProperty("Cur_Abbreviation") String currencyAbbreviation,
                         @JsonProperty("Cur_Scale") int currencyScale,
                         @JsonProperty("Cur_Name") String currencyName,
                         @JsonProperty("Cur_OfficialRate") BigDecimal currencyOfficialRate,
                         @JsonProperty("Response_Status") RatesResponseStatus ratesResponseStatus) {
        this.cityName = cityName;
        this.currencyId = currencyId;
        this.date = date;
        this.currencyAbbreviation = currencyAbbreviation;
        this.currencyScale = currencyScale;
        this.currencyName = currencyName;
        this.currencyOfficialRate = currencyOfficialRate;
        this.ratesResponseStatus = ratesResponseStatus;
    }

    public enum RatesResponseStatus {
        SUCCESS, REJECTED
    }
}
