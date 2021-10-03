package com.datasource.multiconnect.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import com.datasource.multiconnect.repository.EmployeeRepository;
import com.datasource.multiconnect.repository.StudentRepository;

@Configuration
public class DatasourceConfig {

	// Primary DataSource
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.primary") 
	public JndiPropertyHolder jndiPrimary() {
		return new JndiPropertyHolder();
	}
	
	@Bean(name = "datasourcePrimary", destroyMethod = "")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource datasourcePrimary() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		return dataSourceLookup.getDataSource(jndiPrimary().getJndiName());
	}
	
	@Bean(name = "sqlSessionFactoryPrimary")
	public SqlSessionFactory sessionFactoryPrimary(@Qualifier("datasourcePrimary") DataSource dataSource) {
		return getSessionFactory(dataSource);
	}
	
	@Bean(name = "sqlSessionTemplatePrimary")
	public SqlSessionTemplate sessionTemplatePrimary(@Qualifier("sqlSessionFactoryPrimary") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);		
	}
	
	// Secondary DataSource
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	public JndiPropertyHolder jndiSecondary() {
		return new JndiPropertyHolder();
	}
  
	@Bean(name = "datasourceSecondary",destroyMethod = "")
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	public DataSource datasourceSecondary() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		return dataSourceLookup.getDataSource(jndiSecondary().getJndiName());
	}
  
	@Bean(name = "sqlSessionFactorySecondary")
	public SqlSessionFactory sessionFactorySecondary(@Qualifier("datasourceSecondary") DataSource dataSource) {
		return getSessionFactory(dataSource);
	}
  
	@Bean(name = "sqlSessionTemplateSecondary")
	public SqlSessionTemplate sessionTemplateSecondary(@Qualifier("sqlSessionFactorySecondary") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	private SqlSessionFactory getSessionFactory(DataSource dataSource) {
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory;
		try {
			sqlSessionFactory=bean.getObject();
			sqlSessionFactory.getConfiguration().addMapper(EmployeeRepository.class);
			sqlSessionFactory.getConfiguration().addMapper(StudentRepository.class);
			return sqlSessionFactory;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static class JndiPropertyHolder {
		private String jndiName;

		public String getJndiName() {
			return jndiName;
		}

		public void setJndiName(String jndiName) {
			this.jndiName = jndiName;
		}		
	}
}
