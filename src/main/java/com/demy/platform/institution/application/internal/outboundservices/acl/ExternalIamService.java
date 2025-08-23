package com.demy.platform.institution.application.internal.outboundservices.acl;

import com.demy.platform.iam.interfaces.acl.IamContextFacade;
import com.demy.platform.shared.domain.model.valueobjects.AcademyId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalIamService {

    private final IamContextFacade iamContextFacade;

    public ExternalIamService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Optional<AcademyId> fetchCurrentAcademyId() {
        var academyId = iamContextFacade.fetchAuthenticatedUserTenantId();
        return academyId == 0L ? Optional.empty() : Optional.of(new AcademyId(academyId));
    }
}
