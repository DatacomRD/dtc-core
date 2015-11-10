package com.dtc.common.core.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.google.common.base.Preconditions;

public class Converter {
	private static final String NULL_CLASS_MSG = "Class 不能為 null";
	private static final String NULL_VO_MSG = "value object 不能為 null";

	private Converter() {}	//util class 遮掉 constructor
	
	/**
	 * 注意：這個 method 會強制以 UTF-8 處理 xml、而非 {@link Charset#defaultCharset()}，
	 * 若要指定 charset，請使用 {@link #unmarshal(Class, String, Charset)}。 
	 */
	public static <T extends IsXmlVO> T unmarshal(Class<T> clazz, String xml) throws JAXBException {
		return unmarshal(clazz, xml, Charset.forName("UTF-8"));
	}
	
	public static <T extends IsXmlVO> T unmarshal(Class<T> clazz, String xml, Charset charset) throws JAXBException {
		Preconditions.checkNotNull(xml, "xml 字串不能為 null");
		
		return unmarshal(clazz, new ByteArrayInputStream(xml.getBytes(charset)));
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IsXmlVO> T unmarshal(Class<T> clazz, InputStream is) throws JAXBException {
		Preconditions.checkNotNull(clazz, NULL_CLASS_MSG);
		Preconditions.checkNotNull(is);

		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		T result = (T)unmarshaller.unmarshal(is);
		result.afterUnmarshal();
		return result;
	}
	
	/**
	 * @param clazz  VO 的 class
	 * @param vo  VO 的 instance
	 * @param os  marshal 的結果會送進這個 {@link OutputStream}
	 */
	public static <T extends IsXmlVO> void marshal(Class<T> clazz, T vo, OutputStream os) throws JAXBException {
		Preconditions.checkNotNull(clazz, NULL_CLASS_MSG);
		Preconditions.checkNotNull(vo, NULL_VO_MSG);
		Preconditions.checkNotNull(os, "OutputStream 不能為 null");
		
		JAXBContext context = JAXBContext.newInstance(clazz);
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(vo, os);
	}
	
	/**
	 * @param clazz  VO 的 class
	 * @param vo  VO 的 instance
	 * @return XML 字串
	 */
	public static <T extends IsXmlVO> String marshal(Class<T> clazz, T vo) throws JAXBException {
		Preconditions.checkNotNull(clazz, NULL_CLASS_MSG);
		Preconditions.checkNotNull(vo, NULL_VO_MSG);
		
		JAXBContext context = JAXBContext.newInstance(clazz);
		Marshaller marshaller = context.createMarshaller();
		StringWriter result = new StringWriter();
		marshaller.marshal(vo, result);
		return result.toString();
	}
}