package com.ecommerce.ecommnerce.common.filedencryption;

import com.ecommerce.ecommnerce.common.encryption.EncryptorDecryptor;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.jasypt.util.text.BasicTextEncryptor;

import java.lang.reflect.Field;


// Not Using

@Component
public class EntityEncryptionListener {


    private BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

    @PrePersist
    public void encrypt(Object entity) {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(EncryptedField.class)) {
                String value = (String) getFieldValue(entity, field);
                if (value != null) {
                    setFieldValue(entity, field, EncryptorDecryptor.encrypt(value));
                }
            }
        }
    }

    @PostLoad
    public void decrypt(Object entity) {
        System.out.println("hshfhs");
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(EncryptedField.class)) {
                String value = (String) getFieldValue(entity, field);
                if (value != null) {
                    setFieldValue(entity, field, EncryptorDecryptor.decrypt(value));
                }
            }
        }
    }

    private Object getFieldValue(Object entity, Field field) {
        try {
            field.setAccessible(true);
            return field.get(entity);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error accessing field value", e);
        }
    }

    private void setFieldValue(Object entity, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(entity, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error setting field value", e);
        }
    }
}
