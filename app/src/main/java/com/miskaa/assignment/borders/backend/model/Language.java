package com.miskaa.assignment.borders.backend.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "iso639_1",
        "iso639_2",
        "name",
        "nativeName"
})
@Generated("jsonschema2pojo")
public class Language implements Parcelable {

    public static final Parcelable.Creator<Language> CREATOR = new Parcelable.Creator<Language>() {
        @Override
        public Language createFromParcel(Parcel parcel) {
            Language item = new Language();
            item.setIso6392(parcel.readString());
            item.setIso6391(parcel.readString());
            item.setNativeName(parcel.readString());
            item.setName(parcel.readString());
            return item;
        }

        @Override
        public Language[] newArray(int i) {
            return new Language[0];
        }
    };
    @JsonProperty("iso639_1")
    private String iso6391;
    @JsonProperty("iso639_2")
    private String iso6392;
    @JsonProperty("name")
    private String name;
    @JsonProperty("nativeName")
    private String nativeName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("iso639_1")
    public String getIso6391() {
        return iso6391;
    }

    @JsonProperty("iso639_1")
    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    @JsonProperty("iso639_2")
    public String getIso6392() {
        return iso6392;
    }

    @JsonProperty("iso639_2")
    public void setIso6392(String iso6392) {
        this.iso6392 = iso6392;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("nativeName")
    public String getNativeName() {
        return nativeName;
    }

    @JsonProperty("nativeName")
    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(iso6392);
        parcel.writeString(iso6391);
        parcel.writeString(nativeName);
        parcel.writeString(name);
    }
}