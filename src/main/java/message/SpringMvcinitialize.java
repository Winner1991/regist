package message;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * 入口类，初始化
 * */
public class SpringMvcinitialize extends AbstractAnnotationConfigDispatcherServletInitializer{
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// 获取spring的配置类
		return null;
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// 获取springMvc的配置类
		return new Class[] {SpringMvcConfigurer.class};
	}
	@Override
	protected String[] getServletMappings() {
		// 获取请求路径
		return new String[] {"*.do"};
	}
}
