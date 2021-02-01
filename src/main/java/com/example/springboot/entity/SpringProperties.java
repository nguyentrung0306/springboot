package com.example.springboot.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "spring",
        ignoreUnknownFields = true
)
public class SpringProperties {

    private final SpringProperties.DataSource dataSource = new SpringProperties.DataSource();
    private final SpringProperties.Application application = new SpringProperties.Application();

    public SpringProperties() {

    }

    public SpringProperties.DataSource getDataSource() {
        return this.dataSource;
    }

    public SpringProperties.Application getApplication() {
        return this.application;
    }

    public static class Application {
        private String name;

        public Application() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class DataSource {
        private String url;
        private String username;
        private String password;
        private String driverClassName;

        public DataSource() {
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }
    }
}
