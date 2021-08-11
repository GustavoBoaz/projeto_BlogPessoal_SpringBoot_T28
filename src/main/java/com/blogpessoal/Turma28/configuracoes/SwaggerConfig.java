package com.blogpessoal.Turma28.configuracoes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Classe que responsavel pela configuração do Swagger API
 * 
 * @author Turma 28
 *
 */
@Configuration
public class SwaggerConfig {

	/**
	 * Método estatico reponsavel por passar Lista com ResponseBuilder informando
	 * status possiveis e seus significados para todos os endpoints
	 * 
	 * Incluir codigo abaixo no builder do Docket caso necessario utilizar.
	 * .globalResponses(HttpMethod.GET, responseMessage())
	 * .globalResponses(HttpMethod.POST, responseMessage())
	 * .globalResponses(HttpMethod.PUT, responseMessage())
	 * .globalResponses(HttpMethod.DELETE, responseMessage());
	 * 
	 * @return Lista com Response
	 */
	@SuppressWarnings("unused")
	private static List<Response> responseMessage() {
		return new ArrayList<Response>() {
			private static final long serialVersionUID = 1L;
			{
				add(new ResponseBuilder().code("200").description("Sucesso!").build());
				add(new ResponseBuilder().code("201").description("Criado!").build());
				add(new ResponseBuilder().code("400").description("Erro na requisição!").build());
				add(new ResponseBuilder().code("401").description("Não Autorizado!").build());
				add(new ResponseBuilder().code("403").description("Proibido!").build());
				add(new ResponseBuilder().code("404").description("Rota Não Encontrada!").build());
				add(new ResponseBuilder().code("500").description("Erro no Servidor!").build());
			}
		};
	}

	/**
	 * Método estático responsavel por passar dados de contato
	 * 
	 * @return Contact com dados
	 */
	private static Contact contact() {
		return new Contact("Gustavo Boaz", "https://github.com/GustavoBoaz/project_BlogPessoalTurma28",
				"gustavoboaz@gmail.com");
	}

	/**
	 * Método responsavel por passar dados do projeto e receber dados do metodo
	 * contact()
	 * 
	 * @return ApiInfo com dados do projeto
	 */
	public static ApiInfo metadata() {
		return new ApiInfoBuilder().title("API - Blog Pessoal").description("Projeto API Spring - Blog Pessoal")
				.version("1.0.0").license("Apache License Version 2.0")
				.licenseUrl("https://github.com/GustavoBoaz/project_BlogPessoalTurma28").contact(contact()).build();
	}

	/**
	 * Método responsavel por receber dados do metodo metadata e fornecer o package
	 * para os controladores
	 * 
	 * @return Docket com api documentada
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.blogpessoal.Turma28.controladores"))
				.paths(PathSelectors.any()).build().apiInfo(metadata()).useDefaultResponseMessages(false);
	}

}
