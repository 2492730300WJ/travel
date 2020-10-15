package travel.api.util;

import travel.api.util.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class DbManagerUtil {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 是否启用 SQL 日志打印
	 */
	private static ThreadLocal<Boolean> logEnable = ThreadLocal.withInitial(() -> true);

	/**
	 * 启用当前线程的日志打印
	 * <pre>
	 * 执行指定SQL而不需要打印日志时:
	 * try {
	 *	dbManagerUtil.disableLog().xxx();
	 *  dbManagerUtil.xxx();
	 *  ...
	 * } finally {
	 * // 如果当前线程后续调用的方法都不需要打印日志, 可以跳过该步骤
	 *	dbManagerUtil.enableLog();
	 * }
	 * </pre>
	 */
	public DbManagerUtil enableLog(){
		logEnable.set(true);
		return this;
	}

	/**
	 * 关闭当前线程的日志打印
	 * <pre>
	 * 执行指定SQL而不需要打印日志时:
	 * try {
	 *	dbManagerUtil.disableLog().xxx();
	 * } finally {
	 * // 如果当前线程后续调用的方法都不需要打印日志, 可以跳过该步骤
	 *	dbManagerUtil.enableLog();
	 * }
	 * </pre>
	 */
	public DbManagerUtil disableLog(){
		logEnable.set(false);
		return this;
	}

	/**
	 * 获取单行的第一个字段值
	 * @param sql 查询SQL
	 * @param args 查询参数
	 */
	public String queryScalar(String sql, Object... args) {
		logSqlScript(sql, args);

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
		if(list != null && list.size() > 0){
			for(Map<String, Object> map : list){
				int size = map.keySet().size();
				if(map.keySet().size() > 1) {
					throw new RuntimeException(String.format("字段个数应该限定为 1 个, 而不是 %d 个.", size));
				}

				for(String key : map.keySet()){
					return String.valueOf(map.get(key));
				}
			}
		}
		return null;
	}

	/**
	 * 获取SQL查询结果的Map<String, Object>对象
	 * @param sql 查询SQL
	 * @param args 查询参数
	 */
	public Map<String, Object> getMap(String sql, Object... args) {
		logSqlScript(sql, args);

		try {
			return jdbcTemplate.queryForMap(sql, args);
		} catch (EmptyResultDataAccessException e) {
//			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取SQL查询结果集的List<Map<String, Object>>对象
	 * @param sql 查询SQL
	 * @param args 查询参数
	 */
	public List<Map<String, Object>> getListMap(String sql, Object... args) {
		logSqlScript(sql, args);

		return jdbcTemplate.queryForList(sql, args);
	}

	/**
	 * 获取SQL查询结果映射到指定Class的对象
	 * @param sql 查询SQL
	 * @param clazz 指定Bean的class
	 * @param args 查询参数
	 */
	public <T> T getBean(String sql, Class<T> clazz, Object... args) {
		logSqlScript(sql, args);

		try {
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(clazz), args);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取SQL查询结果集映射到指定Class的List对象
	 * @param sql 查询SQL
	 * @param clazz 指定Bean的class
	 * @param args 查询参数
	 */
	public <T> List<T> getBeanList(String sql, Class<T> clazz, Object... args) {
		logSqlScript(sql, args);

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(clazz), args);
	}

	/**
	 * 获取 SQL 查询结果集映射到指定Class的分页对象数据
	 * 数据为空时,会返回 一个空的 Page 对象(不为null) 里面 list 为 null
	 * @param sql 查询SQL
	 * @param curPage 当前页码
	 * @param clazz 指定Bean的class
	 * @param args 查询参数
	 */
	public <T> Page<T> getPage(String sql, int curPage, Class<T> clazz, Object... args) {
		return getPage(sql, curPage, Page.DEFAULT_PAGE_SIZE, clazz, args);
	}

	/**
	 * 获取 SQL 查询结果集映射到指定Class的分页对象数据
	 * 数据为空时,会返回 一个空的 Page 对象(不为null) 里面 list 为 null
	 * @param sql 查询SQL
	 * @param curPage 当前页码
	 * @param pageSize 页面大小
	 * @param clazz 指定Bean的class
	 * @param args 查询参数
	 */
	public <T> Page<T> getPage(String sql, int curPage, short pageSize, Class<T> clazz, Object... args) {
		String countSql = String.format("select count(1) from (%s) a", sql);

		logSqlScript(countSql, args);

		Integer totalCount = jdbcTemplate.queryForObject(countSql, Integer.class, args);
		if(totalCount == null || totalCount == 0){
			return new Page<>(pageSize, curPage, 0);
		}else{
			Page<T> page = new Page<>(pageSize, curPage, totalCount);

			String pageSql = sql + String.format(" limit %d, %d", page.getStartIndex(), page.getPageSize());

			logSqlScript(pageSql, args);

			List<T> list = jdbcTemplate.query(pageSql, new BeanPropertyRowMapper<>(clazz), args);
			return new Page<>(list, pageSize, curPage, totalCount);
		}
	}

	/**
	 * 用于执行 DDL 语句，如：建表
	 * @param sql DDL 语句 SQL
	 */
	public void execute(String sql){
		logSqlScript(sql);

		jdbcTemplate.execute(sql);
	}

	/**
	 * 用于执行 DML 语句, 比如常见的 增删改操作
	 * @param sql DML 语句 SQL
	 * @param args 传入参数
	 * @return 执行 SQL 的受影响行数
	 */
	public int update(String sql, Object... args){
		logSqlScript(sql, args);

		return jdbcTemplate.update(sql, args);
	}

	/**
	 * 用于批量执行DML语句, 比如常见的 增删改操作
	 * @param sql DML语句SQL
	 * @param args 传入参数
	 * @return 每条 SQL 的受影响行数
	 */
	public int[] batchUpdate(String sql, List<Object[]> args){
		logSqlScript(sql, args);

		return jdbcTemplate.batchUpdate(sql, args);
	}

	/**
	 * 打印 SQL 日志
	 */
	private void logSqlScript(String sql, Object... args){
		if(logEnable.get()){
			log.info(String.format("DbManager - Sql Script : %s, Parameters : [ %s ]", sql, StringUtils.join(args, ", ")));
		}
	}

}
