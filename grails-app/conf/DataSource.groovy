dataSource {
   pooled = true
    dbCreate = none
	pooled = true
	url = "jdbc:oracle:thin:@//129.144.7.55:1521/PDB2.a16655.oraclecloud.internal"
	driverClassName = "oracle.jdbc.OracleDriver"
	username = "newwebtbz"
	password = "webtbz1864"
	dialect = "org.hibernate.dialect.Oracle10gDialect"
	//dialect = "org.hibernate.dialect.H2Dialect"
}
hibernate {
	cache.use_second_level_cache = true
	jdbc.use_get_generated_keys = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'org.hibernate.cache.SingletonEhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
	format_sql = true
}

// environment specific settings
environments {
    development {
        dataSource {
            pooled = true
		    dbCreate = "none"
			pooled = "true"
			url = "jdbc:oracle:thin:@//129.144.7.55:1521/PDB2.a16655.oraclecloud.internal"
			driverClassName = "oracle.jdbc.OracleDriver"
			username = "newwebtbz"
			password = "webtbz1864"
			dialect = "org.hibernate.dialect.Oracle10gDialect"
			properties {
				jmxEnabled = true
				initialSize = 5
				maxActive = 50
				minIdle = 5
				maxIdle = 25
				maxWait = 10000
				maxAge = 10 * 60000
				timeBetweenEvictionRunsMillis = 5000
				minEvictableIdleTimeMillis = 60000
				validationQuery = "SELECT 1"
				validationQueryTimeout = 3
				validationInterval = 15000
				testOnBorrow = true
				testWhileIdle = true
				testOnReturn = false
				jdbcInterceptors = "ConnectionState;StatementCache(max=200)"
				defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
			 }
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }
    production {
        dataSource {
			pooled = true
			dbCreate = none
			pooled = true
			url = "jdbc:oracle:thin:@//129.144.7.55:1521/PDB2.a16655.oraclecloud.internal"
			driverClassName = "oracle.jdbc.OracleDriver"
			username = "newwebtbz"
			password = "webtbz1864"
			dialect = "org.hibernate.dialect.Oracle10gDialect"
        }
    }
}

log4j
