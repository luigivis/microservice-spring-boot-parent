package com.luigivismara.microservice.utils.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.luigivismara.microservice.dto.response.GenericResponses;
import com.luigivismara.microservice.dto.response.StatusDTO;
import com.luigivismara.microservice.utils.PageableTools;

/**
 * The Class PageableToolsImpl.
 */
@Service
public class PageableToolsImpl implements PageableTools, Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Gets the pageable list.
   *
   * @param repositoryValueObject the repository value object
   * @return the pageable list
   */
  @Bean
  @Override
  public GenericResponses<Object> getPageableList(Object repositoryValueObject) {

    var genericResponses = new GenericResponses<Object>();

    try {

      List<?> listPages = null;

      var pageTuts = (Page<?>) repositoryValueObject;
      listPages = pageTuts.getContent();

      var response = new HashMap<>();
      response.put("value", listPages);
      response.put("currentPage", pageTuts.getNumber());
      response.put("totalItems", pageTuts.getTotalElements());
      response.put("totalPages", pageTuts.getTotalPages());
      
      genericResponses.setStatus(new StatusDTO(HttpStatus.OK));
      genericResponses.setBody(response);

    } catch (Exception e) {
      genericResponses.setStatus(new StatusDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
          e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
    return genericResponses;
  }

}
