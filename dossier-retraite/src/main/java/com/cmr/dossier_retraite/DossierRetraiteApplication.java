package com.cmr.dossier_retraite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cmr.dossier_retraite.repository")
@EntityScan(basePackages = "com.cmr.dossier_retraite.model")
public class DossierRetraiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(DossierRetraiteApplication.class, args);
    }
}