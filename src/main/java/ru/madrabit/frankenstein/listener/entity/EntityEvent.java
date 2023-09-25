package ru.madrabit.frankenstein.listener.entity;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class EntityEvent extends ApplicationEvent {

    @Getter
    private final AccessType accessType;

    public EntityEvent(Object entity, AccessType accessType) {
        super(entity);
        this.accessType = accessType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityEvent that)) return false;

        return accessType == that.accessType;
    }

    @Override
    public int hashCode() {
        return accessType != null ? accessType.hashCode() : 0;
    }
}
