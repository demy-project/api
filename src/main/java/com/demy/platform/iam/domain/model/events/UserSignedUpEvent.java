package com.demy.platform.iam.domain.model.events;

import com.demy.platform.iam.domain.model.entities.Role;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class UserSignedUpEvent extends ApplicationEvent {

    private final Long userId;
    private final List<Role> roles;

    public UserSignedUpEvent(Object source, Long userId, List<Role> roles) {
        super(source);
        this.userId = userId;
        this.roles = roles;
    }
}
