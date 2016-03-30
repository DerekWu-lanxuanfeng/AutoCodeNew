package ${package}.module.${moduleName}.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.flame.game.core.data.interfaces.ISelectDAO;
import com.flame.game.core.data.interfaces.ISimpleDAO;
import ${package}.module.${moduleName}.dao.I${className}DAO;
import ${package}.module.${moduleName}.vo.${classNameVO};

/** 
 * @Company: 深圳市烈焰时代科技有限公司
 * @File: ${package}.module.${moduleName}.dao.${className}DAO.java
 * @Description: ${className}DAO 实现
 * @Create: AutoCode ${date} 
 * @version: V1.0 
 */
@Repository
public class ${className}DAO implements I${className}DAO{

	@Resource
	private ISimpleDAO simpleDAO; 
	
	@Resource
	private ISelectDAO selectDAO;
	
	public int insert${className}(${classNameVO} ${varClassName}) {
		return simpleDAO.insert(${varClassName});
	}

	public int update${className}(${classNameVO} ${varClassName}) {
		return simpleDAO.update(${varClassName});
	}

	public int delete${className}(${classNameVO} ${varClassName}) {
		return simpleDAO.delete(${varClassName});
	}
	
	public ${classNameVO} selectOne${className}ById(short dbNum, byte tableNum, ${pkType} ${pkName}) {
		return selectDAO.selectOne(dbNum, tableNum, ${pkName}, ${classNameVO}.class);
	}

}