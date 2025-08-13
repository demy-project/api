package com.demy.platform.institution.interfaces.rest.transform;

import com.demy.platform.institution.domain.model.commands.RegisterAcademyCommand;
import com.demy.platform.institution.domain.model.valueobjects.AcademyDescription;
import com.demy.platform.institution.domain.model.valueobjects.AcademyName;
import com.demy.platform.institution.domain.model.valueobjects.AdministratorId;
import com.demy.platform.institution.domain.model.valueobjects.Ruc;
import com.demy.platform.institution.interfaces.rest.resources.RegisterAcademyResource;
import com.demy.platform.shared.domain.model.valueobjects.EmailAddress;
import com.demy.platform.shared.domain.model.valueobjects.PhoneNumber;
import com.demy.platform.shared.domain.model.valueobjects.StreetAddress;

public class RegisterAcademyCommandFromResourceAssembler {
    public static RegisterAcademyCommand toCommandFromResource(RegisterAcademyResource resource) {
        return new RegisterAcademyCommand(
                new AdministratorId(resource.administratorId()),
                new AcademyName(resource.academyName()),
                new AcademyDescription(resource.academyDescription()),
                new StreetAddress(
                        resource.street(),
                        resource.district(),
                        resource.province(),
                        resource.department()
                ),
                new EmailAddress(resource.emailAddress()),
                new PhoneNumber(
                        resource.countryCode(),
                        resource.phone()
                ),
                new Ruc(resource.ruc())
        );
    }
}
