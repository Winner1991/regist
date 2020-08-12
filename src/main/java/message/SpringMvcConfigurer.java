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
	//��ȡ�����ļ�����
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
	//����������Ϣ����������Դ���󣬲�����spring����
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
	//�����ִ��sql֮ǰ������ʹ��MyBatis��ȷִ��ʱʹ���ĸ�����Դ�����������ݿ⣬
	//��Ҫ��Spring����������һ��`SqlSessionFactoryBean`�Ķ���
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
		//ģ�����
		ClassLoaderTemplateResolver templateResolver = new 
				ClassLoaderTemplateResolver();
		templateResolver.setCharacterEncoding(characterEncoding);
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCacheable(false);
		templateResolver.setPrefix("/templates/");
		templateResolver.setSuffix(".html");
		//ģ������
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		//��ͼ����
		ThymeleafViewResolver viewResolver = new 
				ThymeleafViewResolver();
		viewResolver.setCharacterEncoding(characterEncoding);
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}
}
