package com.zym.fastplatform.common.common.framework.listener;

import org.hibernate.bytecode.enhance.spi.LazyPropertyInitializer;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.event.internal.DefaultMergeEventListener;
import org.hibernate.event.spi.MergeContext;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.property.access.internal.PropertyAccessStrategyBackRefImpl;
import org.hibernate.type.Type;

public class IgnoreNullEventListener extends DefaultMergeEventListener {
    public static final IgnoreNullEventListener INSTANCE = new IgnoreNullEventListener();

    @Override
    protected void copyValues(EntityPersister persister, Object entity, Object target, SessionImplementor source, MergeContext copyCache) {
        //源目标
        Object[] original = persister.getPropertyValues(entity);
        //存储目标
        Object[] targets = persister.getPropertyValues(target);
        Type[] types = persister.getPropertyTypes();

        Object[] copies = new Object[original.length];
        for (int i = 0; i < types.length; i++) {
            if(original[i] == null||original[i]== LazyPropertyInitializer.UNFETCHED_PROPERTY
                    ||original[i] == PropertyAccessStrategyBackRefImpl.UNKNOWN){
                copies[i] = targets[i];
            }else {
                copies[i] = types[i].replace(original[i],targets[i], source,target,copyCache);
            }
        }
        persister.setPropertyValues(target, copies);
    }
}
