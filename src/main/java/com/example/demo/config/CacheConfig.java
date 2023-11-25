package com.example.demo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheConfig {

    //    EhCacheManagerFactoryBean	CacacheManager의 적절한 관리 및 인스턴스를 제공하는데 필요하며 EhCache 설정 리소스를 구성한다.
    //    setConfigLocation	지정된 경로를 통해 EhCache 설정 리소스를 로드한다. (미지정시 루트의 ehcache.xml파일을 찾음).
    //    setShared	CacheManager 싱글톤 여부 (default= false).
    //    @EnableCaching	Annotation을 사용하여 캐싱 기능을 이용하겠다고 선언.

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(
                new ClassPathResource("cache/ehcache.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(ehCacheManagerFactoryBean.getObject());
        return ehCacheCacheManager;
    }

}
