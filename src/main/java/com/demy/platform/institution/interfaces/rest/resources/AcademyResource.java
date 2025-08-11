package com.demy.platform.institution.interfaces.rest.resources;

/**
 * Represents a resource for an Academy in the REST API.
 * <p>
 * This record is used to transfer data related to an Academy.
 *
 * @param id the unique identifier of the academy
 * @param administratorId the ID of the administrator managing the academy
 * @param ruc the Registro Único de Contribuyentes (RUC) of the academy
 *
 * @author Salim Ramirez
 * @since 1.0.0
 */
public record AcademyResource(
        Long id,
        String administratorId,
        String academyName,
        String academyDescription,
        String streetAddress,
        String emailAddress,
        String phoneNumber,
        String ruc
) {
}
