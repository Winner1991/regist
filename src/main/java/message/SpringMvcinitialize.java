package message;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * ����࣬��ʼ��
 * */
public class SpringMvcinitialize extends AbstractAnnotationConfigDispatcherServletInitializer{
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// ��ȡspring��������
		return null;
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// ��ȡspringMvc��������
		return new Class[] {SpringMvcConfigurer.class};
	}
	@Override
	protected String[] getServletMappings() {
		// ��ȡ����·��
		return new String[] {"*.do"};
	}
}
