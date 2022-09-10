package com.bp.example.base;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;

	private String codigoExcepcion;
	private String mensaje;
	private HttpStatus respuestaHttp;
	private List<String> listaMensajes = new ArrayList<>();

	public CustomException(String codigoExcepcion, String... mensajeArgunmentos) {
		this(getMensajeCommitSolu(codigoExcepcion, mensajeArgunmentos));
		this.codigoExcepcion = codigoExcepcion;
	}

	public CustomException(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
		if (mensaje != null && !mensaje.equals("")) {
			listaMensajes.add(mensaje);
		}
	}
	
	public CustomException(HttpStatus http, String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
		if (mensaje != null && !mensaje.equals("")) {
			listaMensajes.add(mensaje);
		}
		this.respuestaHttp = http;
	}

	public static CustomException agregarMensaje(CustomException excepcion, String codigoExcepcion,
			String... mensajeArgumento) {
		if (excepcion == null) {
			excepcion = new CustomException(codigoExcepcion, mensajeArgumento);
		} else {
			excepcion.getListaMensajes().add(getMensajeCommitSolu(codigoExcepcion, mensajeArgumento));
		}
		return excepcion;
	}

	private static String getMensajeCommitSolu(String codigoExcepcion, String... mensajeArgunmentos) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		// ctx.register(AppConfig.class);
		ctx.refresh();
		MessageSource resources = ctx.getBean(MessageSource.class);
		String mensaje = resources.getMessage(codigoExcepcion, null, "Default", new Locale("es"));
		if (mensajeArgunmentos != null) {
			MessageFormat formater = new MessageFormat("");
			formater.applyPattern(mensaje);
			mensaje = formater.format(mensajeArgunmentos);
		}
		return mensaje;
	}

	public String getCodigoExcepcion() {
		return codigoExcepcion;
	}

	public void setCodigoExcepcion(String codigoExcepcion) {
		this.codigoExcepcion = codigoExcepcion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<String> getListaMensajes() {
		return listaMensajes;
	}

	public void setListaMensajes(List<String> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}

	public HttpStatus getRespuestaHttp() {
		return respuestaHttp;
	}

	public void setRespuestaHttp(HttpStatus respuestaHttp) {
		this.respuestaHttp = respuestaHttp;
	}
	
	
}
