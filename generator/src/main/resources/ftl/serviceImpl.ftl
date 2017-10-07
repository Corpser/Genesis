package ${package }.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${mapperPackage }.${tableName?cap_first}Mapper;
import ${modelPackage }.${tableName?cap_first};
import ${modelPackage }.${tableName?cap_first}Criteria;
import ${modelPackage }.${tableName?cap_first}Criteria.Criteria;
import ${package }.${tableName?cap_first}Service;
import com.github.pagehelper.PageHelper;

@Service
public class ${tableName?cap_first}ServiceImpl implements ${tableName?cap_first}Service {

	@Autowired
	private ${tableName?cap_first}Mapper ${tableName }Mapper;

	@Override
	public List<${tableName?cap_first}> select(${tableName?cap_first} ${tableName }) {
		${tableName?cap_first}Criteria ${tableName }Criteria = new ${tableName?cap_first}Criteria();
		Criteria criteria = ${tableName }Criteria.createCriteria();
		<#list columns as column>
		//${column.remarks}
		if (${tableName }.get${column.javaProperty?cap_first}() != null && !${tableName }.get${column.javaProperty?cap_first}().equals("")) {
			criteria.and${column.javaProperty?cap_first}EqualTo(${tableName }.get${column.javaProperty?cap_first}());
		}
		</#list>
		//order
		if(${tableName }.getSortby()!=null&&${tableName }.getOrder()!=null){
			${tableName }Criteria.setOrderByClause(${tableName }.getSortby()+" "+${tableName }.getOrder());
		}
		//pager
		if (${tableName }.getPage() != null && ${tableName }.getRows() != null) {
			PageHelper.startPage(${tableName }.getPage(), ${tableName }.getRows());
		}
		return ${tableName }Mapper.selectByExample(${tableName }Criteria);	
	}

	@Override
	public List<${tableName?cap_first}> selectAll() {
		return ${tableName }Mapper.selectByExample(null);
	}

	@Override
	public ${tableName?cap_first} selectByPrimaryKey(${keyType } key) {
		return ${tableName }Mapper.selectByPrimaryKey(key);
	}

	@Override
	public int selectCount(${tableName?cap_first} ${tableName }) {
		${tableName?cap_first}Criteria ${tableName }Criteria = new ${tableName?cap_first}Criteria();
		Criteria criteria = ${tableName }Criteria.createCriteria();
		<#list columns as column>
		//${column.remarks}
		if (${tableName }.get${column.javaProperty?cap_first}() != null && !${tableName }.get${column.javaProperty?cap_first}().equals("")) {
			criteria.and${column.javaProperty?cap_first}EqualTo(${tableName }.get${column.javaProperty?cap_first}());
		}
		</#list>
		return ${tableName }Mapper.countByExample(${tableName }Criteria);
	}

	@Override
	public List<${tableName?cap_first}> selectByCriteria(${tableName?cap_first} ${tableName }) {
		${tableName?cap_first}Criteria ${tableName }Criteria = new ${tableName?cap_first}Criteria();
		Criteria criteria = ${tableName }Criteria.createCriteria();
		<#list columns as column>
		//${column.remarks}
		if (${tableName }.get${column.javaProperty?cap_first}() != null && !${tableName }.get${column.javaProperty?cap_first}().equals("")) {
			criteria.and${column.javaProperty?cap_first}EqualTo(${tableName }.get${column.javaProperty?cap_first}());
		}
		</#list>
		return ${tableName }Mapper.selectByExample(${tableName }Criteria);
	}

	@Override
	public int insert(${tableName?cap_first} ${tableName }) {
		return ${tableName }Mapper.insert(${tableName });
	}

	@Override
	public int update(${tableName?cap_first} ${tableName }) {
		return ${tableName }Mapper.updateByPrimaryKey(${tableName });
	}

	@Override
	public int updateByPrimaryKeySelective(${tableName?cap_first} ${tableName }) {
		return ${tableName }Mapper.updateByPrimaryKeySelective(${tableName });
	}

	@Override
	public int deleteByPrimaryKey(${keyType } key) {
		return ${tableName }Mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int deleteByCriteria(${tableName?cap_first} ${tableName }) {
		${tableName?cap_first}Criteria ${tableName }Criteria = new ${tableName?cap_first}Criteria();
		Criteria criteria = ${tableName }Criteria.createCriteria();
		<#list columns as column>
		//${column.remarks}
		if (${tableName }.get${column.javaProperty?cap_first}() != null && !${tableName }.get${column.javaProperty?cap_first}().equals("")) {
			criteria.and${column.javaProperty?cap_first}EqualTo(${tableName }.get${column.javaProperty?cap_first}());
		}
		</#list>
		return ${tableName }Mapper.deleteByExample(${tableName }Criteria);
	}

}
