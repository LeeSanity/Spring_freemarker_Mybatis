<html>
<head><title>sf login</title></head>
<body>
<div id="header">
	<H2 style="color:b">
		Please Login
	</H2>
</div>
<#if hint??>
	<p>${hint}</p>
</#if>
<div id="content">
  <fieldset>
  	<form name="changeItem" action="/login" method="post">
  		proName: <input type="text" name="userId""/> </tr> </br>
  		proDesc: <input type="text" name="password"/> </tr> </br>
  		<input type="submit" value="   Login  " />
  	<form>
  </fieldset>
</div>  
</body>
</html>  