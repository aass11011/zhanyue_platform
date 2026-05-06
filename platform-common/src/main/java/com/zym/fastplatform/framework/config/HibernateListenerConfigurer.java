package com.zym.fastplatform.framework.config;

import com.zym.fastplatform.framework.listener.IgnoreNullEventListener;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;


//@Configuration
public class HibernateListenerConfigurer {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry register = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        register.getEventListenerGroup(EventType.MERGE).clear();
        register.getEventListenerGroup(EventType.MERGE).prependListener(IgnoreNullEventListener.INSTANCE);
    }
}
