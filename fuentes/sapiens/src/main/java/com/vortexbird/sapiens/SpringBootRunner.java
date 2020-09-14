package com.vortexbird.sapiens;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@ServletComponentScan
@EnableSwagger2
public class SpringBootRunner implements WebMvcConfigurer{
	
//implements ServletContextInitializer, WebMvcConfigurer{
	
	private static Logger log = LoggerFactory.getLogger(SpringBootRunner.class);
	
    public static void main(String[] args) {
    	log.info("Iniciando aplicación...");
        SpringApplication.run(SpringBootRunner.class, args);
    }
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                                                      .apis(RequestHandlerSelectors.any())
                                                      .paths(PathSelectors.any())
                                                      .build();
    }

//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		
//		log.info("Iniciando aplicación onStartup...");
//		
//		// Register the CKFinder's servlet.
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("ckfinder", new CKFinderServlet());
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/ckfinder/*");
//        dispatcher.setInitParameter("scan-path", "example.ckfinder");
//
//        
//        FilterRegistration.Dynamic filter = servletContext.addFilter("x-content-options", new Filter() {
//            @Override
//            public void init(FilterConfig filterConfig) {
//            }
//
//            @Override
//            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//                ((HttpServletResponse) response).setHeader("X-Content-Type-Options", "nosniff");
//                chain.doFilter(request, response);
//            }
//
//            @Override
//            public void destroy() {
//            }
//        });
//
//        filter.addMappingForUrlPatterns(null, false, "/userfiles/*");
//
//        String tempDirectory;
//
//        try {
//            tempDirectory = Files.createTempDirectory("ckfinder").toString();
//        } catch (IOException e) {
//            tempDirectory = null;
//        }
//
//        dispatcher.setMultipartConfig(new MultipartConfigElement(tempDirectory));
//		
//	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configure the resource handler to serve files uploaded with CKFinder.
        String publicFilesDir = String.format("file:%s/userfiles/", System.getProperty("user.dir"));

        registry.addResourceHandler("/userfiles/**")
                .addResourceLocations(publicFilesDir);
    }
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.office365.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("sabertool@usbcali.edu.co");
	    //mailSender.setUsername("adm.usbcali.edu.co\\sabertool");
	    mailSender.setPassword("52390372");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
}
