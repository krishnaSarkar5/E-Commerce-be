package com.ecommerce.ecommnerce.product.validator;

import com.ecommerce.ecommnerce.product.annotations.ImageSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class ImageSizeValidator implements ConstraintValidator<ImageSize, String> {
    private int minimumSize;

    @Override
    public void initialize(ImageSize constraintAnnotation) {
        minimumSize = constraintAnnotation.minSize();
    }

    @Override
    public boolean isValid(String imageUrl, ConstraintValidatorContext context) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return true; // Validation will be skipped if the field is not provided
        }

        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");

            int fileSize = connection.getContentLength(); // Size in bytes

            if (fileSize != -1) {
                double fileSizeInMB = fileSize / (1024.0 * 1024.0); // Convert bytes to MB
                return fileSizeInMB <= minimumSize;
            } else {
                // Unable to determine the file size
                return false;
            }
        } catch (IOException e) {
            // Error occurred while validating the URL or fetching the image
            return false;
        }
    }
}