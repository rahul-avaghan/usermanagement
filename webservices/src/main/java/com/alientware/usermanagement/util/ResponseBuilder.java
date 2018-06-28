package com.alientware.usermanagement.util;

import java.net.URI;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * The Class {@link ResponseBuilder} is a helper for creating HTTP-Response
 * configurations for different use cases.
 *
 * @author Rahul avaghan
 */
public class ResponseBuilder {

    /**
     * Builds a success response for GET request with an entity transfered in
     * the message body and a cache-header deactivating caching of the resource
     * on client side.
     *
     * @param entity to be passed in message body as JSON
     * @return Response with status code 200
     */
    public static Response buildGetSuccess(Object entity) {

        // deactivate caching for client
        CacheControl cc = new CacheControl();
        cc.setNoCache(true);
        cc.setNoStore(true);
        cc.setMustRevalidate(true);
        return Response.status(Response.Status.OK).header("Pragma", "no-cache").header("Expires", 0).cacheControl(cc).entity(entity).build();
    }

    /**
     * Builds a simple success response without further information.
     *
     * @return Response with status code 200
     */
    public static Response buildSimpleSuccess() {
        return Response.ok().build();
    }

    private ResponseBuilder() {
    }

    /**
     * Creates a success response for resources created on the server.
     *
     * @param uriInfo URI context of new resource
     * @param dto PermissionWidgetDto of the Permission
     * @return configured HTTP response with URI of new resource in HTTP
     * location header
     */
    public static Response buildCreateSuccess(UriInfo uriInfo, Object dto) {

        URI uri = uriInfo.getAbsolutePathBuilder().path(dto.toString()).build();
        return Response.created(uri).entity(dto).build();
    }

}
