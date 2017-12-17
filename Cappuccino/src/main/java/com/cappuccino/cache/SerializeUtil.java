package com.cappuccino.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

public class SerializeUtil {

	private static final Logger logger = Logger.getLogger(SerializeUtil.class);

	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream obj_put = null;
		ByteArrayOutputStream byte_put = null;
		try {
			byte_put = new ByteArrayOutputStream();
			obj_put = new ObjectOutputStream(byte_put);
			obj_put.writeObject(object);
			byte[] bytes = byte_put.toByteArray();
			return bytes;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		ByteArrayInputStream byte_input = null;
		try {

			byte_input = new ByteArrayInputStream(bytes);
			ObjectInputStream obj_input = new ObjectInputStream(byte_input);
			return obj_input.readObject();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
