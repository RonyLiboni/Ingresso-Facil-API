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
				"Esta aplicação utiliza token JWT para segurança.\n Clique no botão com cadeado <b>'Authorize'</b> quando for utilizar um endpoint que precisa de autenticação. "
				+ "\n A autenticação pode ser feita no autenticacao-controller, nele você receberá um token. "
						+ "Você deve escrever a palavra 'Bearer ' e copiar o token que recebeu. Cole tudo no campo 'value' e clique em 'Authorize'"
						+ "\n Exemplo: <b>Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJbmdyZXNzbyB </b>\n"
						+ "#### Você pode utilizar os JSON pré-configurados no swagger.\r\n"
						+ "\r\n"
						+ "## Tecnologias utilizadas\r\n"
						+ "  - Java com Spring Boot\r\n"
						+ "  - Spring Security com token JWT\r\n"
						+ "  - Banco de dados MySQL\r\n"
						+ "  - FlyWay para versionamento do banco de dados (cria e popula o banco de dados ao iniciar o sistema)\r\n"
						+ "  - Documentação com SWAGGER\r\n"
						+ "  - Java Mail Sender para envio de email\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "## Funcionalidades que não precisam de autenticação\r\n"
						+ "•	Visualizar eventos disponíveis\r\n"
						+ "\r\n"
						+ "•	Fazer cadastro de cliente \r\n"
						+ "\r\n"
						+ "•	Cliente recebe email sinalizando que seu cadastro deu certo\r\n"
						+ "\r\n"
						+ "•	Autenticar-se na plataforma\r\n"
						+ "## Funcionalidades administrador\r\n"
						+ "•	Cadastro local de evento (CRUD)\r\n"
						+ "\r\n"
						+ "•	Cadastro de evento (CRUD)\r\n"
						+ "## Funcionalidades cliente\r\n"
						+ "•	Colocar eventos no carrinho de compras (para retirar um item, basta você zerar a quantidade dele)\r\n"
						+ "\r\n"
						+ "•	Visualizar carrinho de compras\r\n"
						+ "\r\n"
						+ "•	Comprar todos itens dentro do carrinho de compras\r\n"
						+ "\r\n"
						+ "•	Recebe email após compra ser concluida\r\n"
						+ "\r\n"
						+ "•	Visualizar histórico de compras"
				)
				.version("1.0.0").build();
	}

}
