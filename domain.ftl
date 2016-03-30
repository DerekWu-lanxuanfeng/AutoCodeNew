package ${package}.module.${moduleName}.vo;

import java.util.Date;

import com.flame.game.core.data.BaseVO;

/** 
 * @Company: 深圳市烈焰时代科技有限公司
 * @File: ${package}.module.${moduleName}.vo.${classNameVO}.java
 * @Description: ${classNameVO} 
 * @Create: AutoCode ${date} 
 * @version: V1.0 
 */
public class ${classNameVO} extends BaseVO {

<#list columnInfoList as columnInfo>
	/** ${columnInfo.comment} */
	private ${columnInfo.dataType} ${columnInfo.name};
	
</#list>
	public ${classNameVO}(short dbNum, byte tableNum){
		setDbNum(dbNum);
		setTableNum(tableNum);
	}
	
	public long getId() { 
	    return this.${pkName};
	}
	

<#list columnInfoList as columnInfo>
	public ${columnInfo.dataType} get${columnInfo.getterSetterName}() {
		return ${columnInfo.name};
	}

	public void set${columnInfo.getterSetterName}(${columnInfo.dataType} ${columnInfo.name}) {
		this.${columnInfo.name} = ${columnInfo.name};
	}
</#list>

	@Override
	public String toString() {  
		StringBuffer sb = new StringBuffer("${classNameVO} [");
		<#list columnInfoList as columnInfo>
		sb.append(" ${columnInfo.name}=");
		sb.append(this.${columnInfo.name});
		</#list>
		sb.append("]");
	    return sb.toString();
	}  

}

<#list columnInfoList as columnInfo>
aaaa.set${columnInfo.getterSetterName}(bbbb.get${columnInfo.getterSetterName}()); //${columnInfo.comment}
</#list>

message ${className} {
<#list columnInfoList as columnInfo>
	optional ${columnInfo.protoDataType} ${columnInfo.name} = ${columnInfo_index+1}; //${columnInfo.comment}
</#list>
}