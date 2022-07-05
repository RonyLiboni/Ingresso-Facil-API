package br.com.IngressoFacilAPI.config;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("testes")
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public abstract class RepositoryTestConfig {
	
}
