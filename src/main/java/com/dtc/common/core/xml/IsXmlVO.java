package com.dtc.common.core.xml;

import java.io.InputStream;

/**
 * 基礎的 interface，主要是為了 {@link Converter#unmarshal(Class, InputStream)} 完畢
 * 可以觸發 {@link #afterUnmarshal()}。
 * 如果不想實作 {@link #afterUnmarshal()}，可考慮直接繼承 {@link XmlVO}。
 * 
 * @author MontyPan
 */
public interface IsXmlVO {
	void afterUnmarshal();
}