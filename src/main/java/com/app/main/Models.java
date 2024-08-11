package com.app.main;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Models {




    public static class MainModels {
        public static class RespStructure{
            @JsonProperty("apiVersion")
            public String apiVersion;
            @JsonProperty("data")
            public data data;
            @JsonProperty("error")
            public data error;
        }

        public static class data{
            @JsonProperty("results")
            public List<Result> results;
        }

        public static class Result {
            @JsonProperty("adult")
            public boolean adult;
            @JsonProperty("first_year")
            public String first_year;
            @JsonProperty("last_year")
            public String last_year;
            @JsonProperty("highlight")
            public String highlight;
            @JsonProperty("id")
            public Integer id;
            @JsonProperty("image")
            public String image;
            @JsonProperty("name")
            public String name;
            @JsonProperty("platforms")
            public List<String> platforms;
            @JsonProperty("type")
            public Integer type;
            @JsonProperty("url")
            public String url;
            @JsonProperty("year")
            public String year;
            @JsonProperty("num_games")
            public Integer num_games;
        }

    }

}
