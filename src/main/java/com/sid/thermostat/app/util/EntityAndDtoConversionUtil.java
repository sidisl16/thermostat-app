package com.sid.thermostat.app.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class EntityAndDtoConversionUtil {

	public static <V, R> R convert(V v, Class<R> cl) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(v, cl);
	}

}
