package message;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
@ComponentScan("message")
@Configuration
@PropertySource("jdbc.properties")
@MapperScan("mapper")
public class SpringMvcConfigurer implements WebMvcConfigurer{
	//获取配置文件属性
	@Value("${mysql.driver}")
	private String driver;
	@Value("${mysql.url}")
	private String url;
	@Value("${mysql.username}")
	private String username;
	@Value("${mysql.password}")
	private String password;
	@Value("${mysql.maxActive}")
	private Integer maxActive;
	@Value("${mysql.initialSize}")
	private Integer initialSize;
	@Value("${mybatis.mapper-locations}")
	private Resource[] mapperLocations;
	//基于配置信息创建数据资源对象，并交给spring管理
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setUrl(url);
		dataSource.setDriverClassName(driver);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxTotal(maxActive);
		return dataSource;
	}
	//最后，在执行sql之前，还得使得MyBatis明确执行时使用哪个数据源可以连接数据库，
	//需要在Spring环境中配置一个`SqlSessionFactoryBean`的对象
	@Bean 
	public SqlSessionFactoryBean sqlBean(DataSource dataSource) {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(mapperLocations);
		return bean;
	}
//===============================================================================
	
	private String characterEncoding="utf-8";
	@Bean
	public ViewResolver configViewResolver() {
		//模板解析
		ClassLoaderTemplateResolver templateResolver = new 
				ClassLoaderTemplateResolver();
		templateResolver.setCharacterEncoding(characterEncoding);
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCacheable(false);
		templateResolver.setPrefix("/templates/");
		templateResolver.setSuffix(".html");
		//模板引擎
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		//视图解析
		ThymeleafViewResolver viewResolver = new 
				ThymeleafViewResolver();
		viewResolver.setCharacterEncoding(characterEncoding);
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}
}
