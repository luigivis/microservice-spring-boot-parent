package com.luigivismara.microservice.utils;

import org.springframework.stereotype.Service;
import com.luigivismara.microservice.dto.response.GenericResponses;

/**
 * The Interface PageableTools.
 */
@Service
public interface PageableTools {

  /**
   * Gets the pageable list.
   *
   * @param repositoryValueObject the repository value object
   * @return the pageable list
   */
  GenericResponses<?> getPageableList(Object repositoryValueObject);
}
