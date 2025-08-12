package com.demy.platform.institution.interfaces.rest.controllers;

import com.demy.platform.institution.domain.services.AcademyCommandService;
import com.demy.platform.institution.interfaces.rest.resources.AcademyResource;
import com.demy.platform.institution.interfaces.rest.resources.RegisterAcademyResource;
import com.demy.platform.institution.interfaces.rest.transform.AcademyResourceFromEntityAssembler;
import com.demy.platform.institution.interfaces.rest.transform.RegisterAcademyCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/academies", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Academies", description = "Endpoints for managing academies")
public class AcademiesController {

    private final AcademyCommandService academyCommandService;

    public AcademiesController(AcademyCommandService academyCommandService) {
        this.academyCommandService = academyCommandService;
    }

    @PostMapping
    public ResponseEntity<AcademyResource> registerAcademy(@RequestBody RegisterAcademyResource resource) {
        var registerAcademyCommand = RegisterAcademyCommandFromResourceAssembler.toCommandFromResource(resource);
        var academy = academyCommandService.handle(registerAcademyCommand);
        if (academy.isEmpty()) return ResponseEntity.badRequest().build();
        var academyEntity = academy.get();
        var academyResource = AcademyResourceFromEntityAssembler.toResourceFromEntity(academyEntity);
        return new ResponseEntity<>(academyResource, HttpStatus.CREATED);
    }
}
