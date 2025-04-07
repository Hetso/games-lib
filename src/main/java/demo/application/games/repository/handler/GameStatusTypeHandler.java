package demo.application.games.repository.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import demo.application.games.model.GameStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameStatusTypeHandler extends BaseTypeHandler<GameStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, GameStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.intValue());
    }

    @Override
    public GameStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return fromValue(rs.getInt(columnName));
    }

    @Override
    public GameStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return fromValue(rs.getInt(columnIndex));
    }

    @Override
    public GameStatus getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        return fromValue(cs.getInt(columnIndex));
    }

    private GameStatus fromValue(int value) {
        for (GameStatus status : GameStatus.values()) {
            if (status.intValue() == value) {
                return status;
            }
        }
        return null;
    }
}

