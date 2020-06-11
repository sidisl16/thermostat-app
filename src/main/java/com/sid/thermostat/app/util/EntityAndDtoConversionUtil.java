package com.sid.thermostat.app.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;

import com.google.common.base.Strings;

public class EntityAndDtoConversionUtil {

	public static <V, R> R convert(V v, Class<R> cl) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(v, cl);
	}

	public static <S, T> void copyIgnoreNull(S source, T target) {
		BeanUtils.copyProperties(source, target, getNullProperties(source));
	}

	public static <S, T> void copy(S source, T target) {
		BeanUtils.copyProperties(source, target);
	}

	private static <S> String[] getNullProperties(S source) {

		Field[] fields = source.getClass().getDeclaredFields();
		Set<String> nullAttributes = new HashSet<>();

		Arrays.stream(fields).filter(field -> {
			try {
				field.setAccessible(true);
				return ((field.get(source) == null)
						|| (field.getType() == String.class && Strings.isNullOrEmpty((String) field.get(source))));
			} catch (IllegalArgumentException | IllegalAccessException e1) {
			}
			return false;
		}).forEach(field -> nullAttributes.add(field.getName()));

		return nullAttributes.toArray(new String[nullAttributes.size()]);
	}
}