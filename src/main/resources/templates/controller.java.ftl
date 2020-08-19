package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * @author ${author}
 * @since ${date}
*/
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${entity?uncap_first}Service;

    <#if cfg.queryById>
    @RequestMapping("/queryById/{id}")
    public ${entity} queryById(@PathVariable("id") Integer id) {
        return ${entity?uncap_first}Service.getById(id);
    }
    </#if>

}
</#if>
