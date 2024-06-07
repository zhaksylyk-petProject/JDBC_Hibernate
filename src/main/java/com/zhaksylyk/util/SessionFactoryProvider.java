package com.zhaksylyk.util;

import com.zhaksylyk.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {
    public static SessionFactory provideSessionFactory()
    {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }
}
