package br.com.IngressoFacilAPI.config.swagger;

import java.util.ArrayList;
import java.util.List;

import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;

public final class GlobalResponses {
	
	@SuppressWarnings("serial")
	public static List<ResponseMessage> responseMessageForGET() {
	    return new ArrayList<ResponseMessage>() {
		{
	        add(httpCode200_Ok());
	        add(httpCode404_NotFound());
	        add(httpCode403_Forbidden());
	    }};
	}
	@SuppressWarnings("serial")
	public static List<ResponseMessage> responseMessageForPOST() {
	    return new ArrayList<ResponseMessage>() {{
	        add(httpCode201_Created());
	        add(httpCode400_BadRequest());
	        add(httpCode404_NotFound());
	        add(httpCode403_Forbidden());
	    }};
	}
	@SuppressWarnings("serial")
	public static List<ResponseMessage> responseMessageForPUT() {
	    return new ArrayList<ResponseMessage>() {{
	        add(httpCode200_Ok());
	        add(httpCode400_BadRequest());
	        add(httpCode403_Forbidden());
	        add(httpCode404_NotFound());
	    }};
	}
	
	@SuppressWarnings("serial")
	public static List<ResponseMessage> responseMessageForDELETE() {
	    return new ArrayList<ResponseMessage>() {{
	        add(httpCode204_NoContent());
	        add(httpCode404_NotFound());
	        add(httpCode403_Forbidden());
	        add(httpCode409_Conflict());
	    }};
	}
	
	private static ResponseMessage httpCode200_Ok() {
		return new ResponseMessageBuilder()
	            .code(200)
	            .message("Solicitação bem sucedida!")
	            .build();
	}
	
	private static ResponseMessage httpCode201_Created() {
		return new ResponseMessageBuilder()
	            .code(201)
	            .message("O recurso foi criado com sucesso!")
	            .build();
	}
	
	private static ResponseMessage httpCode204_NoContent() {
		return new ResponseMessageBuilder()
	            .code(204)
	            .message("O recurso foi deletado com sucesso!")
	            .build();
	}
	
	private static ResponseMessage httpCode400_BadRequest() {
		return new ResponseMessageBuilder()
	            .code(400)
	            .message("Não foram enviadas todas informações necessárias para concluir sua solicitação!")
	            .build();
	}
	
	private static ResponseMessage httpCode403_Forbidden() {
		return new ResponseMessageBuilder()
	            .code(403)
	            .message("Você não tem permissão para utilizar este endpoint!")
	            .build();
	}
	
	private static ResponseMessage httpCode404_NotFound() {
		return new ResponseMessageBuilder()
	            .code(404)
	            .message("O recurso não foi encontrado!")
	            .build();
	}
	
	
	private static ResponseMessage httpCode409_Conflict() {
		return new ResponseMessageBuilder()
	            .code(409)
	            .message("Você deve deletar os recursos que dependem deste, antes de exclui-lo.")
	            .build();
	}
}
