package com.luigivismara.microservice.utils;

import com.luigivismara.microservice.dto.response.GenericResponses;

/**
 * The Interface PageableTools.
 */
public interface PageableTools {

  /**
   * Gets the pageable list.
   *
   * @param entityObject the entity object
   * @param repositoryValueObject the repository value object
   * @return the pageable list
   */
  GenericResponses<?> getPageableList(Object entityObject, Object repositoryValueObject);
}
