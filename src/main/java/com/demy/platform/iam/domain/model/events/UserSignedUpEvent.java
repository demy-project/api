package com.demy.platform.iam.domain.model.events;

import com.demy.platform.iam.domain.model.aggregates.User;
import com.demy.platform.iam.domain.model.entities.Role;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class UserSignedUpEvent extends ApplicationEvent {

    private final User user;
    private final List<Role> roles;

    public UserSignedUpEvent(Object source, User user, List<Role> roles) {
        super(source);
        this.user = user;
        this.roles = roles;
    }
}
