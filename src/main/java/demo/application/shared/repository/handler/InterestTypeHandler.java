package demo.application.shared.repository.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import demo.application.shared.model.Interest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterestTypeHandler extends BaseTypeHandler<Interest> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Interest parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.intValue());
    }

    @Override
    public Interest getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return fromValue(rs.getInt(columnName));
    }

    @Override
    public Interest getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return fromValue(rs.getInt(columnIndex));
    }

    @Override
    public Interest getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        return fromValue(cs.getInt(columnIndex));
    }

    private Interest fromValue(int value) {
        for (Interest interest : Interest.values()) {
            if (interest.intValue() == value) {
                return interest;
            }
        }
        return null; // Ou lançar uma exceção se o valor for inválido
    }
}

