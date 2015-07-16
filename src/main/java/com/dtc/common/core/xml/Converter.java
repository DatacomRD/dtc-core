package com.dtc.common.core.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Converter {
	private Converter() {}	//util class 遮掉 constructor
	
	public static <T extends IsXmlVO> T unmarshal(Class<T> clazz, String xml) throws JAXBException {
		return unmarshal(clazz, new ByteArrayInputStream(xml.getBytes()));
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IsXmlVO> T unmarshal(Class<T> clazz, InputStream is) throws JAXBException {
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
		JAXBContext context = JAXBContext.newInstance(clazz);
		Marshaller marshaller = context.createMarshaller();
		StringWriter result = new StringWriter();
		marshaller.marshal(vo, result);
		return result.toString();
	}
}