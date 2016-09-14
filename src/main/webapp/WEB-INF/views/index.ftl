<html>
<head><title>FreeMarker Spring MVC Hello World</title></head>
<body>
<div id="header">
<#macro greet person color>
	<H2 style="color:${color}">
		FreeMarker Spring MVC, Hello ${person} and ${avg(10,30)}
	</H2>
</#macro>
<@greet person="new beginner" color="red"/>
</div>

<#function avg x y>
	<#return (x+y)/2>
</#function>

<#assign x="something">

<h3>${indexOf("met",x)} and ${indexOf("foo",x)}<h3>
<div id="content">
    
  <fieldset>
  	<legend>Add User</legend>
  <form name="user" action="add.html" method="post">
  	Firstname: <input type="text" name="firstname" />	<br/>
  	Lastname: <input type="text" name="lastname" />	<br/>
  	<input type="submit" value="   Submit   " />
  </form>
  </fieldset>
  <br/>
  <table class="datatable">
  	<tr>
  		<th>Firstname</th>  <th>Lastname</th>
  	</tr>
    <#list model["userList"] as user>
  	<tr>
  		<td>${user.firstname}</td> <td>${user.lastname}</td>
  	</tr>
    </#list>
  </table>

</div>  
</body>
</html>  