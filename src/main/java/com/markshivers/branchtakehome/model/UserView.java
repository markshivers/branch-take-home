package com.markshivers.branchtakehome.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserView(
        @JsonProperty("user_name")
        String userName,
        @JsonProperty("display_name")
        String displayName,
        String avatar,
        @JsonProperty("geo_location")
        String geoLocation,
        String email,
        String url,
        @JsonProperty("created_at")
        String createdAt,
        List<RepositoryView> repos) {
}
