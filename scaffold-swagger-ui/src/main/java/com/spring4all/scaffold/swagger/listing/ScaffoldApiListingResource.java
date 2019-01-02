package com.spring4all.scaffold.swagger.listing;

import io.swagger.annotations.ApiOperation;
import io.swagger.jaxrs.listing.BaseApiListingResource;
import io.swagger.models.Swagger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * @author fangzhibin
 */
@Path("/scaffold/swagger.{type:json|yaml}")
public class ScaffoldApiListingResource extends BaseApiListingResource {

  @Context
  ServletContext context;

  @GET
  @Produces({MediaType.APPLICATION_JSON, "application/yaml"})
  @ApiOperation(value = "The swagger definition in either JSON or YAML", hidden = true)
  public Response getListing(
      @Context Application app,
      @Context ServletConfig sc,
      @Context HttpHeaders headers,
      @Context UriInfo uriInfo,
      @Context HttpServletRequest servletRequest,
      @PathParam("type") String type) {
    Swagger swagger = process(app, context, sc, headers, uriInfo);
    if (swagger == null) {
      return Response.status(404).build();
    }
    if (swagger.getHost() == null) {
      swagger.setHost(servletRequest.getServerName() + ":" + servletRequest.getServerPort());
    }
    if (StringUtils.isNotBlank(type) && type.trim().equalsIgnoreCase("yaml")) {
      return getListingYamlResponse(app, context, sc, headers, uriInfo);
    } else {
      return getListingJsonResponse(app, context, sc, headers, uriInfo);
    }
  }
}
