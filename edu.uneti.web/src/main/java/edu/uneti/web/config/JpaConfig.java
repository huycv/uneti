package edu.uneti.web.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "edu.uneti.infra")
@EntityScan(basePackages = "edu.uneti.infra")
public class JpaConfig extends JpaBaseConfiguration {

	@Value("${eclipselink.targetDbProperties}")
	private String targetDbProperties;
	
	protected JpaConfig(DataSource dataSource, JpaProperties properties,
			ObjectProvider<JtaTransactionManager> jtaTransactionManager,
			ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
		super(dataSource, properties, jtaTransactionManager, transactionManagerCustomizers);
	}
	
	@Override
	protected String[] getPackagesToScan() {
		return new String[]{"edu.uneti.infra"};
	}

	@Override
	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
		return new EclipseLinkJpaVendorAdapter();
	}

	@Override
	protected Map<String, Object> getVendorProperties() {
		HashMap<String, Object> map = new HashMap<>();
		map.put(PersistenceUnitProperties.WEAVING, this.detectWeavingMode());
		map.put(PersistenceUnitProperties.DDL_GENERATION, "none");
		map.put(PersistenceUnitProperties.TARGET_DATABASE_PROPERTIES, targetDbProperties);
		map.put(PersistenceUnitProperties.QUERY_CACHE, "false");
		map.put(PersistenceUnitProperties.CACHE_SHARED_DEFAULT, "false");
		return map;
	}

	private Object detectWeavingMode() {
		return InstrumentationLoadTimeWeaver.isInstrumentationAvailable() ? "true" : "static";
	}

}
