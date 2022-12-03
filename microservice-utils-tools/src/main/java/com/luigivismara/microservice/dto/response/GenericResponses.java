package com.luigivismara.microservice.dto.response;

import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * The type Generic responses.
 *
 * @param <Body>  the type parameter
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponses<Body> {

    @NonNull
    private StatusDTO status;

    @NonNull
    private Body body;

    public ResponseEntity<GenericResponses<Body>> getResponseHttp(GenericResponses<Body> genericResponse){
        return ResponseEntity.status(genericResponse.getStatus().getHttpStatus()).body(genericResponse);
    }

    public GenericResponses(@NonNull Body body) {
        this.body = body;
    }
}
