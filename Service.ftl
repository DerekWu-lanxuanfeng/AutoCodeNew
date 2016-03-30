package ${package}.module.${moduleName}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ${package}.module.${moduleName}.dao.I${className}DAO;
import ${package}.module.${moduleName}.service.I${className}Service;
import ${package}.module.${moduleName}.vo.${classNameVO};

/** 
 * @Company: 深圳市烈焰时代科技有限公司
 * @File: ${package}.module.${moduleName}.service.impl.${className}Service.java
 * @Description: ${className}Service 实现
 * @Create: AutoCode ${date} 
 * @version: V1.0 
 */
@Repository
public class ${className}Service extends BaseService implements I${className}Service{

	@Autowired
	private I${className}DAO ${daoName}DAO;
	
	public int insert${className}(${classNameVO} ${varClassName}) {
		return ${daoName}DAO.insert${className}(${varClassName});
	}

	public int update${className}(${classNameVO} ${varClassName}) {
		return ${daoName}DAO.update${className}(${varClassName});
	}

	public int delete${className}(${classNameVO} ${varClassName}) {
		return ${daoName}DAO.delete${className}(${varClassName});
	}
	
	public ${classNameVO} get${className}ById(String ${pkName}) {
		return (${classNameVO})${daoName}DAO.get${className}ById(${pkName});
	}

	public ${classNameVO} selectOne${className}ById(short dbNum, byte tableNum, ${pkType} ${pkName}) {
		return ${daoName}DAO.selectOne${className}ById(dbNum, tableNum, ${pkName});
	}

}