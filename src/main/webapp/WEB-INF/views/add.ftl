<#import "spring.ftl" as spring /> 
<html>
<head><title>change item</title></head>
<body>
<div id="header">
	<H2 style="color:b">
		change and save
	</H2>
</div>

<div id="content">
  <fieldset>
  	<form name="problem" action="saveChange.html" method="post">
  		proName: <input type="text" name="proName" value="${problem.proName}"/> </tr><br/>
  		<#if item??>  
    		<@spring.showErrors "<br>"/>  <br/>
		</#if>  
  		proDesc: <input type="text" name="proDesc" value="${problem.proDesc}"/> </tr><br/>
  		<#if item??>
    		<@spring.showErrors "<br>"/>  <br/>
		</#if>  
  		phoneNum: <input type="text" name="phoneNum" value="${problem.phoneNum}"/> </tr><br/>
  		<#if item??>
    		<@spring.showErrors "<br>"/>  <br/>
		</#if>  
  		email: <input type="text" name="email" value="${problem.email}"/> </tr><br/>
  		<#if item??>
    		<@spring.showErrors "<br>"/>  <br/>
		</#if>  
  		<input type="submit" value="   save  " />
  	<form>
  </fieldset>
</div>  
</body>