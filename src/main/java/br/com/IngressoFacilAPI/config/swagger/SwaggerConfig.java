package br.com.IngressoFacilAPI.config.swagger;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.IngressoFacilAPI.entities.usuario.Usuario;
import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.IngressoFacilAPI")).paths(PathSelectors.any()).build()
				.useDefaultResponseMessages(false)
				.ignoredParameterTypes(Usuario.class)
				.globalResponseMessage(RequestMethod.GET, GlobalResponses.responseMessageForGET())
				.globalResponseMessage(RequestMethod.DELETE, GlobalResponses.responseMessageForDELETE())
				.globalResponseMessage(RequestMethod.POST, GlobalResponses.responseMessageForPOST())
				.globalResponseMessage(RequestMethod.PUT, GlobalResponses.responseMessageForPUT())
				.securitySchemes(Arrays.asList(new ApiKey("JWT", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
				.securityContexts(Arrays.asList(securityContext())).apiInfo(apiInfo());
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("ADMIN", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Ingresso Fácil REST API").description(
				"Esta aplicação utiliza token JWT para segurança, autentique-se no Autenticação controller antes de testá-la.\n Clique no botão com cadeado <b>'Authorize'</b>. "
						+ "Você deve escrever a palavra 'Bearer ' (sem as aspas e com um espaço) e copiar o token que receber na autenticacao e depois clicar em 'Authorize' novamente. "
						+ "\n Exemplo: <b>Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJbmdyZXNzbyB </b>"
						+ "\n Aplicação utiliza <b>Banco de dados relacional MySQL</b> e <b>FlyWay</b> para o versionamento do banco."
						+ " Foi criada com <b>Spring Boot</b> e usei o <b>Lombok</b>. "
						+ "\n Os exemplos de JSON que estão na documentação já estão prontos para teste. "
						+ "\n Todos atributos descritos no JSON fornecido são obrigatórios. "
						+ "\n Todos códigos Http retornados tem descrição do que significam quando retornados nesta API.")
				.version("1.0.0").build();
	}

}
