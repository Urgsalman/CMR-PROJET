package com.cmr.dossier_retraite.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.cmr.dossierretraite.repository")
@EntityScan(basePackages = "com.cmr.dossierretraite.model")
public class DatabaseConfig {
}
