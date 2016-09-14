<#import "spring.ftl" as spring /> 
<html>
<head><title>FreeMarker Spring MVC</title></head>
<body>
<div id="header">
	<H2 style="color:black">
		new product
	</H2>
</div>

<div id="itemInfo">
    
  <fieldset>
  	<legend>Add User</legend>
	  <form name="item" action="addItem.html" method="post">
	  	proName:&nbsp;&nbsp;<input type="text" name="proName"/>	<br/>
	  	<#if item??>  
	    	<@spring.bind "item.proName" />  
	    	<@spring.showErrors "<br>"/>  <br/>
		</#if>  	
	  	proDesc:&nbsp;&nbsp;<input type="text" name="proDesc" />	<br/>
	  	<#if item??>  
	    	<@spring.bind "item.proDesc" />  
	    	<@spring.showErrors "<br>"/>  <br/>
		</#if>
	  	phoneNum:&nbsp;<input type="text" name="phoneNum"/>	<br/>
	  	<#if item??>  
	    	<@spring.bind "item.phoneNum" />  
	    	<@spring.showErrors "<br>"/>  <br/>
		</#if>
	  	email:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="email"/>	<br/>
	  	<#if item??>  
	    	<@spring.bind "item.email" />  
	    	<@spring.showErrors "<br>"/>  <br/>
		</#if>
	  	<input type="submit" value="   Submit   " />
	  </form>
  </fieldset>
  
  <fieldset>
  	<legend>upload pictures</legend>
  	<form enctype="multipart/form-data" action="/upload" method="POST">
    	File to upload: <input type="file" name="file"><br /> 
    	Name: <input type="text" name="name"><br /> <br /> 
    	<input type="submit" value="Upload"> Press here to upload the file!
	</form>
  </fieldset>
  <br/>
  
  <#if imagesName??>
  	<img src="/images/${imagesName}" style="width:30px; height:28px"/>
  </#if>
  
  <table class="datatable" border="1" cellpadding="10" cellspacing="0">
  	<br/>
  	<tr>
  		<th>id</th>    <th>name</th>    <th>description</th>   <th>phoneNumber</th>   <th>email</th> <th>operation</th>
  	</tr>
	
	<#if proItem??>
		<#list proItem?keys as key>
			<#if !proItem[key].deleted>
				<tr>
			  		<td>${proItem[key].id}</td> <td>${proItem[key].proName}</td> <td>${proItem[key].proDesc}</td> <td>${proItem[key].phoneNum}</td> <td>${proItem[key].email}</td>
			  		<td>
			  			<a href="deleteItem/${proItem[key].id}">Delete</a>
			  		</td>
			  		<td>
			  			<a href="changeItem/${proItem[key].id}">Change</a>
			  		</td>
			  	</tr>
			</#if>
		</#list>
	</#if>
  </table>

</div>  
</body>
</html>  