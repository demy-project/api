package com.demy.platform.iam.interfaces.rest.resources;

public record SignInResource(
        String emailAddress,
        String password
) {
}
