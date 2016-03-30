package ${package}.module.${moduleName}.dao;

import ${package}.module.${moduleName}.vo.${classNameVO};

/** 
 * @Company: 深圳市烈焰时代科技有限公司
 * @File: ${package}.module.${moduleName}.dao.I${className}DAO.java
 * @Description: I${className}DAO 接口
 * @Create: AutoCode ${date} 
 * @version: V1.0 
 */
public interface I${className}DAO {

	/**
	 * 新增${className}
	 * @param ${varClassName}
	 * @return 数据影响条数
	 */
	public int insert${className}(${classNameVO} ${varClassName});
	
	/**
	 * 修改${className}
	 * @param ${varClassName}
	 * @return 数据影响条数
	 */
	public int update${className}(${classNameVO} ${varClassName});

	/**
	 * 删除${className}
	 * @param ${varClassName}
	 * @return 数据影响条数
	 */
	public int delete${className}(${classNameVO} ${varClassName});
	
	/**
	 * 通过主键ID查询${className}
	 * @param ${pkName}
	 * @return ${className}
	 */
	public ${classNameVO} selectOne${className}ById(short dbNum, byte tableNum, ${pkType} ${pkName});
	

}