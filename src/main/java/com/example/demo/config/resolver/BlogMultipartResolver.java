package com.example.demo.config.resolver;

import com.example.demo.common.properties.properties.BlogFileProperties;
import com.example.demo.exception.BlogMultipartException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.testcontainers.shaded.com.google.common.io.Files.getFileExtension;

@Slf4j
@RequiredArgsConstructor
public class BlogMultipartResolver extends CommonsMultipartResolver {

    private final BlogFileProperties blogFileProperties;

    @Override
    public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {

        //  부모 메소드 실행.
        MultipartHttpServletRequest multipartHttpServletRequest = super.resolveMultipart(request);

        //  추가적으로 custom한 부분.
        BlogFileProperties.BlogFileValidateProperties blogFileValidateProperties = blogFileProperties.getBlogFileValidateProperties();
        List<String> imageFileExtension = blogFileValidateProperties.getImageFileExtension();

        Long maxFileSize = blogFileValidateProperties.getMaximumFileSize();
        Long minFileSize = blogFileValidateProperties.getMinimumFileSize();
        Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();

        //  해당 부분에서 전달받은 파일에 대한 검증을 처리한다.
        while (fileNames.hasNext()) {
            String key = fileNames.next();
            for (MultipartFile files : multipartHttpServletRequest.getFiles(key)) {
                String fileExtension = getFileExtension(Objects.requireNonNull(files.getOriginalFilename()));
                Long size = files.getSize();
                log.info("file extension = {}, size = {}", fileExtension, size);
                if (!imageFileExtension.contains(fileExtension) || size <= minFileSize || size > maxFileSize) {
                    log.error("file validate error extension = {}, size = {}", fileExtension, size);
                    throw new BlogMultipartException(request.getRequestURI(), fileExtension, size);
                }
            }
        }

        return multipartHttpServletRequest;
    }

}
