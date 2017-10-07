package ${package };

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ${modelPackage }.${tableName?cap_first};
import ${servicePackage }.${tableName?cap_first}Service;
import com.base.BaseController;
import com.base.PageBean;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("${tableName }")
public class ${tableName?cap_first}Controller extends BaseController{

	private Logger log = LoggerFactory.getLogger(${tableName?cap_first}Controller.class);

	@Autowired
	private ${tableName?cap_first}Service ${tableName }Service;

	@ApiOperation(value = "获取${tableName?cap_first}信息", notes = "获取${tableName?cap_first}信息")
	@RequestMapping(value = "getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<${tableName?cap_first}> getAll() {
		return ${tableName }Service.selectAll();
	}

	@ApiOperation(value = "获取一个${tableName?cap_first}信息", notes = "获取一个${tableName?cap_first}信息")
	@ApiParam(name = "key", value = "${tableName?cap_first}ID", required = true)
	@RequestMapping(value = "getByKey/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ${tableName?cap_first} getByKey(@PathVariable ${keyType } key) {
		return ${tableName }Service.selectByPrimaryKey(key);
	}

	@ApiOperation(value = "查询${tableName?cap_first}信息", notes = "查询${tableName?cap_first}信息")
	@RequestMapping(value = "getByConditionPage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PageBean<${tableName?cap_first}> getByConditionPage(@ApiParam(value = "${tableName?cap_first}信息", required = false) @RequestBody ${tableName?cap_first} ${tableName }) {
		List<${tableName?cap_first}> ${tableName }List = ${tableName }Service.select(${tableName });
		PageBean<${tableName?cap_first}> pageBean = new PageBean<${tableName?cap_first}>(${tableName }List);
		return pageBean;
	}

	@ApiOperation(value = "添加${tableName?cap_first}信息", notes = "添加${tableName?cap_first}信息")
	@RequestMapping(value = "inster", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelMap inster(@ApiParam(value = "${tableName?cap_first}信息", required = false) @RequestBody ${tableName?cap_first} ${tableName }) {
		ModelMap result = new ModelMap();
		try {
			${tableName }Service.insert(${tableName });
			result.put("key", ${tableName }.getId());
		} catch (Exception e) {
			log.error("添加失败", e);
			result.put("error", "添加失败");
		}
		return result;
	}

	@ApiOperation(value = "更新${tableName?cap_first}信息", notes = "更新${tableName?cap_first}信息")
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelMap update(@ApiParam(value = "${tableName?cap_first}信息", required = false) @RequestBody ${tableName?cap_first} ${tableName }) {
		ModelMap result = new ModelMap();
		try {
			${tableName }Service.update(${tableName });
		} catch (Exception e) {
			log.error("更新失败", e);
			result.put("error", "更新失败");
		}
		return result;
	}

	@ApiOperation(value = "更新${tableName?cap_first}选择信息", notes = "更新${tableName?cap_first}选择信息")
	@RequestMapping(value = "updateSelective", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelMap updateByPrimaryKeySelective(@ApiParam(value = "${tableName?cap_first}信息", required = false) @RequestBody ${tableName?cap_first} ${tableName }) {
		ModelMap result = new ModelMap();
		try {
			${tableName }Service.updateByPrimaryKeySelective(${tableName });
		} catch (Exception e) {
			log.error("更新失败", e);
			result.put("error", "更新失败");
		}
		return result;
	}

	@ApiOperation(value = "删除一个${tableName?cap_first}信息", notes = "删除一个${tableName?cap_first}信息")
	@ApiParam(name = "key", value = "${tableName?cap_first}ID", required = true)
	@RequestMapping(value = "deleteByKey/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelMap deleteByKey(@PathVariable ${keyType } key) {
		ModelMap result = new ModelMap();
		try {
			${tableName }Service.deleteByPrimaryKey(key);
		} catch (Exception e) {
			log.error("删除失败", e);
			result.put("error", "删除失败");
		}
		return result;
	}

	@ApiOperation(value = "删除${tableName?cap_first}信息", notes = "删除${tableName?cap_first}信息")
	@RequestMapping(value = "deleteByCriteria", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelMap deleteByCriteria(@ApiParam(value = "${tableName?cap_first}信息", required = false) @RequestBody ${tableName?cap_first} ${tableName }) {
		ModelMap result = new ModelMap();
		try {
			${tableName }Service.deleteByCriteria(${tableName });
		} catch (Exception e) {
			log.error("删除失败", e);
			result.put("error", "删除失败");
		}
		return result;
	}

	@ApiOperation(value = "获取${tableName?cap_first}数量", notes = "获取${tableName?cap_first}数量")
	@RequestMapping(value = "selectCount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public int selectCount(@ApiParam(value = "${tableName?cap_first}信息", required = false) @RequestBody ${tableName?cap_first} ${tableName }) {
		return ${tableName }Service.selectCount(${tableName });
	}
}
