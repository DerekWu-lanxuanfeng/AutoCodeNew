<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
<mapper namespace="${classNameVO}">

	<sql id="${classNameVO}Columns"> <#list columnInfoList as columnInfo>${columnInfo.columnName} AS ${columnInfo.name}<#if conlumnInfoCount!=columnInfo_index+1>, </#if></#list> </sql>

	<insert id="insert${classNameVO}" useGeneratedKeys="true" keyProperty="${pkName}" parameterType="${classNameVO}">
		insert into ${tableName}(<#list columnInfoList as columnInfo><#if columnInfo_index!=0>${columnInfo.columnName}<#if conlumnInfoCount!=columnInfo_index+1>, </#if></#if></#list>) values(<#list columnInfoList as columnInfo><#if columnInfo_index!=0>#${"{"}${columnInfo.name}${"}"}<#if conlumnInfoCount!=columnInfo_index+1>, </#if></#if></#list>)
	</insert>

	<update id="update${classNameVO}" parameterType="${classNameVO}">
		update ${tableName} set <#list columnInfoList as columnInfo><#if columnInfo_index!=0>${columnInfo.columnName}=#${"{"}${columnInfo.name}${"}"}<#if conlumnInfoCount!=columnInfo_index+1>, </#if></#if></#list> where ${pkColumnName}=#${"{"}${pkName}${"}"}
	</update>
	
	<delete id="delete${classNameVO}ById" parameterType="${classNameVO}">
		delete from ${tableName} where ${pkColumnName}=#${"{"}${pkName}${"}"}
	</delete> 
	
	<select id="selectOne${classNameVO}ById" parameterType="Map" resultType="${classNameVO}">
		select <include refid="${classNameVO}Columns"/> from ${tableName} where ${pkColumnName}=#${"{"}id${"}"}
	</select>
	
	<select id="select${classNameVO}ListByParams" resultType="${classNameVO}" parameterType="Map">
		select <include refid="${classNameVO}Columns"/> from ${tableName}
		<where> 
		    <if test="${pkName} != null">
		         ${pkColumnName} = #${"{"}${pkName}${"}"}
		    </if> 
		</where>
	</select>

</mapper>

<typeAlias alias="${classNameVO}" type="${package}.module.${moduleName}.vo.${classNameVO}" />

<mapper resource="${packageFile}/module/${moduleName}/dao/sqlmap/${classNameVO}SqlMap.xml" />