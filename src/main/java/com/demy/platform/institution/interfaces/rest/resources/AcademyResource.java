package com.demy.platform.institution.interfaces.rest.resources;

/**
 * Represents a resource for an Academy in the REST API.
 * <p>
 * This record is used to transfer data related to an Academy.
 *
 * @param id the unique identifier of the academy
 * @param administratorResource the administrator resource associated with the academy
 * @param academyName the name of the academy
 * @param academyDescription a description of the academy
 * @param streetAddress the street address of the academy
 * @param emailAddress the email address of the academy
 * @param phoneNumber the phone number of the academy
 * @param ruc the Registro Único de Contribuyentes (RUC) of the academy
 *
 * @author Salim Ramirez
 * @since 1.0.0
 */
public record AcademyResource(
        Long id,
        AdministratorResource administratorResource,
        String academyName,
        String academyDescription,
        String streetAddress,
        String emailAddress,
        String phoneNumber,
        String ruc
) {
}
