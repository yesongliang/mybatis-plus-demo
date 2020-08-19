package com.kedacom.tz.sh.typeHandlers;

import java.nio.charset.Charset;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.Base64Utils;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({ String.class })
public class PasswordTypeHandler extends BaseTypeHandler<String> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		String str = Base64Utils.encodeToString(parameter.getBytes(Charset.defaultCharset()));
		ps.setString(i, str);
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return decroty(rs.getString(columnName));
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return decroty(rs.getString(columnIndex));
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return decroty(cs.getString(columnIndex));
	}

	private String decroty(String str) {
		byte[] decodeFromString = Base64Utils.decodeFromString(str);
		return new String(decodeFromString);
	}

}
