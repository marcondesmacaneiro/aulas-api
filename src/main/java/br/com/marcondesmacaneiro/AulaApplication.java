package br.com.marcondesmacaneiro;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class AulaApplication implements ApplicationContextAware {

	private static ApplicationContext ctx;

	public static <T> T getBean(Class<T> tClass) {
		return ctx.getBean(tClass);
	}

        public static <T> T getBean(String name, Class<T> tClass) {
		return ctx.getBean(name, tClass);
	}

	public static void main(String[] args) {
		ctx = SpringApplication.run(AulaApplication.class, args);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}
}
//@SpringBootApplication(scanBasePackages = "br.com.marcondesmacaneiro")
//public class AulaApplication extends SpringBootServletInitializer {
//
//    private static ApplicationContext ctx;
//
//    public static <T> T getBean(Class<T> tClass) {
//        return ctx.getBean(tClass);
//    }
//
//    public static <T> T getBean(String name, Class<T> tClass) {
//        return ctx.getBean(name, tClass);
//    }
//
//    public static void main(String[] args) {
//        Locale.setDefault(new Locale("pt", "BR"));
//        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
//        SpringApplication.run(Application.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        Locale.setDefault(new Locale("pt", "BR"));
//        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
//        return application.sources(Application.class);
//    }
//}
