package com.compassion.user.management.controller.spec;

import com.compassion.user.management.domain.vo.ApiErrorResponseVO;
import com.compassion.user.management.domain.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserControllerSpec {

    String NOT_FOUND_ERROR_EXAMPLE = """
                {
                    "code": "NOT_FOUND",
                    "message": "Not found"
                }
            """;


    @Operation(summary = "Create a new user",
            description = "Endpoint to create a new user in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserVO.class)))
    })
    ResponseEntity<UserVO> create(@RequestBody final UserVO userVO);

    @Operation(summary = "Get all users",
            description = "Endpoint to retrieve a list of all users.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "List of users retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserVO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Any users founded.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponseVO.class),
                            examples = @ExampleObject(value = NOT_FOUND_ERROR_EXAMPLE))
            )

    })
    ResponseEntity<List<UserVO>> findAll();

    @Operation(summary = "Find user by ID",
            description = "Endpoint to retrieve a specific user by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserVO.class))
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "User not founded.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorResponseVO.class),
                            examples = @ExampleObject(value = NOT_FOUND_ERROR_EXAMPLE))
            )
    })
    ResponseEntity<UserVO> findById(
            @Parameter(description = "Unique ID of the user to be retrieved", required = true)
            @PathVariable final String userId
    );

    @Operation(summary = "Update user",
            description = "Endpoint to update an existing user's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserVO.class)))
    })
    ResponseEntity<UserVO> update(@RequestBody final UserVO userVO);

    @Operation(summary = "Delete user by ID",
            description = "Endpoint to delete a specific user by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully",
                    content = @Content)
    })
    ResponseEntity<Void> deleteById(
            @Parameter(description = "Unique ID of the user to be deleted", required = true)
            @PathVariable final String userId
    );
}
