package xyz.todooc4.blogbackend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectMapperUtils {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T readValue(HttpServletRequest request, Class<T> clazz) {
		try {
			return clazz.cast(objectMapper.readValue(request.getInputStream(), clazz));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
