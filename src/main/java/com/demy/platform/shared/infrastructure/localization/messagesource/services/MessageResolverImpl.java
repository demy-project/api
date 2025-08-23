package com.demy.platform.shared.infrastructure.localization.messagesource.services;

import com.demy.platform.shared.application.internal.outboundservices.localization.LocalizedMessageResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageResolverImpl implements LocalizedMessageResolver {

    private final MessageSource messageSource;

    public MessageResolverImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) {
        try {
            return messageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            return code + (args != null && args.length > 0 ? formatArgs(args) : "");
        }
    }

    @Override
    public String getMessage(String code, Locale locale) {
        return getMessage(code, null, locale);
    }

    private String formatArgs(Object[] args) {
        StringBuilder sb = new StringBuilder(" [");
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if (i < args.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
