package br.com.IngressoFacilAPI.services.envio_de_emails;

public enum EmailsTemplates {
	CONTA_CRIADA_COM_SUCESSO {
		@Override
		public String getSubject() {
			return "Cadastro no site da Ingresso Facil foi um sucesso!";
		}
		
		@Override
		public String getText() {
			return "Olá, email para sinalizar que sua conta foi criada com sucesso!"
					+ "\n https://github.com/RonyLiboni/Ingresso-Facil-API.git";
		}
	},
	COMPRA_REALIZADA_COM_SUCESSO {
		@Override
		public String getSubject() {
			return "A compra dos seus ingressos foi realizada com sucesso!";
		}
		
		@Override
		public String getText() {
			return "Olá, email para sinalizar que sua compra dos seus ingressos foi realizada com sucesso!" +
					"\n https://github.com/RonyLiboni/Ingresso-Facil-API.git";
		}
	};

	public abstract String getText();

	public abstract String getSubject();
}
