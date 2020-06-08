package com.nk.hoadonService;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class TimeDeserializer extends JsonDeserializer<LocalDate>{

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) 
        throws IOException, JsonProcessingException {

        String date = p.getText();
        LocalDate localDate = LocalDate.parse(date);
        return localDate;
    }
	
}
